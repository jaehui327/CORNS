package com.w6w.corns.controller;

import com.w6w.corns.service.word.WordService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("word")
public class WordController {

    private final Logger logger = LoggerFactory.getLogger(WordController.class);

    @Autowired
    WordService wordService;

    @ApiOperation(value = "쫑알 단어 추가", notes = "wordEng, wordKor를 request body에 담아서 요청")
    @PostMapping(value = "/{userId}")
    private ResponseEntity<?> save(@PathVariable int userId) {
        logger.debug("userId: {}", userId);
        Map resultMap = new HashMap<>();
        HttpStatus status;

        try {

            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return new ResponseEntity<Map>(resultMap, status);
    }

}
