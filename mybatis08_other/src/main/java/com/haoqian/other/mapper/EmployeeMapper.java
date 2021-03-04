package com.haoqian.other.mapper;

import com.haoqian.other.domain.Employee;
import com.haoqian.other.domain.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public List<Employee> getEmps();

    public Long addEmp(Employee employee);

    // 调用存储过程
    public void callProcedure(Procedure procedure);

}