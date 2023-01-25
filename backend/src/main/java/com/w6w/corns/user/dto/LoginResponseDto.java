package com.w6w.corns.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private int userId;
    private String email;
    private String nickname;
    private String imgUrl;
    private String accessToken;
    private String refreshToken;


    @Builder
    public LoginResponseDto(int userId, String email, String nickname, String imgUrl, String accessToken, String refreshToken) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
