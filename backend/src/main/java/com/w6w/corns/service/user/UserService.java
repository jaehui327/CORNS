package com.w6w.corns.service.user;

import com.w6w.corns.dto.user.LoginResponseDto;
import com.w6w.corns.dto.user.UserRequestDto;

public interface UserService {

    int signUp(UserRequestDto requestUser) throws Exception;
    int validateDuplicateUser(String emails) throws Exception;
    LoginResponseDto login(UserRequestDto requestUser) throws Exception;
    void updateLastLoginTm(int userId) throws Exception;
    int saveRefreshToken(int userId, String refreshToken) throws Exception;
    Object getRefreshToken(int userId) throws Exception;
    void deleteRefreshToken(int userId) throws Exception;
    LoginResponseDto findUserById(int userId) throws Exception;
    LoginResponseDto findByEmail(String email) throws Exception;
    boolean isSamePassword(UserRequestDto requestUser) throws Exception;
}
