package com.haoqian.mybatis07_plugin;

import com.haoqian.mybatis07_plugin.domain.Employee;
import com.haoqian.mybatis07_plugin.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Mybatis07PluginApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 测试插件
     */
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test01() throws IOException {
        // 获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 创建代理对象，执行查询方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            // 关闭sqlSession对象
            openSession.close();
        }
    }
}
