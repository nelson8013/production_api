package com.nelson.production_api.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nelson.production_api.Model.Employee;
import com.nelson.production_api.Services.EmployeeService;

@RestController
public class EmployeeController {

   
   @Autowired
   private EmployeeService employeeService;

   @GetMapping("/employees")
   public ResponseEntity<List<Employee>> employees(){
    List<Employee> employees = employeeService.employees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   @GetMapping("/paginated_employees")
   public ResponseEntity<List<Employee>> employees(@RequestParam int pageNumber, @RequestParam int pageSize){
    List<Employee> employees = employeeService.employees(pageNumber, pageSize);
    return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   @GetMapping("/employee/{id}")
   public Employee employee(@PathVariable Long id){
    return employeeService.employee(id);
   }

   @PostMapping("/employees")
   public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
   }

   @PutMapping("/employee/{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        employee.setId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
   }

    @DeleteMapping("/employee")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filterByFirstName")
    public ResponseEntity< List<Employee>> employeesByFirstName(@RequestParam String first_name){
        List<Employee> employeeNames = employeeService.getEmployeesByFirstName(first_name);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    }

    @GetMapping("/employees/filterByLastName")
    public ResponseEntity< List<Employee>> employeesByLastName(@RequestParam String last_name){
        List<Employee> employeeNames = employeeService.getEmployeesByLastName(last_name);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    }


    @GetMapping("/employees/filterByFirstNameAndLocation")
    public ResponseEntity< List<Employee>> employeesByLastName(@RequestParam String first_name, @RequestParam String location){
        List<Employee> employeeNames = employeeService.getEmployeeByFirstNameAndLocation(first_name, location);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    }

    @GetMapping("/employees/filterByFirstNameKeyword")
    public ResponseEntity< List<Employee>> employeesByFirstNameKeyword(@RequestParam String first_name){
        List<Employee> employeeFirstNames = employeeService.getEmployeeFirstNameByKeyword(first_name);
        return new ResponseEntity<>(employeeFirstNames, HttpStatus.OK);
    }

    @GetMapping("/employees/filterByLastNameKeyword")
    public ResponseEntity< List<Employee>> employeesByLastNameKeyword(@RequestParam String last_name){
        List<Employee> employeeLastNames = employeeService.getEmployeeLastNameByKeyword(last_name);
        return new ResponseEntity<>(employeeLastNames, HttpStatus.OK);
    }

    @GetMapping("/employees/filterByEmailKeyword")
    public ResponseEntity< List<Employee>> employeesByEmailKeyword(@RequestParam String email){
        List<Employee> employeeEmails = employeeService.getEmployeeEmailByKeyword(email);
        return new ResponseEntity<>(employeeEmails, HttpStatus.OK);
    }
   
   
   
   


 
}
