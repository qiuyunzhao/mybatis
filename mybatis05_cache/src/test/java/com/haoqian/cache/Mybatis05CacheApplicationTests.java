package com.haoqian.cache;

import com.haoqian.cache.domain.Department;
import com.haoqian.cache.domain.Employee;
import com.haoqian.cache.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Mybatis05CacheApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 测试缓存
     */
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    // =========================================== 二级缓存 ====================================================
    @Test
    public void test05() throws IOException {
        // 1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper1.getEmpById(2);
            System.out.println(emp01);
//            mapper1.addEmp(new Employee(null, "cache", "cache@qq.com", "0", null));
            // 注意：数据会放在一级缓存中；SqlSession提交或关闭后，一级缓存中的数据才会转移到二级缓存中；
            sqlSession1.commit();
            Employee emp02 = mapper2.getEmpById(2);
            System.out.println(emp02);
            sqlSession2.commit();
            System.out.println(emp01 == emp02);
        } finally {
            // 3、用完要关闭
            sqlSession1.close();
            sqlSession2.close();
        }
    }

    // =========================================== 一级缓存 ====================================================

    /**
     * 手动清除了一级缓存
     */
    @Test
    public void test04() throws IOException {
        // 1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper.getEmpById(2);
            System.out.println(emp01);
            sqlSession.commit();
//            sqlSession.clearCache(); // 清除一级缓存
            Employee emp02 = mapper.getEmpById(2);
            System.out.println(emp02);
            sqlSession.commit();
            System.out.println(emp01 == emp02);
        } finally {
            // 3、用完要关闭
            sqlSession.close();
        }
    }

    /**
     * sqlSession相同，两次查询之间执行了增删改操作会导致一级缓存失效
     */
    @Test
    public void test03() throws IOException {
        // 1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper.getEmpById(2);
            System.out.println(emp01);
            mapper.addEmp(new Employee(null, "cache", "cache@qq.com", "0", null));
            Employee emp02 = mapper.getEmpById(2);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);

            // 2、手动提交数据
            sqlSession.commit();
        } finally {
            // 3、用完要关闭
            sqlSession.close();
        }
    }

    /**
     * 一级缓存是sqlSession级别的缓存
     */
    @Test
    public void test02() throws IOException {
        // 1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper1.getEmpById(2);
            System.out.println(emp01);
            Employee emp02 = mapper2.getEmpById(2);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);

            // 2、手动提交数据
            sqlSession1.commit();
            sqlSession2.commit();
        } finally {
            // 3、用完要关闭
            sqlSession2.close();
            sqlSession2.close();
        }
    }

    /**
     * 一级缓存是一直开启的（无法关闭）
     */
    @Test
    public void test01() throws IOException {
        // 1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper.getEmpById(2);
            System.out.println(emp01);
            Employee emp02 = mapper.getEmpById(2);
            System.out.println(emp02);
            System.out.println(emp01 == emp02);

            // 2、手动提交数据
            sqlSession.commit();
        } finally {
            // 3、用完要关闭
            sqlSession.close();
        }
    }


}
