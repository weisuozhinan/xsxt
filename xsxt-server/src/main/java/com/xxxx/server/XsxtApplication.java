package com.xxxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")
public class XsxtApplication {
    public static void main(String[] args) {
        SpringApplication.run(XsxtApplication.class,args);
    }
}
