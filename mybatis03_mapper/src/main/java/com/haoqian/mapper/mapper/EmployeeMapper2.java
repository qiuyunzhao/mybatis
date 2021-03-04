package com.haoqian.mapper.mapper;


import com.haoqian.mapper.domain.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeMapper2 {

    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept1(Integer id);

    public Employee getEmpAndDept2(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public Employee getEmpDiscriminator(Integer id);
}