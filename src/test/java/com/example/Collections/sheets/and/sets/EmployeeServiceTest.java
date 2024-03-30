package com.example.Collections.sheets.and.sets;
import com.example.Collections.sheets.and.sets.exception.*;
import com.example.Collections.sheets.and.sets.model.Employee;
import com.example.Collections.sheets.and.sets.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void createEmployeeSuccessfully() {
        //Подготовка входных данных.
        String firstName = "Иван";
        String lastName = "Иванов";
        int salary = 50000;
        int departmentId = 1;
        //Подготовка ожидаемого результата.
        Employee expectedEmployee = new Employee(firstName, lastName, salary, departmentId);
        //Проверка на Null.
        assertNotNull(expectedEmployee);
        //Начала теста.
        Employee actualEmployee = employeeService.createEmployee(firstName, lastName, salary, departmentId);
        //Проверка на Null.
        assertNotNull(actualEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void createEmployeeThrowsExceptionName() {
        //Подготовка входных данных.
        Executable executable1 = () -> employeeService.createEmployee("123", "Иванов", 50000, 1);
        Executable executable2 = () -> employeeService.createEmployee("Иван", "123", 50000, 1);
        // Проверка выбрасывания исключения.
        assertThrows(InputException.class, executable1);
        assertThrows(InputException.class, executable2);
    }

    @Test
    void removeEmployeeSuccessfully() {
        //Подготовка входных данных.
        employeeService.createEmployee("Иван", "Иванов", 50000, 1);
        //Начала теста.
        Employee removed = employeeService.removeEmployee("Иван", "Иванов");
        //Проверка на Null.
        assertNotNull(removed);
    }

    @Test
    void removeEmployeeThrowsExceptionNotFound() {
        //Подготовка входных данных.
        Executable executable = () -> employeeService.removeEmployee("Нет", "Сотрудника");
        // Проверка выбрасывания исключения.
        assertThrows(EmployeeNotFoundException.class, executable);
    }

    @Test
    void findEmployeeSuccessfully() {
        //Подготовка входных данных.
        employeeService.createEmployee("Иван", "Иванов", 50000, 1);
        //Начала теста.
        Employee found = employeeService.findEmployee("Иван", "Иванов");
        //Проверка на Null.
        assertNotNull(found);
    }

    @Test
    void findEmployeeThrowsExceptionNotFound() {
        //Подготовка входных данных.
        Executable executable = () -> employeeService.findEmployee("Нет", "Сотрудника");
        // Проверка выбрасывания исключения.
        assertThrows(EmployeeNotFoundException.class, executable);
    }
}
