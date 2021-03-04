package com.haoqian.dynamic;

import com.haoqian.dynamic.domain.Department;
import com.haoqian.dynamic.domain.Employee;
import com.haoqian.dynamic.mapper.EmployeeMapperDynamicSQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Mybatis04DynamicsqlApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 测试动态sql
     */
    @Test
    public void test01() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession();//1、获取到的SqlSession不会自动提交数据
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);

            // 测试if和where
//            Department dept = new Department(101, "科技部", null);
//            Employee employee = new Employee(null, "%小%", "", "1", dept);
//            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
//            System.out.println(emps);

            // 测试 trim
//            Employee employee = new Employee(null, "%小%", "", "0", null);
//            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
//            System.out.println(emps);

            // 测试 choose
//            Employee employee = new Employee(null, "", "", "0", null);
//            List<Employee> emps = mapper.getEmpsWithChoose(employee);
//            System.out.println(emps);

            // 测试 set
//            Employee employee = new Employee(1, "小明", null, null, null);
//            mapper.updateWithSet(employee);

            // 测试foreach批量查询
//            List<Employee> emps = mapper.getEmpsWithForeach(Arrays.asList(1, 2, 3));
//            System.out.println(emps);

            // 测试foreach，mysql批量查询
//            List<Employee> emps = new ArrayList<>();
//            emps.add(new Employee(null, "smith0x1", "smith0x1@qq.com", "1",
//                    new Department(101, null, null)));
//            emps.add(new Employee(null, "allen0x1", "allen0x1@qq.com", "0",
//                    new Department(102, null, null)));
//            mapper.addEmps(emps);

            // 测试内置变量 _databaseId、_parameter
//            Employee employee = new Employee(null, "%明%", null, null, null);
//            List<Employee> emps = mapper.getEmpsInnerParameter(employee);
//            System.out.println(emps);

            // 测试<bind>
//            List<Employee> emps = mapper.getEmpsBind("小");
//            System.out.println(emps);

            // 测试<sql>、<include>
            Employee emp = mapper.getEmpSqlInclude(2);
            System.out.println(emp);

            openSession.commit(); //2、手动提交数据
        } finally {
            openSession.close();
        }
    }

}
