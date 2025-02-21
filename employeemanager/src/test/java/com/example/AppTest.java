package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private List<Employee> employees;

    @BeforeEach
    public void setup() {
        employees = new ArrayList<>();
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
    }

    @Test
    public void testAddEmployee() {
        Employee newEmployee = new Employee("Ana", LocalDate.of(1995, 7, 15), BigDecimal.valueOf(3000.00),
                "Recepcionista");
        employees.add(newEmployee);
        assertEquals(4, employees.size());
        assertTrue(employees.contains(newEmployee));
    }

    @Test
    public void testRemoveEmployee() {
        employees.removeIf(employee -> employee.getName().equals("João"));
        assertEquals(2, employees.size());
        assertFalse(employees.stream().anyMatch(employee -> employee.getName().equals("João")));
    }

    @Test
    public void testTotalSalaryCalculation() {
        BigDecimal totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(BigDecimal.valueOf(2009.44 + 2284.38 + 9836.14), totalSalary);
    }

    @Test
    public void testSalaryIncrease() {
        employees.forEach(employee -> employee.increaseSalary(10));
        employees.forEach(employee -> {
            assertTrue(employee.getSalary().compareTo(BigDecimal.valueOf(0)) > 0);
        });
    }

    @Test
    public void testGetFormattedSalary() {
        Employee employee = new Employee("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador");
        assertEquals("2.284,38", employee.getFormattedSalary());
    }

    @Test
    public void testGetAge() {
        Employee employee = new Employee("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador");
        assertEquals(64, employee.getAge());
    }

    @Test
    public void testEmployeeFields() {
        Employee employee = new Employee("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor");
        assertEquals("Miguel", employee.getName());
        assertEquals("Diretor", employee.getRole());
    }

    @Test
    public void testNegativeSalary() {
        Employee employee = new Employee("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68),
                "Recepcionista");
        assertThrows(IllegalArgumentException.class, () -> employee.setSalary(BigDecimal.valueOf(-1000)));
    }

    @Test
    public void testEmployeeConstructor() {
        Employee employee = new Employee("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador");
        assertEquals("Heitor", employee.getName());
        assertEquals(LocalDate.of(1999, 11, 19), employee.getBirthDate());
        assertEquals(BigDecimal.valueOf(1582.72), employee.getSalary());
        assertEquals("Operador", employee.getRole());
    }

}
