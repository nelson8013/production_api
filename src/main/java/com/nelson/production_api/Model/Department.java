package com.nelson.production_api.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

  @JsonIgnore
  @OneToOne( mappedBy = "department")
  private Employee employee;
}
