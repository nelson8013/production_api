package com.nelson.production_api.Exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeException {
 
   private final String message;
   private final Throwable cause;
   private final HttpStatus status;

   EmployeeException(String message, Throwable cause,HttpStatus status ){
     this.message = message;
     this.cause   = cause;
     this.status  = status;
   }

   public String getMessage() {
    return this.message;
   }


   public Throwable getCause() {
    return this.cause;
   }


   public HttpStatus getStatus() {
    return this.status;
   }


}
