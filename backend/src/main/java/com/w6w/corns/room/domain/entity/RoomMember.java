package com.w6w.corns.room.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class RoomMember {

    @Id()
    @Column(name = "room_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomUserNo;
    @Column(name = "room_no", nullable = false)
    private int roomNo;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "room_user_code", nullable = false)
    private int roomUserCd;

    private String connectionId;

    private String recordId;

    private String token;

    private String scriptUrl;

    private LocalDateTime regTm;

    private LocalDateTime modTm;

}
