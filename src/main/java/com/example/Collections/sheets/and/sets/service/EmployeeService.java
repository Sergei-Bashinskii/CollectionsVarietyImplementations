package com.example.Collections.sheets.and.sets.service;

import com.example.Collections.sheets.and.sets.exception.*;
import com.example.Collections.sheets.and.sets.exception.IllegalArgumentException;
import com.example.Collections.sheets.and.sets.helper.HelperNull;
import com.example.Collections.sheets.and.sets.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int employeeCount = 10;
    private final Map<String, Employee> employees = new HashMap<>(employeeCount);

    public Employee createEmployee(String firstName, String lastName, int salary, int department) {
        if (employees.size() >= employeeCount) {
            throw new EmployeeStorageIsFullException();
        }
        String key = getKey(firstName, lastName);
        if (HelperNull.isEmptyString(firstName) || HelperNull.isEmptyString(lastName)) {
            throw new IllegalArgumentException();
        }
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InputException();
        }
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department);
        employees.put(key, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        if (HelperNull.isEmptyString(firstName) || HelperNull.isEmptyString(lastName)) {
            throw new IllegalArgumentException();
        }
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InputException();
        }
        Employee employee = employees.remove(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (HelperNull.isEmptyString(firstName) || HelperNull.isEmptyString(lastName)) {
            throw new IllegalArgumentException();
        }
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new InputException();
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
        return StringUtils.capitalize(firstName) + StringUtils.capitalize(lastName);
    }
}