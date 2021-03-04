package com.haoqian.mybatis07_plugin.mapper;

import com.haoqian.mybatis07_plugin.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}