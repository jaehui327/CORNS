package com.w6w.corns.user.service;

import com.w6w.corns.user.domain.User;

public interface UserService {

    int signUp(User user) throws Exception;
    User login(User user) throws Exception;
    int saveRefreshToken(int id, String refreshToken) throws Exception;
    Object getRefreshToken(int id) throws Exception;
    void deleteRefreshToken(int id) throws Exception;

}
