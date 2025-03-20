package com.example.controller;

import com.example.common.Result;
import com.example.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private MinioUtils minioUtils;
    
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = minioUtils.uploadFile(file);
        return Result.success(url);
    }
}