package com.w6w.corns.controller;

import com.w6w.corns.domain.user.User;
import com.w6w.corns.dto.evaluation.SelfEvaluationDto;
import com.w6w.corns.dto.evaluation.ThumbResultDto;
import com.w6w.corns.dto.evaluation.ThumbLogDto;
import com.w6w.corns.service.evaluation.EvaluationService;
import com.w6w.corns.service.room.RoomService;
import com.w6w.corns.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/evaluation")
@Api("평가 컨트롤러")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;


    @ApiOperation("따봉멤버 투표")
    @PostMapping("/thumb")
    public ResponseEntity<?> voteThumbMember(@RequestBody Map<String, Object> map) {
        int roomNo = (int)map.get("roomNo");
        int fromId = (int)map.get("fromId");
        int toId = (int)map.get("toId");
        evaluationService.voteThumbMember(new ThumbLogDto(roomNo, fromId, toId));

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation("따봉멤버 결과")
    @GetMapping("/thumb/{roomNo}")
    public ResponseEntity<?> getThumbResult(@PathVariable int roomNo) {
        try {
            Map<String, Object> response = new HashMap<>(); //반환용 map 객체
            List<ThumbResultDto> resultList = new ArrayList<>();    //따봉멤버 결과 리스트

            List<Integer> userList = roomService.getUserList(roomNo);   //참여자 리스트
            for (int userId : userList) {
                User user = userService.getUser(userId);
                resultList.add(ThumbResultDto.builder()
                        .userId(userId)
                        .nickname(user.getNickname())
                        .imgUrl(user.getImgUrl())
                        .thumbCnt(evaluationService.getThumbResult(roomNo, userId))
                        .build());
            }

            response.put("resultList", resultList);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("자기평가 등록")
    @PostMapping("/self")
    public ResponseEntity<?> writeSelfEvaluation(@RequestBody SelfEvaluationDto selfEvalDto) {
        evaluationService.writeSelfEvaluation(selfEvalDto);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
