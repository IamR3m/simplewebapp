package com.alexkasko.simplewebapp.mapper;

import com.alexkasko.simplewebapp.dto.Employee;
import com.alexkasko.simplewebapp.dto.Gender;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow (ResultSet resultSet, int arg1) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getLong("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setDepartmentId(resultSet.getLong("department_id"));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setGender(Gender.valueOf(resultSet.getString("gender")));
        employee.setDateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth"),
                DateTimeFormatter.ISO_LOCAL_DATE));
        return employee;
    }
}