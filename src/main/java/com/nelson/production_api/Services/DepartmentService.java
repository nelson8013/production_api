package com.nelson.production_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import java.util.ArrayList;
import com.nelson.production_api.Interfaces.DepartmentServiceInterface;
import com.nelson.production_api.Model.Department;
import com.nelson.production_api.Repositories.DepartmentRepository;
import com.nelson.production_api.Responses.DepartmentResponse;


@Service
@Transactional
public class DepartmentService implements DepartmentServiceInterface {

 @Autowired
 private DepartmentRepository departmentRepository;


 @Override
 public List<DepartmentResponse> getDepartments() {
  List<Department> departments = departmentRepository.findAll();

  List<DepartmentResponse> list = new ArrayList<>();

  departments.forEach(department -> {
   DepartmentResponse departmentResponse = new DepartmentResponse();
     departmentResponse.setName(department.getName());
     departmentResponse.setId(department.getId());
     departmentResponse.setEmployeeName( department.getEmployee().getFirstName() + " " + department.getEmployee().getLastName());
     list.add(departmentResponse);
  });
 
  return list;
 }
 
}
