package com.nelson.production_api.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
 
  private String firstName;
  private String lastName;
  private Long   age;
  private String location;
  private String email;
  private String department;
}
