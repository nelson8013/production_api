package com.nelson.production_api.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nelson.production_api.Requests.EmployeeRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class Employee {

 @Id
 @GeneratedValue( strategy = GenerationType.IDENTITY)
 private Long id;

 @NotEmpty( message = "First Name cannot be null")
 private String firstName;

 @NotEmpty( message = "Last Name cannot be null")
 private String lastName;

 private Long age = 0L;

 @NotBlank( message = "Location cannot be null")
 private String location;

 @Email( message = "Please enter a valid email address. Are you mad?")
 @NotBlank( message = "Email cannot be null")
 @Column( unique = true)
 private String email;

 @JoinColumn(name = "department_id")
 @OneToOne
 private Department department;

 @CreationTimestamp
 @Column(name = "created_at", nullable = false, updatable = false)
 private Date createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private Date updatedAt;


 public Employee(EmployeeRequest request){
   this.firstName = request.getFirstName();
   this.lastName  = request.getLastName();
   this.age       = request.getAge();
   this.location  = request.getLocation();
   this.email     = request.getEmail();
 }
 
}
