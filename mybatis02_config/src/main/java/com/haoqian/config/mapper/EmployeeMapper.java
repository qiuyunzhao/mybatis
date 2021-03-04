package com.haoqian.config.mapper;

import com.haoqian.config.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {

    // 配置文件方式绑定sql语句
    public Employee getEmpById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(value = "INSERT INTO tb_employee(last_name,gender,email) VALUES(#{lastName},#{gender},#{email})",databaseId = "mysql")
    @Insert(value = "INSERT INTO tb_employee(last_name,gender) VALUES(#{lastName},#{gender})",databaseId = "oracle")
    public int insertEmployee(Employee employee);

    @Update("UPDATE tb_employee SET last_name=#{lastName},gender=#{gender},email=#{email} WHERE id=#{id}")
    public int updateEmp(Employee employee);

    @Delete(value = "DELETE FROM tb_employee WHERE id=#{id}")
    public int deleteEmpById(Integer id);

}