package com.w6w.corns.user.service;

import com.w6w.corns.user.domain.User;
import com.w6w.corns.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     *
     * @param user 가입하는 회원 정보
     * @throws Exception
     */
    @Override
    public int signUp(User user) throws Exception {

        //이메일 검증 필요
        int result = validateDuplicateUser(user);
        if (result == 0) return -1;
         else {
            //암호화필요, social도 함께 저장 필요
            userRepository.save(user);
            userRepository.updateSocial(user.getUserId(),
                    user.getSocial() | 1);
            return 1;
        }
    }
    private int validateDuplicateUser(User user){
        System.out.println("user = " + user);
        User findUser = userRepository.findByEmail(user.getEmail());
//        System.out.println("findUser = " + findUser);
        if(findUser == null) return 1; //중복 x
        else return 0; //중복
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
