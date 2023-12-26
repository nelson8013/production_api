package com.nelson.production_api.Interfaces;

import com.nelson.production_api.Responses.DepartmentResponse;

import java.util.List;

public interface DepartmentServiceInterface {
 List<DepartmentResponse> getDepartments();
}
