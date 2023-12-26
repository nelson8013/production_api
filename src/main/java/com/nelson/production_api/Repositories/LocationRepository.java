package com.nelson.production_api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelson.production_api.Model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
 
}
