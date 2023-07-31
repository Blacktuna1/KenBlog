package com.kenblog;

import org.mapstruct.MapperConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kenblog.ken.mapper")
public class KenBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(KenBlogApplication.class,args);
    }
}
