package com.haoqian.helloworld.domain;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
}
