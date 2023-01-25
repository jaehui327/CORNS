package com.w6w.corns.user.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(name="nickname", length = 20)
    String nickname;

    @Column(length = 100)
    String email;

    @Column(length = 512)
    String password;

    @Column(length = 1000)
    String imgUrl;

    @Column(columnDefinition = "SMALLINT")
    int expTotal;

    @Column(columnDefinition = "SMALLINT")
    int level;

    @Column(columnDefinition = "SMALLINT")
    int friendTotal;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime lastLoginTm;

    @Column(name="social", columnDefinition = "TINYINT", length=1)
    int social;

    @Column(length = 1000)
    String refreshToken;

    @Column(name="delete_yn", length=1)
    char deleteYn;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime reg_tm;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime mod_tm;

    @Builder(builderClassName = "UserRegister", builderMethodName = "userRegister")
    public User(String email, String password, String nickname, int social) {
        this.email = email;
        this.password=password;
        this.nickname=nickname;
        this.social = social;
    }

    public User update(String email, String imgUrl, int social){
        this.email=email;
        this.imgUrl=imgUrl;
        this.social=social;
        return this;
    }

}
