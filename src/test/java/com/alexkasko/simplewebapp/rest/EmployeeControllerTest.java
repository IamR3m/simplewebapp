package com.alexkasko.simplewebapp.rest;

import com.alexkasko.simplewebapp.dto.Employee;
import com.alexkasko.simplewebapp.dto.Gender;
import com.google.common.collect.ImmutableList;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void test1GetAll() {

        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/simplewebapp/employeeList", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> actualList = responseEntity.getBody();

        assert actualList != null;
        assertThat(actualList.size(), is(2));
        List<Long> actualIds = actualList.stream().map(Employee::getEmployeeId)
                .collect(Collectors.collectingAndThen(toList(), ImmutableList::copyOf));
        assertThat(actualIds, containsInAnyOrder(1L,2L));
    }

    @Test
    public void test2CreateEmployee() {

        Employee employee = new Employee(3L,"Bruce","Wayne",3L,
                "Security Manager", Gender.MALE, LocalDate.of(1939,3,30));

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:8080/simplewebapp/createEmp", employee, String.class);

        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void test3ExecuteUpdateEmployee() {

        Employee employee = new Employee(3L,"Bruce","Wayne",3L,
                "Security Manager Expert", Gender.MALE, LocalDate.of(1939,3,30));

        HttpEntity<Employee> requestEntity = new HttpEntity<>(employee);
        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:8080/simplewebapp/executeUpdateEmp", HttpMethod.PUT,
                        requestEntity, String.class);

        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void test4DeleteEmployee() {

        Employee employee = new Employee();
        employee.setEmployeeId(3L);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://localhost:8080/simplewebapp/deleteEmpById", HttpMethod.DELETE,
                new HttpEntity<>(employee), String.class);

        assertThat(responseEntity.getStatusCodeValue(), is(200));
    }

}
