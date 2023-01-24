package com.w6w.corns.room.room.entity;

import com.w6w.corns.util.RoomCode;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@Entity
public class Room {

    @Id()
    @Column(name = "room_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNo;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "time", nullable = false)
    private int time;
    @Column(name = "max_member", nullable = false)
    private int maxMember;
    @Column(name = "subject_no", nullable = false)
    private int subjectNo;
    @Column(name = "host_user_id", nullable = false)
    private int hostUserId;
    @Column(name = "room_cd",  nullable = false)
    private int roomCd;
    @Column(name = "session_id", nullable = true, length = 1_000)
    private String sessionId;
    @Column(name = "script_url", nullable = true, length = 1_000)
    private String scriptUrl;
    @Column(name = "reg_tm", nullable = false)
    private LocalDateTime regTm;
    @Column(name = "mod_tm", nullable = true)
    private LocalDateTime modTm;
}
