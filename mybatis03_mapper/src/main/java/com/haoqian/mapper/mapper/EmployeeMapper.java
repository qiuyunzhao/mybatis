package com.haoqian.mapper.mapper;


import com.haoqian.mapper.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeMapper {

    // 配置文件方式绑定sql语句
    public Employee getEmpById(Integer id);

    public Employee getEmpByIdAndLastName(@Param("e_id") Integer id, @Param("e_name") String lastName);

    public Employee getEmpByMap(Map<String, Object> map);

    public Employee getEmpByPOJO(Employee employee);

    public Employee getEmpByList(List<Integer> ids);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public List<Employee> getEmpsByDeptId(Integer deptId);

    //返回一条记录封装成的map；key就是列名，值就是对应的值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    @MapKey("lastName") // @MapKey:告诉mybatis封装这个map的时候使用哪个JavaBean属性作为map的key
    public Map<String, Employee> getEmpsReturnMap(String lastName);

    public int insertEmployee(Employee employee);

    public int updateEmp(Employee employee);

    public int deleteEmpById(Integer id);

}