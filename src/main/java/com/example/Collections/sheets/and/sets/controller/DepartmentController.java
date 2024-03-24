package com.example.Collections.sheets.and.sets.controller;

import com.example.Collections.sheets.and.sets.model.Employee;
import com.example.Collections.sheets.and.sets.service.DepartmentsService;
import com.example.Collections.sheets.and.sets.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentsService service;

    public DepartmentController(DepartmentsService service) {
        this.service = service;
    }

    @GetMapping(path = "/{departmentId}/salary/max")
    public Employee maxSalaryDepartmentId(@PathVariable int departmentId) {
        return service.maxSalaryDepartmentId(departmentId);
    }

    @GetMapping(path = "/{departmentId}/salary/min")
    public Employee minSalaryDepartmentId(@PathVariable int departmentId) {
        return service.minSalaryDepartmentId(departmentId);
    }

    @GetMapping(path = "/{departmentId}/employees")
    public Collection<Employee> findByDepartment(@PathVariable int departmentId) {
        return service.findByDepartmentId(departmentId);
    }

    @GetMapping(path = "/{departmentId}/salary/sum")
    public int getSalaryByDepartment(@PathVariable int departmentId) {
        return service.getSalarySumByDepartment(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartmentId();
    }
}
