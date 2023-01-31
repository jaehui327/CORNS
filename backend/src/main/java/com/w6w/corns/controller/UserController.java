package com.w6w.corns.controller;

//import com.w6w.corns.auth.OAuthService;
import com.w6w.corns.dto.oauth.GetSocialOauthRes;
import com.w6w.corns.dto.oauth.GoogleUserDto;
import com.w6w.corns.dto.user.*;
import com.w6w.corns.service.jwt.JwtService;
import com.w6w.corns.service.oauth.OAuthService;
import com.w6w.corns.service.user.UserService;
import com.w6w.corns.util.Constant.SocialType;
import com.w6w.corns.util.SHA256Util;
import com.w6w.corns.util.code.UserCode;
import io.swagger.annotations.Api;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final OAuthService oAuthService;

    /**
     * 예외 처리
     * @param e
     * @return
     */
    private ResponseEntity<?> exceptionHandling(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 기본 회원가입
     * @param user
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinRequestDto user){
        try {
            user.setSocial(1);
            int result = userService.signUp(user);
            if(result < 0) return new ResponseEntity<>(HttpStatus.CONFLICT); //중복 이메일
            else return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @GetMapping("/email-check/{email}")
    public ResponseEntity<?> checkDuplicateEmail(@PathVariable String email){

        System.out.println("email = " + email);
        try {
            int result = userService.validateDuplicateUser(email);
            System.out.println("result = " + result);
            if(result == 0) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error((e.getMessage()));
            return exceptionHandling(e);
        }
    }

    /**
     * 기본 로그인
     * @param requestUser
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto requestUser){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        LoginResponseDto loginUser = null;
        try {
            loginUser = userService.login(requestUser);

            if(loginUser == null) status = HttpStatus.UNAUTHORIZED;
            else{
                String accessToken = jwtService.createAccessToken("id", loginUser.getUserId());
                String refreshToken = jwtService.createRefreshToken("id", loginUser.getUserId());

                //lastLoginTm 갱신
                userService.updateLastLoginTm(loginUser.getUserId());

                userService.saveRefreshToken(loginUser.getUserId(), refreshToken);
                loginUser.setRefreshToken(refreshToken);

                resultMap.put("accessToken", accessToken);
                resultMap.put("refreshToken", refreshToken);
                resultMap.put("loginUser", loginUser);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            log.error("로그인 실패 "+ e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }


    /**
     * 소셜 로그인으로 리다이렉트
     * @param socialPath
     * @return
     */
    @GetMapping("/login/auth/{socialType}")
    public void socialLoginRedirect(@PathVariable(name = "socialType") String socialPath){

        System.out.println("socialPath = " + socialPath);
        SocialType socialType = SocialType.valueOf(socialPath.toUpperCase());
        try {
            oAuthService.request(socialType);
            System.out.println("hh");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  소셜 로그인 서버 요청에 의한 callback 처리
     * @param socialPath
     * @param code - code API Server 에서 넘어오는 code
     * @return
     */
    @GetMapping("/auth/{socialType}/callback")
    public ResponseEntity<?> callback(@PathVariable(name = "socialType") String socialPath,
            @RequestParam(name = "code") String code){

        SocialType socialType = SocialType.valueOf(socialPath.toUpperCase());
        try {
            Map<String, Object> resultMap = new HashMap<>();

            LoginResponseDto loginResponseDto = oAuthService.oAuthLogin(socialType, code);

            String accessToken = jwtService.createAccessToken("id", loginResponseDto.getUserId());
            String refreshToken = jwtService.createRefreshToken("id", loginResponseDto.getUserId());

            log.debug("accessToken = " + accessToken);
            log.debug("refreshToken = " + refreshToken);

            //경험치도 줘야함!, 리팩토링 필요
            userService.saveRefreshToken(loginResponseDto.getUserId(), refreshToken);
            userService.updateLastLoginTm(loginResponseDto.getUserId());
            loginResponseDto.setRefreshToken(refreshToken);

            //lastLoginTm 갱신
            userService.updateLastLoginTm(loginResponseDto.getUserId());

            // 로그인로그
            userService.makeLoginLog(loginResponseDto.getUserId());

            resultMap.put("accessToken", accessToken);
            resultMap.put("refreshToken", refreshToken);
            resultMap.put("loginUser", loginResponseDto);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    /**
     * 로그아웃
     * @param userId
     * @return
     */
    @PostMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable int userId){

        try {
            userService.deleteRefreshToken(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    /**
     * 토큰 재발급
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> reissueToken(@RequestBody int userId, HttpServletRequest request){

        HttpStatus status = null;
        String token = request.getHeader("refreshToken");
        try{
            if(jwtService.checkToken(token) && token.equals(userService.getRefreshToken(userId))){
                String accessToken = jwtService.createAccessToken("id", userId);
                log.debug("토큰 재발급");
                Map<String, Object> result = new HashMap<>();
                result.put("accessToken",accessToken);
                status = HttpStatus.OK;

                return new ResponseEntity<>(result, status);
            }else{
                log.debug("refresh token 만료");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(status);
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable int userId){

        try {
            LoginResponseDto user = userService.findByUserId(userId);
            Map<String, LoginResponseDto> result = new HashMap<>();
            result.put("user",user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @PatchMapping
    public ResponseEntity<?> modifyUserInfo(@RequestBody UserModifyRequestDto user){

        try {
            return new ResponseEntity<>(userService.updateUserInfo(user), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkPassword(@RequestBody UserLoginRequestDto user){

        try {
            if(userService.isSamePassword(user)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> withdraw(@PathVariable int userId){

        try {
            userService.updateUserCd(userId, UserCode.USER_UNREGISTER.getCode());
            userService.deleteRefreshToken(userId);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }
}
