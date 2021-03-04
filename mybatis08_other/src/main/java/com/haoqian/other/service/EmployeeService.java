package com.haoqian.other.service;

import com.haoqian.other.domain.Employee;
import com.haoqian.other.mapper.EmployeeMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {


    @Autowired
    EmployeeMapper employeeMapper;

    public void insertEmps() {
        long start = System.currentTimeMillis(); // 开始时间
        for (int i = 0; i < 100; i++) {
            employeeMapper.addEmp(new Employee(
                    null,
                    UUID.randomUUID().toString().substring(0, 5),
                    UUID.randomUUID().toString().substring(0, 5) + "@qq.com",
                    "1"));
        }
        long end = System.currentTimeMillis(); // 结束时间
        System.out.println("执行时长：" + (end - start));
    }

}
