package com.w6w.corns.controller;

import com.w6w.corns.dto.level.LevelDto;
import com.w6w.corns.dto.user.UserRequestDto;
import com.w6w.corns.service.growth.GrowthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/growth")
@Api("성장기록 컨트롤러")
public class GrowthController {

    private final GrowthService growthService;

    private ResponseEntity<?> exceptionHandling(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/exp/{userId}")
    public ResponseEntity<?> levelBar(@PathVariable int userId){

        try {
            Map<String, Object> result = new HashMap<>();

            //경험치 상위 백분위
            int percentile = growthService.calExpPercentile(userId);

            //레벨바
            LevelDto level = growthService.getUserLevel(userId);

            result.put("percentile", percentile);
            result.put("level", level);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return exceptionHandling(e);
        }
    }

    @GetMapping("/exp/list/{userId}")
    public ResponseEntity<?> listExp(@PathVariable int userId){

        return null;
    }

    @GetMapping("/exp/{fromId}/{toId}")
    public ResponseEntity<?> showDetail(@PathVariable int fromId, @PathVariable int toId){

        return null;
    }

    @GetMapping("/indicator/{userId}/{type}")
    public ResponseEntity<?> showGraph(@PathVariable int userId, @PathVariable int type){

        return null;
    }
}
