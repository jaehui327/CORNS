package com.w6w.corns.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/test")
@ApiOperation("file")
public class FileController {

    @PostMapping
    public ResponseEntity sendFile(int userId, @RequestParam MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();

        String saveUrl = "/home/ubuntu/uploads/"+userId+"/"+originalFileName;
        System.out.println("saveUrl = " + saveUrl);

        String path = System.getProperty("user.dir")+File.separator+"/home/ubuntu/uploads/";
        System.out.println("path = " + path);

        File dest = new File(path);
        
        if(!dest.exists()){
            if(dest.mkdir()) System.out.println("폴더 생성 성공");
            else System.out.println("실패");
        }else System.out.println("이미존재");
 
    
    //        File dest = new File("/home/ubuntu/uploads/"+userId+"/"+originalFileName);

        file.transferTo(dest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
