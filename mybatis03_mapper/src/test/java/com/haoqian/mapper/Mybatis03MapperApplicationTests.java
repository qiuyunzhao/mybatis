package com.haoqian.mapper;

import com.haoqian.mapper.domain.Department;
import com.haoqian.mapper.domain.Employee;
import com.haoqian.mapper.mapper.DepartmentMapper;
import com.haoqian.mapper.mapper.EmployeeMapper;
import com.haoqian.mapper.mapper.EmployeeMapper2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatis03MapperApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 测试基本增删改
     * 1、mybatis允许增删改直接定义以下类型返回值
     * Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * sqlSessionFactory.openSession();===》手动提交
     * sqlSessionFactory.openSession(true);===》自动提交
     */

    @Test
    public void test01() throws IOException {
        //1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            //测试添加
//            Employee employee = new Employee(null, "jerry4", null, "1");
//            mapper.insertEmployee(employee);
//            System.out.println(employee.getId());

            //测试修改
//            Employee employee = new Employee(11, "Tom", "0", "jerry@atguigu.com");
//            int updateEmp = mapper.updateEmp(employee);
//            System.out.println(updateEmp);

            //测试删除
//            mapper.deleteEmpById(10);

            // 测试查询
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);

            //2、手动提交数据
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试参数处理
     */
    @Test
    public void test02() throws IOException {
        //1、获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

//            // 多个参数
//            Employee employee = mapper.getEmpByIdAndLastName(1, "小明");
//            System.out.println(employee);

            // 入参是map
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "小明");
            map.put("tableName", "tb_employee");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);

            // 入参是pojo
//            Employee employee = new Employee(1, "小明", null, "0");
//            Employee res = mapper.getEmpByPOJO(employee);
//            System.out.println(employee);

            // 入参是List
//            List<Integer> l = new ArrayList<>();
//            l.add(1);
//            l.add(2);
//            Employee employee = mapper.getEmpByList(l);
//            System.out.println(employee);


            //2、手动提交数据
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试select返回类型
     */
    @Test
    public void test03() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession();//1、获取到的SqlSession不会自动提交数据
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            // 查询多条记录返回列表
//            List<Employee> emps = mapper.getEmpsByLastNameLike("%小%");
//            System.out.println(emps);

            // 查询一条记录封装成map
//            Map<String, Object> empMap = mapper.getEmpByIdReturnMap(1);
//            System.out.println(empMap);

            // 查询多条记录封装成map
            Map<String, Employee> empsReturnMap = mapper.getEmpsReturnMap("%小%");
            System.out.println(empsReturnMap);

            openSession.commit(); //2、手动提交数据
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试resultMap自定义查询映射规则与对象关联查询
     */
    @Test
    public void test04() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession();//1、获取到的SqlSession不会自动提交数据
        try {
            EmployeeMapper2 mapper = openSession.getMapper(EmployeeMapper2.class);

            // 自定义结果集映射规则
//            Employee emp = mapper.getEmpById(1);
//            System.out.println(emp);

            // 对象关联查询：级联属性封装结果集
//            Employee empAndDept1 = mapper.getEmpAndDept1(1);
//            System.out.println(empAndDept1);

            // 对象关联查询：使用<association>标签定义关联的单个对象的封装规则
//            Employee empAndDept2 = mapper.getEmpAndDept2(1);
//            System.out.println(empAndDept2);

            // 对象关联查询：使用分步查询实现(支持子查询延时加载)
//            Employee empByIdStep = mapper.getEmpByIdStep(2);
//            System.out.println(empByIdStep.getLastName());

            // 鉴别器<discriminator>
            Employee empDiscriminator = mapper.getEmpDiscriminator(1);
            System.out.println(empDiscriminator);

            openSession.commit(); //2、手动提交数据
        } finally {
            openSession.close();
        }
    }

    /**
     * 测试resultMap自定义查询映射规则与集合关联查询
     */
    @Test
    public void test05() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession();//1、获取到的SqlSession不会自动提交数据
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);

            // 集合关关联查询
//            Department deptAndEmpsByDid = mapper.getDeptAndEmpsByDid(101);
//            System.out.println(deptAndEmpsByDid);

            // 集合关分步关联查询
            Department deptAndEmpsByDidStep = mapper.getDeptAndEmpsByDidStep(101);
            System.out.println(deptAndEmpsByDidStep);


            openSession.commit(); //2、手动提交数据
        } finally {
            openSession.close();
        }
    }

}
