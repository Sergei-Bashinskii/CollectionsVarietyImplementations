package com.example.Collections.sheets.and.sets.controller;

import com.example.Collections.sheets.and.sets.model.Employee;
import com.example.Collections.sheets.and.sets.service.DepartmentsService;
import com.example.Collections.sheets.and.sets.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryDepartmentId(@RequestParam int departmentId) {
        return service.maxSalaryDepartmentId(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalaryDepartmentId(@RequestParam int departmentId) {
        return service.minSalaryDepartmentId(departmentId);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> findByDepartment(@RequestParam int departmentId) {
        return service.findByDepartmentId(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartmentId();
    }
}
