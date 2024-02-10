package com.example.Collections.sheets.and.sets.service;

import com.example.Collections.sheets.and.sets.exception.EmployeeAlreadyAddedException;
import com.example.Collections.sheets.and.sets.exception.EmployeeNotFoundException;
import com.example.Collections.sheets.and.sets.exception.EmployeeStorageIsFullException;
import com.example.Collections.sheets.and.sets.exception.IllegalArgumentException;
import com.example.Collections.sheets.and.sets.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int employeeCount = 5;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee createEmployee(String firstName, String lastName) {
        if (employees.size() >= employeeCount) {
            throw new EmployeeStorageIsFullException();
        }
        String key = getKey(firstName, lastName);
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Employee employee = employees.remove(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}