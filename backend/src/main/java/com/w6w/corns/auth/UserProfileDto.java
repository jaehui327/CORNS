package com.w6w.corns.auth;

import com.w6w.corns.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfileDto {
    private final String email;
    private final String imgUrl;

    @Builder
    public UserProfileDto(String email, String imgUrl) {
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public User toUser(){
        return User.builder()
                .email(email)
                .imgUrl(imgUrl)
                .build();
    }
}
