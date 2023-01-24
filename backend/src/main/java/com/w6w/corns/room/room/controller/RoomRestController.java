package com.w6w.corns.room.room.controller;

import com.w6w.corns.room.room.entity.Room;
import com.w6w.corns.room.room.model.service.RoomService;
import com.w6w.corns.util.RoomCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/room")
public class RoomRestController {

    private final Logger logger = LoggerFactory.getLogger(RoomRestController.class);

    @Autowired
    RoomService roomService;

    @ApiOperation(value = "쫑알룸 생성하기", notes = "title, time, max_member, subject_no, host_user_id, session_id를 body에 담아 요청")
    @PostMapping()
    private ResponseEntity<?> save(@RequestBody Room room) {
        logger.debug("room: {}", room);
        Map resultMap = new HashMap<>();
        HttpStatus status;

        try {
            room.setRoomCd(RoomCode.ROOM_WAITING.getCode());
            room.setRegTm(LocalDateTime.now());
            Room saveRoom = roomService.save(room);
            logger.debug("saveRoom: {}", saveRoom);
            resultMap.put("room", saveRoom);
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultMap, status);
    }

}
