package com.w6w.corns.service.user;

import com.w6w.corns.domain.loginlog.LoginLogRepository;
import com.w6w.corns.domain.user.User;
import com.w6w.corns.domain.user.UserRepository;
import com.w6w.corns.dto.loginlog.LoginLogSaveDto;
import com.w6w.corns.dto.user.*;
import com.w6w.corns.util.SHA256Util;
import com.w6w.corns.util.code.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final LoginLogRepository loginLogRepository;

    @Override
    @Transactional
    public int signUp(UserJoinRequestDto requestUser) throws Exception {

        //최종적으로 이메일 검증
        int result = validateDuplicateUser(requestUser.getEmail());

        if (result == 1) return -1;
         else {
             //암호화
            String salt = SHA256Util.generateSalt();
            String newPass = SHA256Util.getEncrypt(requestUser.getPassword(), salt);

            userRepository.save(
                    User.userRegister()
                            .email(requestUser.getEmail())
                            .nickname(requestUser.getNickname())
                            .password(newPass) //암호화된 비밀번호 저장
                            .salt(salt) 
                            .social(1) //기본 회원가입 사용자로 설정
                            .build()); //회원 저장
            return 1;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public int validateDuplicateUser(String email){
        User findUser = userRepository.findByEmail(email);
        if(findUser == null) return 0; //중복 x
        else return 1; //중복
    }


    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(UserLoginRequestDto requestUser) throws Exception{

        //해당 이메일을 가진 객체를 db에서 찾음
        User user = userRepository.findByEmail(requestUser.getEmail());

        //탈퇴회원 및 이용정지회원은 나중에 처리하기
        //이메일, 비밀번호 일치 확인 && 회원코드 확인
        if(isSamePassword(requestUser) && user.getUserCd() == UserCode.USER_DEFAULT.getCode()){

            //로그인로그 insert
            makeLoginLog(user.getUserId());

            //따봉, 친구, 출석, 발화량 나중에 추가 필요
            return LoginResponseDto.fromEntity(user);
        }
        return null;
    }
    @Override
    @Transactional
    public void makeLoginLog(int userId) throws Exception{

        //로그인로그 데이터 삽입
        LoginLogSaveDto loginLogSaveDto = LoginLogSaveDto.builder().userId(userId).build();
        loginLogRepository.save(loginLogSaveDto.toEntity());
    }
    @Override
    @Transactional(readOnly = true)
    public void updateLastLoginTm(int userId) throws Exception{
        
        //last_login_tm 변경
        User user = userRepository.findByUserId(userId);
        userRepository.updateUserLastLoginTm(userId, LocalDateTime.now());
        System.out.println("user = " + user);
    }

    @Override
    @Transactional
    public int saveRefreshToken(int userId, String refreshToken) throws Exception {
        return userRepository.updateRefreshToken(userId, refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public String getRefreshToken(int userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        return user.getRefreshToken();
    }

    @Override
    @Transactional
    public void deleteRefreshToken(int userId) throws Exception {
        userRepository.updateRefreshToken(userId, null);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto findByUserId(int userId) throws Exception{
        User user = userRepository.findByUserId(userId);
        return LoginResponseDto.fromEntity(user);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto findByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        return LoginResponseDto.fromEntity(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSamePassword(UserLoginRequestDto requestUser) throws Exception {

        User user = userRepository.findByEmail(requestUser.getEmail());

        if(user != null){
            String userSalt = user.getSalt();

            //입력받은 user의 password 정보
            String inputPass = requestUser.getPassword();
            String newPass = SHA256Util.getEncrypt(inputPass, userSalt);

            if(newPass.equals(user.getPassword())) return true;
        }
        return false;
    }

    public boolean updateUserPassword(Map<String, Object> body) throws Exception{

        int userId = (int) body.get("userId");
        String pass = (String) body.get("password");
        String newPass = (String) body.get("newPassword");

        User user = userRepository.findByUserId(userId);
        UserLoginRequestDto requestDto = new UserLoginRequestDto(user.getEmail(), pass);

        //비밀번호 확인
        if(!isSamePassword(requestDto)) return false;

        //비밀번호 변경
        String salt = SHA256Util.generateSalt();
        String encryptPass = SHA256Util.getEncrypt(newPass, salt);
        user.ssetPassword(encryptPass);

        userRepository.save(user);
        return true;
    }
    @Override
    @Transactional
    public UserModifyRequestDto updateUserInfo(UserModifyRequestDto requestUser) throws Exception{

        System.out.println("requestUser = " + requestUser);
        User user = userRepository.findByUserId(requestUser.getUserId());

        if(requestUser.getNickname() != null){
            userRepository.updateNickname(requestUser.getUserId(), requestUser.getNickname());

        }else if(requestUser.getImgUrl() != null){
            userRepository.updateImgUrl(requestUser.getUserId(), requestUser.getImgUrl());

        }
        return new UserModifyRequestDto().builder().user(user).build();
    }

    @Override
    @Transactional
    public void updateUserCd(int userId, int userCd) throws Exception{
        userRepository.updateUserCd(userId, userCd);
    }
}
