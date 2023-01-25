package com.w6w.corns.user.controller;

import com.w6w.corns.auth.OAuthService;
import com.w6w.corns.jwt.JwtService;
import com.w6w.corns.user.domain.User;
import com.w6w.corns.user.dto.LoginResponseDto;
import com.w6w.corns.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //join
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user){
        try {
            int result = userService.signUp(user);
            if(result < 0) return new ResponseEntity<>(HttpStatus.CONFLICT); //중복 이메일
            else return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        User loginUser = null;
        try {
            loginUser = userService.login(user);
            System.out.println("loginUser = " + loginUser);
            if(loginUser == null) status = HttpStatus.UNAUTHORIZED;
            else{
                String accessToken = jwtService.createAccessToken("id", loginUser.getUserId());
                String refreshToken = jwtService.createRefreshToken("id", loginUser.getUserId());

                log.debug("accessToken = " + accessToken);
                log.debug("refreshToken = " + refreshToken);

                userService.saveRefreshToken(loginUser.getUserId(), refreshToken);

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

    //Authorization code 받기 -> OAuth 서버에 토큰 요청
    //access token으로 이름, 이메일, 프로필url 요청
    //db에 존재한다면 업데이트, 아니면 새로 등록
    //유저 primary key 값으로 jwt 토큰 생성
//    @PostMapping("/login/google")
//    public ResponseEntity<?> googleLogin(@RequestBody User user){
//        //처음 로그인
//        try {
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        //기존 로그인 회원
//        return null;
//    }
//
    @GetMapping("/login/google")
    public ResponseEntity<?> googleLogin(@RequestParam String code){

        LoginResponseDto loginResponseDto = oAuthService.login("google", code);
        return ResponseEntity.ok().body(loginResponseDto);
    }

    //logout
}
