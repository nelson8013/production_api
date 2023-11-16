package com.nelson.production_api.Repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelson.production_api.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
 List<Employee> findByFirstName(String first_name);
 List<Employee> findByLastName(String last_name);
 List<Employee> findByFirstNameAndLocation(String first_name, String location);
 boolean existsByEmail(String email);
 List<Employee> findByFirstNameContaining(String keyword, Sort sort);
 List<Employee> findByLastNameContaining(String keyword, Sort sort);
 List<Employee> findByEmailContaining(String keyword, Sort sort);
}
