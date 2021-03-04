package com.haoqian.other;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haoqian.other.domain.Employee;
import com.haoqian.other.domain.Procedure;
import com.haoqian.other.mapper.EmployeeMapper;
import com.haoqian.other.service.EmployeeService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class Mybatis08OtherApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 查询单个
     */
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

    /**
     * 查询所有，没有分页查询
     */
    @Test
    public void test02() throws IOException {
        // 获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 创建代理对象，执行查询方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmps();
            for (Employee employee : emps) {
                System.out.println(employee);
            }
        } finally {
            // 关闭sqlSession对象
            openSession.close();
        }
    }

    /**
     * 使用PageHelper插件，分页查询获取基本信息
     */
    @Test
    public void test03() throws IOException {
        // 获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 创建代理对象，执行查询方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            // 1.Mapper接口方法调用之前调用 startPage(int pageNum, int pageSize)方法设置分页信息即可
            Page<Object> page = PageHelper.startPage(1, 3);

            List<Employee> emps = mapper.getEmps();
            for (Employee employee : emps) {
                System.out.println(employee);
            }

            // 2.分页信息封装在Page对象中
            System.out.println("当前页码：" + page.getPageNum());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("每页的记录数：" + page.getPageSize());
            System.out.println("总页码：" + page.getPages());
        } finally {
            openSession.close();// 关闭sqlSession对象
        }
    }


    /**
     * 使用PageHelper插件，分页查询获取详细信息
     */
    @Test
    public void test04() throws IOException {
        SqlSession openSession = sqlSessionFactory.openSession(); // 获取sqlSession对象
        try {
            // 创建Mapper接口代理对象
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            // 1.Mapper接口方法调用之前调用startPage(int pageNum, int pageSize)方法
            // 设置分页信息(第1页，每页3条)即可
            PageHelper.startPage(15, 10);

            List<Employee> emps = mapper.getEmps();
            for (Employee employee : emps) {
                System.out.println(employee);
            }

            // 2.用PageInfo对结果进行包装，PageInfo包含了非常全面的分页属性
            // 第二个参数：获取连续显示3页信息.用到的页码数，默认是总页数
            PageInfo pageInfo = new PageInfo(emps, 6);
            System.out.println("当前页码：" + pageInfo.getPageNum());
            System.out.println("总记录数：" + pageInfo.getTotal());
            System.out.println("每页的记录数：" + pageInfo.getPageSize());
            System.out.println("总页码：" + pageInfo.getPages());
            System.out.println("是否第一页：" + pageInfo.isIsFirstPage());
            System.out.println("连续显示的页码：");
            int[] nums = pageInfo.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }
        } finally {
            openSession.close();// 关闭sqlSession对象
        }
    }


    /**
     * 批量  ：（预编译sql 一次 ==> 设置参数10000次 ==> 执行一次）
     * 非批量：（预编译sql 10000次 ==> 设置参数10000次 ==> 执行10000次
     * 批量操作执行效率要高很多；
     */
    @Test
    public void testBatch() throws IOException {
        // 创建可以执行批量操作的sqlSession(默认不写是ExecutorType.SIMPLE)
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        long start = System.currentTimeMillis(); // 开始时间
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 10000; i++) {
                mapper.addEmp(new Employee(
                        null,
                        UUID.randomUUID().toString().substring(0, 5),
                        UUID.randomUUID().toString().substring(0, 5) + "@qq.com",
                        "1"));
                openSession.commit();
            }
            long end = System.currentTimeMillis(); // 结束时间
            System.out.println("执行时长：" + (end - start));
        } finally {
            openSession.close();
        }

    }

    /**
     * 测试spring容器中注入支持批量操作的SqlSession(没成功！！！！)
     */
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testSpringBatch() throws IOException {
        employeeService.insertEmps();
    }


    /**
     * 存储过程
     */
    @Test
    public void testProcedure() throws IOException {
        // 获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 创建代理对象，执行查询方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Procedure procedure = new Procedure(101, null);
            mapper.callProcedure(procedure);
            System.out.println(procedure.getEmpCount());
        } finally {
            // 关闭sqlSession对象
            openSession.close();
        }
    }


}
