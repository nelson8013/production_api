package com.nelson.production_api.Exceptions;

public class EmployeeNotFoundException extends RuntimeException{
 
 public EmployeeNotFoundException(String message){
  super(message);
 }

 public EmployeeNotFoundException(String message, Throwable cause){
  super(message, cause);
 }
}
