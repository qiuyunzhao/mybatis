package com.haoqian.other;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.haoqian.other.mapper")
public class Mybatis08OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis08OtherApplication.class, args);
    }

}
