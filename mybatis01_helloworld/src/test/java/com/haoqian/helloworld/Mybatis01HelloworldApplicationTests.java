package com.haoqian.helloworld;

import com.haoqian.helloworld.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Mybatis01HelloworldApplicationTests {


    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;

    @Test
    public void testDruid() throws SQLException {
        // 数据源
        System.out.println(dataSource.getClass());
        // 从数据源获取连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    // ============================================ mybatis原始编码操作 ==================================================
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMybatis() {
        SqlSession session = sqlSessionFactory.openSession();
        Employee employee = (Employee) session.selectOne("com.haoqian.helloworld.mapper.EmployeeMapper.getEmpById", 1);
        System.out.println(employee);
    }

}
