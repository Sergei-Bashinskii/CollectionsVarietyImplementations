package com.example.Collections.sheets.and.sets.service;

import com.example.Collections.sheets.and.sets.exception.EmployeeAlreadyAddedException;
import com.example.Collections.sheets.and.sets.exception.EmployeeNotFoundException;
import com.example.Collections.sheets.and.sets.exception.EmployeeStorageIsFullException;
import com.example.Collections.sheets.and.sets.exception.IllegalArgumentException;
import com.example.Collections.sheets.and.sets.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int employeeCount = 5;
    private final List<Employee> employees = new ArrayList<>();

    public Employee createEmployee(String firstName, String lastName) {
        if (employees.size() >= employeeCount) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (!employees.remove(target)) {
            throw new EmployeeNotFoundException();
        }
        return target;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int targetIndex = employees.indexOf(target);
        if (targetIndex < 0) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(targetIndex);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }
}
