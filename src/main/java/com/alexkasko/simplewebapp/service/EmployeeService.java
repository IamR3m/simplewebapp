package com.alexkasko.simplewebapp.service;

import com.alexkasko.simplewebapp.dao.EmployeeDao;
import com.alexkasko.simplewebapp.dto.Employee;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EmployeeService {
    @Resource
    EmployeeDao employeeDao;

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public void insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void executeUpdateEmployee(Employee employee) {
        employeeDao.executeUpdateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.deleteEmployee(employee);
    }
}