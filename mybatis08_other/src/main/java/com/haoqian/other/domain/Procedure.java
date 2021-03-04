package com.haoqian.other.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Procedure {
    private Integer did;      // 部门id
    private Integer empCount; // 员工数量
}
