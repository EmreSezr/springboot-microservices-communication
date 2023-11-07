package com.emresezer.departmentservice.service.impl;

import com.emresezer.departmentservice.dto.DepartmentDto;
import com.emresezer.departmentservice.entity.Department;
import com.emresezer.departmentservice.exception.ResourceNotFoundException;
import com.emresezer.departmentservice.mapper.AutoDepartmentMapper;
import com.emresezer.departmentservice.repository.DepartmentRepository;
import com.emresezer.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;


    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert department dto to department jpa entity
        Department department = AutoDepartmentMapper
                .MAPPER
                .mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto =
                AutoDepartmentMapper
                        .MAPPER
                        .mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department == null) {
            throw new ResourceNotFoundException("Department", "code", departmentCode);
        }

        DepartmentDto departmentDto =
                AutoDepartmentMapper
                        .MAPPER
                        .mapToDepartmentDto(department);

        return departmentDto;
    }
}
