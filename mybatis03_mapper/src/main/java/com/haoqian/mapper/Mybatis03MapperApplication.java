package com.haoqian.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan
 * mybatis的注解，可以标注在mybatis配置类上或者springboot主启动类上
 * 告诉springboot扫描哪些mybatis的maper接口
 * 也可以使用 @Maper 注解分别为每个mybatis的maper接口进行标注
 * mybatis会为这些接口生成代理对象（不设置会导致spring找不到mapper的对象而无法启动项目）
 * 注解版和配置版都需要
 */
@MapperScan(value = "com.haoqian.mapper.mapper")
@SpringBootApplication
public class Mybatis03MapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis03MapperApplication.class, args);
    }

}
