package com.example.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://objectstorageapi.hzh.sealos.run")
                .credentials("jockt2wx", "g9cndz8zl5m8hwv5")
                .build();
    }
}