package com.haoqian.dynamic.mapper;

import com.haoqian.dynamic.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsWithChoose(Employee employee);

    public void updateWithSet(Employee employee);

    public List<Employee> getEmpsWithForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsInnerParameter(Employee employee);

    public List<Employee> getEmpsBind(String lastName);

    public Employee getEmpSqlInclude(int id);

}