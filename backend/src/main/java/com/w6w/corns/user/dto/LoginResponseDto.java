package com.w6w.corns.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private int userId;
    private String email;
    private String nickname;
    private String imgUrl;
    private int exp;
    private int level;
    private int friendTotal;
    private int social;
    private String accessToken;
    private String refreshToken;
    private int attendTotal;
    private int conversationTotal;
    private int ddabongTotal;

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
