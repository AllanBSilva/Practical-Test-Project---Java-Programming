package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Employee extends Person {
    private BigDecimal salary;
    private String role;
    private static final BigDecimal MIN_SALARY = BigDecimal.valueOf(1212);

    public Employee(String name, LocalDate birthDate, BigDecimal salary, String role) {
        super(name, birthDate);
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo.");
        }
        if (salary.compareTo(MIN_SALARY) < 0) {
            throw new IllegalArgumentException("O salário não pode ser inferior ao salário mínimo.");
        }
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("A função não pode ser nula ou vazia.");
        }
        this.role = role;
    }

    public void increaseSalary(double percentage) {
        this.salary = this.salary.add(this.salary.multiply(BigDecimal.valueOf(percentage / 100)));
    }

    public String getFormattedSalary() {
        return String.format("%,.2f", salary);
    }

    public boolean isAdult() {
        int age = Period.between(getBirthDate(), LocalDate.now()).getYears();
        return age >= 18;
    }

    public boolean isBirthDateValid() {
        return getBirthDate().isBefore(LocalDate.now());
    }
}
