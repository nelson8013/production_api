package com.nelson.production_api.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelson.production_api.Interfaces.LocationServiceInterface;
import com.nelson.production_api.Model.Location;
import com.nelson.production_api.Repositories.LocationRepository;

public class LocationService implements LocationServiceInterface{

 @Autowired
 private LocationRepository locationRepository;


 @Override
 public Location createLocation(String location) {
  return null;
 }
 
}
