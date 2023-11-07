package com.emresezer.employeeservice.service;

import com.emresezer.employeeservice.dto.APIResponseDto;
import com.emresezer.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}
