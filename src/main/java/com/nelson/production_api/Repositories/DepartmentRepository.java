package com.nelson.production_api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelson.production_api.Model.Department;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long>{
  Department findByName(String name);
}
