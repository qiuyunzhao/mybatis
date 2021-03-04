package com.haoqian.config;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class Mybatis02ConfigApplicationTests {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    void contextLoads() {
    }

}
