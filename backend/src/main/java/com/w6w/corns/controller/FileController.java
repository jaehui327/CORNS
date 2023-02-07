package com.w6w.corns.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/test")
@ApiOperation("file")
public class FileController {

    @Value("{upload.path.upload-im}")
    private String uploadPath;

    @PostMapping
    public ResponseEntity sendFile(int userId, @RequestPart MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();

        String saveUrl = "/home/ubuntu/uploads/"+userId+"/"+originalFileName;
        System.out.println("saveUrl = " + saveUrl);

        File dest = new File(saveUrl);

//        File dest = new File("/home/ubuntu/uploads/"+userId+"/"+originalFileName);

        file.transferTo(dest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
