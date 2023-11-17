package com.nelson.production_api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "department")
public class Department {
 
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
}
