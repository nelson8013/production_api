package com.nelson.production_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.nelson.production_api.Services.DepartmentService;
import com.nelson.production_api.Responses.DepartmentResponse;

@RestController
public class DepartmentController {

 @Autowired
 DepartmentService departmentService;

 @GetMapping("/departments")
 public List<DepartmentResponse> departments(){
  return departmentService.getDepartments();
 }
 
}
