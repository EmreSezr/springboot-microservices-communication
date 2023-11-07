package com.emresezer.employeeservice.service.impl;

import com.emresezer.employeeservice.dto.APIResponseDto;
import com.emresezer.employeeservice.dto.DepartmentDto;
import com.emresezer.employeeservice.dto.EmployeeDto;
import com.emresezer.employeeservice.entity.Employee;
import com.emresezer.employeeservice.exception.ResourceNotFoundException;
import com.emresezer.employeeservice.mapper.AutoEmployeeMapper;
import com.emresezer.employeeservice.repository.EmployeeRepository;
import com.emresezer.employeeservice.service.APIClient;
import com.emresezer.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    //    private RestTemplate restTemplate;

    /**
     * private WebClient webClient;
     */
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee =
                AutoEmployeeMapper
                        .MAPPER
                        .mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto =
                AutoEmployeeMapper
                        .MAPPER
                        .mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

        /**   DepartmentDto departmentDto = webClient
         .get()
         .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
         .retrieve()
         .bodyToMono(DepartmentDto.class)
         .block();
         */

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto =
                AutoEmployeeMapper
                        .MAPPER
                        .mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
