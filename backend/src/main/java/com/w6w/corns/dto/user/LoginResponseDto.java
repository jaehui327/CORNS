package com.w6w.corns.dto.user;

import com.w6w.corns.domain.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponseDto {

    private int userId;
    private String email;
    private String nickname;
    private String imgUrl;
    private int expTotal;
    private int level;
    private int friendTotal;
    private int social;
    private String refreshToken;
    private int attendTotal;
    private int conversationTotal;
    private int ddabongTotal;

    @Builder
    public LoginResponseDto(int userId, String email, String nickname, String imgUrl, String refreshToken) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.refreshToken = refreshToken;
    }

    @Builder
    public LoginResponseDto(User user){
        this.userId=user.getUserId();
        this.email=user.getEmail();
        this.imgUrl=user.getImgUrl();
        this.nickname=user.getNickname();
        this.level=user.getLevel().getLevelNo();
        //여기서부터 계속
//        this.
    }
}
