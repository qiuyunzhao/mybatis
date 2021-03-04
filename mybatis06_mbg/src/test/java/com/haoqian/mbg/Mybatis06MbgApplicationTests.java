package com.haoqian.mbg;

import com.haoqian.mbg.bean.Employee;
import com.haoqian.mbg.bean.EmployeeExample;
import com.haoqian.mbg.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class Mybatis06MbgApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMyBatis3() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            /**
             * xxxExample就是封装查询条件的
             */
            //1、查询所有
            //List<Employee> emps = mapper.selectByExample(null);

            /**
             * select id, last_name, gender, email, d_id
             * from tb_employee
             * WHERE ( last_name like ? and gender = ? ) or( email like ? )
             */
            //2、查询员工名字中有e字母的，和员工性别是1的
            EmployeeExample example = new EmployeeExample(); //封装员工查询条件的example
            EmployeeExample.Criteria criteria = example.createCriteria(); // Criteria就是拼装查询条件
            criteria.andLastNameLike("%小%");
            criteria.andGenderEqualTo("1");
            EmployeeExample.Criteria criteria2 = example.createCriteria();
            criteria2.andEmailLike("%@qq.com%");
            example.or(criteria2);

            List<Employee> list = mapper.selectByExample(example);
            for (Employee employee : list) {
                System.out.println(employee);
            }

        } finally {
            openSession.close();
        }
    }
}
