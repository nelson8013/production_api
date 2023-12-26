package com.nelson.production_api.Requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
 
  private String firstName;
  private String lastName;
  private Long   age;
  private List<String> location;
  private String email;
  private String department;
}
