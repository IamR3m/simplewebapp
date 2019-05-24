package com.alexkasko.simplewebapp.dao;

import com.alexkasko.simplewebapp.dto.Employee;
import com.alexkasko.simplewebapp.mapper.EmployeeRowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDao {

    private NamedParameterJdbcTemplate template;

    public EmployeeDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public List<Employee> getAll() {
        return template.query("select * from employee", new EmployeeRowMapper());
    }

    public void insertEmployee (Employee employee) {
        final String sql = "insert into employee(employee_id,first_name,last_name,department_id,job_title,gender,date_of_birth) values(:employee_id,:first_name,:last_name,:department_id,:job_title,:gender,:date_of_birth)";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("employee_id",employee.getEmployeeId())
                .addValue("first_name",employee.getFirstName())
                .addValue("last_name",employee.getLastName())
                .addValue("department_id",employee.getDepartmentId())
                .addValue("job_title",employee.getJobTitle())
                .addValue("gender",employee.getGender().toString())
                .addValue("date_of_birth",employee.getDateOfBirth());
        template.update(sql,parameterSource, new GeneratedKeyHolder());
    }

    public void updateEmployee (Employee employee) {
        final String sql = "update employee set employee_id=:employee_id, first_name=:first_name, last_name=:last_name, department_id=:department_id, job_title=:job_title, gender=:gender, date_of_birth=:date_of_birth where employee_id=:employee_id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("employee_id", employee.getEmployeeId())
                .addValue("first_name",employee.getFirstName())
                .addValue("last_name",employee.getLastName())
                .addValue("department_id",employee.getDepartmentId())
                .addValue("job_title", employee.getJobTitle())
                .addValue("gender",employee.getGender().toString())
                .addValue("date_of_birth",employee.getDateOfBirth());
        template.update(sql,parameterSource,new GeneratedKeyHolder());
    }

    public void executeUpdateEmployee (Employee employee) {
        final String sql = "update employee set employee_id=:employee_id, first_name=:first_name, last_name=:last_name, department_id=:department_id, job_title=:job_title, gender=:gender, date_of_birth=:date_of_birth where employee_id=:employee_id";

        Map<String,Object> map = new HashMap<>();
        map.put("employee_id",employee.getEmployeeId());
        map.put("first_name",employee.getFirstName());
        map.put("last_name",employee.getLastName());
        map.put("department_id",employee.getDepartmentId());
        map.put("job_title",employee.getJobTitle());
        map.put("gender",employee.getGender().toString());
        map.put("date_of_birth",employee.getDateOfBirth());

//        template.execute(sql, map, new PreparedStatementCallback<Object>() {
//            @Override
//            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
//                return preparedStatement.executeUpdate();
//            }
//        });
        template.execute(sql,map,PreparedStatement::executeUpdate);
    }

    public void deleteEmployee (Employee employee) {
        final String sql = "delete from employee where employee_id=:employee_id";

        Map<String,Object>  map = new HashMap<>();
        map.put("employee_id",employee.getEmployeeId());

//        template.execute(sql, map, new PreparedStatementCallback<Object>() {
//            @Override
//            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
//                return preparedStatement.executeUpdate();
//            }
//        });
        template.execute(sql,map,PreparedStatement::executeUpdate);
    }
}