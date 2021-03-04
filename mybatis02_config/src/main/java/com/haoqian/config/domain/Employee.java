package com.haoqian.config.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("emp")
public class Employee {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
}
