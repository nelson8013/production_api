package com.nelson.production_api.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelson.production_api.Exceptions.EmployeeAlreadyExistsException;
import com.nelson.production_api.Exceptions.EmployeeNotFoundException;
import com.nelson.production_api.Interfaces.EmployeeServiceInterface;
import com.nelson.production_api.Model.Employee;
import com.nelson.production_api.Repositories.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;
  
    @Override
    public List<Employee> employees() {
      return employeeRepository.findAll();
    }

    @Override
    public List<Employee> employees(int pageNumber, int pageSize) {
      Sort sort = Sort.by(Sort.Direction.DESC, "id");
      Pageable pages = PageRequest.of(pageNumber, pageSize, sort);
      return employeeRepository.findAll(pages).getContent();
  }

    @Override
    public Employee employee(Long id) {
    Optional<Employee> employee = employeeRepository.findById(id);
    if(employee.isPresent()){
      return employee.get();
    }
    throw new EmployeeNotFoundException("There's no Employee with an id of " + id);
	}

    @Override
    public Employee createEmployee(Employee employee) {
    if(!employeeRepository.existsByEmail(employee.getEmail())){
      return employeeRepository.save(employee);
    }
    throw new EmployeeAlreadyExistsException("An Employee with that email already exists");
  }

    @Override
    public Employee updateEmployee(Employee employee) {
  return employeeRepository.save(employee);
	}

    @Override
    public void deleteEmployee(Long id) {
      employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String first_name) {
    return employeeRepository.findByFirstName(first_name);
  }

    @Override
    public List<Employee> getEmployeesByLastName(String last_name) {
    return employeeRepository.findByLastName(last_name);
  }

    @Override
    public List<Employee> getEmployeeByFirstNameAndLocation(String first_name, String location) {
    return employeeRepository.findByFirstNameAndLocation(first_name,location);
  }

		@Override
		public List<Employee> getEmployeeFirstNameByKeyword(String first_name) {
      Sort sort = Sort.by(Sort.Direction.DESC, "id");
			return employeeRepository.findByFirstNameContaining(first_name, sort);
		}

		@Override
		public List<Employee> getEmployeeLastNameByKeyword(String last_name) {
      Sort sort = Sort.by(Sort.Direction.DESC, "id");
			return employeeRepository.findByLastNameContaining(last_name, sort);
		}

		@Override
		public List<Employee> getEmployeeEmailByKeyword(String email) {
      Sort sort = Sort.by(Sort.Direction.DESC, "id");
			return employeeRepository.findByEmailContaining(email,sort);
		}

  
 
}