package com.w6w.corns.user.service;

import com.w6w.corns.user.domain.User;
import com.w6w.corns.user.domain.UserRepository;
import com.w6w.corns.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public int signUp(UserRequestDto user) throws Exception {

        //이메일 검증 필요
        int result = validateDuplicateUser(user.getEmail());
        if (result == 1) return -1;
         else {
            //암호화필요
            user.setSocial(1); //기본 회원가입 설정
            userRepository.save(user.toEntity()); //회원 저장
            return 1;
        }
    }

    @Override
    public int validateDuplicateUser(String email){
        User findUser = userRepository.findByEmail(email);
//        System.out.println("findUser = " + findUser);
        if(findUser == null) return 0; //중복 x
        else return 1; //중복
    }


    @Override
    @Transactional(readOnly = true)
    public User login(User user) throws Exception{

        User loginUser = userRepository.findByEmail(user.getEmail());
        System.out.println("user = " + loginUser);
        if(!user.getPassword().equals(loginUser.getPassword())) return null;
        else return loginUser;
    }

    @Override
    public int saveRefreshToken(int userId, String refreshToken) throws Exception {
        return userRepository.updateRefreshToken(userId, refreshToken);
    }

    @Override
    public Object getRefreshToken(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteRefreshToken(int id) throws Exception {

    }
}
