package com.haoqian.mapper.mapper;

import com.haoqian.mapper.domain.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptAndEmpsByDid(Integer did);

    public Department getDeptAndEmpsByDidStep(Integer did);
}
