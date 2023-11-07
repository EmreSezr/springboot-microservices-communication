package com.emresezer.departmentservice.service;

import com.emresezer.departmentservice.dto.DepartmentDto;
import com.emresezer.departmentservice.entity.Department;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
