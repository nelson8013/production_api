package com.nelson.production_api.Interfaces;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.nelson.production_api.Model.Employee;

public interface EmployeeServiceInterface {
 List<Employee> employees();
 List<Employee> employees(int pageNumber, int pageSize);
 Employee employee(Long id);
 Employee createEmployee(Employee employee);
 Employee updateEmployee(Employee employee);
 void deleteEmployee(Long id);

 List<Employee> getEmployeesByFirstName(String first_name);
 List<Employee> getEmployeesByLastName(String last_name);

 List<Employee> getEmployeeByFirstNameAndLocation(String first_name, String location);
 List<Employee> getEmployeeFirstNameByKeyword(String first_name);
 List<Employee> getEmployeeLastNameByKeyword(String last_name);
 List<Employee> getEmployeeEmailByKeyword(String email);
}
 