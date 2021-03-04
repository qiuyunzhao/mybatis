package com.haoqian.helloworld.controller;

import com.haoqian.helloworld.domain.Employee;
import com.haoqian.helloworld.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id) {
        return employeeService.getEmpById(id);
    }

    @PostMapping("/emp")
    public Employee insertEmployee(@RequestBody Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    @PutMapping("/emp")
    public Employee updateEmp(@RequestBody Employee employee) {
        return employeeService.updateEmp(employee);
    }

    @DeleteMapping("/emp/{id}")
    public int deleteEmpById(@PathVariable("id") Integer id) {
        return employeeService.deleteEmpById(id);
    }
}
