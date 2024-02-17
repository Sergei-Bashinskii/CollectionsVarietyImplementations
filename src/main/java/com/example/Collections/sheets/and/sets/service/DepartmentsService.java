package com.example.Collections.sheets.and.sets.service;

import com.example.Collections.sheets.and.sets.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {

    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee maxSalaryDepartmentId(int departmentId) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee minSalaryDepartmentId(int departmentId) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Collection<Employee> findByDepartmentId(int departmentId){
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDepartmentId() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
