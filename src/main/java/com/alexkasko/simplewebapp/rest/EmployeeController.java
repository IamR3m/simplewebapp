package com.alexkasko.simplewebapp.rest;

import com.alexkasko.simplewebapp.dto.Employee;
import com.alexkasko.simplewebapp.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/simplewebapp")
public class EmployeeController {

    @Resource
    EmployeeService employeeService;

    @GetMapping(value = "/employeeList")
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @PostMapping(value = "/createEmp")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.insertEmployee(employee);
    }

    @PutMapping(value = "/updateEmp")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @PutMapping(value = "/executeUpdateEmp")
    public void executeUpdateEmployee (@RequestBody Employee employee) {
        employeeService.executeUpdateEmployee(employee);
    }

    @DeleteMapping(value = "/deleteEmpById")
    public void deleteEmployee (@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
    }
}