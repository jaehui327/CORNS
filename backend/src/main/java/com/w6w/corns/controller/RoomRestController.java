package com.w6w.corns.controller;

import com.w6w.corns.domain.room.Room;
import com.w6w.corns.dto.room.CreateRoomRequestDto;
import com.w6w.corns.dto.room.RoomRequestDto;
import com.w6w.corns.dto.room.RoomUserRequestDto;
import com.w6w.corns.service.room.RoomService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.*;

@RestController()
@RequestMapping("room")
public class RoomRestController {

    private final Logger logger = LoggerFactory.getLogger(RoomRestController.class);

    @Autowired
    RoomService roomService;


    @ApiOperation(value = "쫑알룸 생성하기", notes = "방 정보와 OpenVidu 관련 정보를 body에 담아서 요청")
    @PostMapping()
    private ResponseEntity<?> save(@RequestBody CreateRoomRequestDto body) {
        logger.debug("request body: {}", body);
        Map resultMap = new HashMap<>();
        HttpStatus status;

        try {
            if (roomService.save(body) == 1)
                status = HttpStatus.OK;
            else
                status = HttpStatus.CONFLICT;
        } catch (Exception e) {
            resultMap.put("message", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultMap, status);
    }

    @ApiOperation(value = "쫑알쫑알 전체 목록 보기", notes = "필터링 추가해야 함")
    @GetMapping()
    private ResponseEntity<?> getAllRooms(Pageable pageable) {

        Map resultMap = new HashMap<>();
        HttpStatus status;

        try {
            List<Room> rooms = roomService.findAll();
            logger.debug("rooms: {}", rooms);
            if (rooms.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultMap.put("rooms", rooms);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultMap, status);
    }

    @ApiOperation(value = "쫑알룸 정보 가져오기", notes = "쫑알룸 room_no를 path variable로 넘기면 쫑알룸 정보를 리턴")
    @GetMapping(value = "/{roomNo}")
    private ResponseEntity<?> getRoom(@PathVariable Integer roomNo) {

        logger.debug("room_no: {}", roomNo);
        Map resultMap = new HashMap<>();
        HttpStatus status;

        try {
            Optional<Room> room = roomService.findByRoomNo(roomNo);
            logger.debug("room: {}", room);
            if (room.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultMap.put("room", room);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultMap, status);
    }
}
