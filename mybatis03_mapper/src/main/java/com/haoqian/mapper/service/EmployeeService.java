package com.haoqian.mapper.service;


import com.haoqian.mapper.domain.Employee;
import com.haoqian.mapper.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    public Employee insertEmployee(Employee employee) {
        int num = employeeMapper.insertEmployee(employee);
        System.out.println("受影响的行数：" + num);
        return employee;
    }

    public Employee updateEmp(Employee employee) {
        int num = employeeMapper.updateEmp(employee);
        System.out.println("受影响的行数：" + num);
        return employee;
    }

    public int deleteEmpById(Integer id) {
        int num = employeeMapper.deleteEmpById(id);
        System.out.println("受影响的行数：" + num);
        return num;
    }
}