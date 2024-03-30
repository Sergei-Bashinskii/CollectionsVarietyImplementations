package com.example.Collections.sheets.and.sets;
import com.example.Collections.sheets.and.sets.model.Employee;
import com.example.Collections.sheets.and.sets.service.DepartmentsService;
import com.example.Collections.sheets.and.sets.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DepartmentsServiceTest {
    private DepartmentsService departmentsService;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = Mockito.mock(EmployeeService.class);
        departmentsService = new DepartmentsService(employeeService);
    }

    @Test
    void maxSalaryDepartmentIdReturnsCorrectEmployee() {
        //Подготовка входных данных.
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(
                new Employee("Иван", "Иванов", 60000, 1),
                new Employee("Олег", "Олегович", 70000, 1)
        ));
        //Начала теста для 1 отдела.
        Employee maxSalaryEmployee = departmentsService.maxSalaryDepartmentId(1);
        //Проверка на Null.
        assertNotNull(maxSalaryEmployee);
        //Проверка максимальной зарплаты.
        assertEquals(70000, maxSalaryEmployee.getSalary());
    }

    @Test
    void minSalaryDepartmentIdReturnsCorrectEmployee() {
        //Подготовка входных данных.
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(
                new Employee("Иван", "Иванов", 60000, 1),
                new Employee("Олег", "Олегович", 50000, 1)
        ));
        //Начала теста для 1 отдела.
        Employee minSalaryEmployee = departmentsService.minSalaryDepartmentId(1);
        //Проверка на Null.
        assertNotNull(minSalaryEmployee);
        //Проверка минимальной зарплаты.
        assertEquals(50000, minSalaryEmployee.getSalary());
    }

    @Test
    void getSalarySumByDepartmentReturnsCorrectSum() {
        //Подготовка входных данных.
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(
                new Employee("Иван", "Иванов", 60000, 1),
                new Employee("Олег", "Олегович", 70000, 1)
        ));
        //Начала теста для 1 отдела.
        int sum = departmentsService.getSalarySumByDepartment(1);
        //Проверка суммы зарплат.
        assertEquals(130000, sum);
    }

    @Test
    void findByDepartmentIdReturnsCorrectEmployees() {
        //Подготовка входных данных.
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(
                new Employee("Иван", "Иванов", 60000, 1),
                new Employee("Олег", "Олегович", 70000, 1),
                new Employee("Виктор", "Викторов", 50000, 2)
        ));
        //Начала теста для 1 отдела.
        Collection<Employee> employees = departmentsService.findByDepartmentId(1);
        // Проверка двух сотрудников из 1 отдела.
        assertEquals(2, employees.size());
    }

    @Test
    void groupByDepartmentIdReturnsCorrectMap() {
        //Подготовка входных данных.
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(
                new Employee("Иван", "Иванов", 60000, 1),
                new Employee("Олег", "Олегович", 70000, 3),
                new Employee("Виктор", "Викторов", 50000, 2),
                new Employee("Леонид", "Леонидович", 80000, 1)
        ));
        //Начала теста.
        Map<Integer, List<Employee>> groupedEmployees = departmentsService.groupByDepartmentId();
        //Проверка правильного группирования.
        assertEquals(3, groupedEmployees.size());
        //Проверка отдела 1-3.
        assertTrue(groupedEmployees.containsKey(1));
        assertTrue(groupedEmployees.containsKey(2));
        assertTrue(groupedEmployees.containsKey(3));
        //Проверка правильного группирования в отделах.
        assertEquals(2, groupedEmployees.get(1).size());
        assertEquals(1, groupedEmployees.get(2).size());
        assertEquals(1, groupedEmployees.get(3).size());
    }
}
