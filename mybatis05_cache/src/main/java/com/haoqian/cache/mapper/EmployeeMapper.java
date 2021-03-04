package com.haoqian.cache.mapper;


import com.haoqian.cache.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public Long addEmp(Employee employee);
}