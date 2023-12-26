package com.nelson.production_api.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import com.nelson.production_api.Exceptions.EmployeeAlreadyExistsException;
import com.nelson.production_api.Model.Department;
import com.nelson.production_api.Model.Employee;
import com.nelson.production_api.Model.Location;
import com.nelson.production_api.Repositories.DepartmentRepository;
import com.nelson.production_api.Repositories.LocationRepository;
import com.nelson.production_api.Requests.EmployeeRequest;
import com.nelson.production_api.Services.EmployeeService;

@RestController
public class EmployeeController {

   
   @Autowired
   private EmployeeService employeeService;

   @Autowired
   private DepartmentRepository departmentRepository;

   @Autowired
 private LocationRepository locationRepository;

   @GetMapping("/employees")
   public ResponseEntity<List<Employee>> employees(){
    List<Employee> employees = employeeService.employees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   @GetMapping("/paginated_employees")
   public ResponseEntity<Page<Employee>> employees(@RequestParam int pageNumber, @RequestParam int pageSize){
    Page<Employee> employees = employeeService.employees(pageNumber, pageSize);
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("X-Total-Count", String.valueOf(employees.getTotalElements()));
    return ResponseEntity.ok().headers(responseHeaders).body(employees);
   }

   @GetMapping("/employee")
   public Employee employee(@RequestParam Long id){
    return employeeService.employee(id);
   }

   @PostMapping("/employees")
   public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest request){
        try{
            Department existingDepartment = departmentRepository.findByName(request.getDepartment());
            Department department;
            List<String> locations = request.getLocation();

            if (existingDepartment != null) {
                department = existingDepartment;
            }else{
                department = new Department();
                department.setName(request.getDepartment());
                department = departmentRepository.save(department);
            }

            Employee employee = new Employee(request);
            employee = employeeService.createEmployee(employee);
            employee.setDepartment(department);

            for(String employee_location : locations){
                Location location = new Location();
                location.setName(employee_location);
                location.setEmployee(employee);

                locationRepository.save(location);
            }
            
            return new ResponseEntity<>(employee, HttpStatus.CREATED);

        }catch(EmployeeAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
   }

   @PutMapping("/employee")
   public ResponseEntity<Employee> updateEmployee(@RequestParam Long id, @RequestBody Employee employee){
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


    /* @GetMapping("/employees/filterByFirstNameAndLocation")
    public ResponseEntity< List<Employee>> employeesByFirstNameAndLocation(@RequestParam String first_name, @RequestParam String location){
        List<Employee> employeeNames = employeeService.getEmployeeByFirstNameAndLocation(first_name, location);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    } */

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
   
   
    /* @GetMapping("/employees/{first_name}/{location}")
    public ResponseEntity< List<Employee>> employeesByFirstNameOrLocation(@PathVariable String first_name, @PathVariable String location){
        List<Employee> employeeNames = employeeService.getEmployeeByFirstNameOrLocation(first_name, location);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    } */
   
    @DeleteMapping("/employees/delete/{first_name}")
    public ResponseEntity<String> deleteEmployeeByFirstName(@PathVariable String first_name){
        Integer deletedEmployee = employeeService.deleteByEmployeeFirstName(first_name);
        return new ResponseEntity<>(deletedEmployee  + " record(s) deleted", HttpStatus.OK);
    }
    
    @GetMapping("/employees/filter/{name}")
    public ResponseEntity< List<Employee>> employeesByDepartment(@PathVariable String name){
        List<Employee> employeeNames =employeeService.getEmployeesByDepartment(name);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    }

    @GetMapping("/employees/filter_two/{name}")
    public ResponseEntity< List<Employee>> employeesByDepartmentTwo(@PathVariable String name){
        List<Employee> employeeNames =employeeService.getEmployeesByDepartmentTwo(name);
        return new ResponseEntity<>(employeeNames, HttpStatus.OK);
    }
 
}
