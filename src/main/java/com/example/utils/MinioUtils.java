package com.example.utils;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class MinioUtils {
    @Autowired
    private MinioClient minioClient;
    
    private static final String BUCKET_NAME = "jockt2wx-zxyf";
    
    public String uploadFile(MultipartFile file) {
        try {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            
            // 上传文件
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            
            // 返回文件访问路径
            return "http://objectstorageapi.hzh.sealos.run/" + BUCKET_NAME + "/" + objectName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
}