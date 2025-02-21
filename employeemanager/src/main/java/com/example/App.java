package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class App {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        try {
            employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
            employees.add(new Employee("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
            employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
            employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
            employees
                    .add(new Employee("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
            employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
            employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
            employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
            employees.add(
                    new Employee("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
            employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
        }

        // Remove "João"
        employees.removeIf(employee -> employee.getName().equals("João"));

        System.out.println("***********************************************************");
        System.out.println("Imprimir todos os funcionários com todas suas informações:");
        System.out.println("***********************************************************");

        employees.forEach(employee -> {
            System.out.println("Nome: " + employee.getName());
            System.out.println("Data de Nascimento: " + employee.getFormattedBirthDate());
            System.out.println("Salário: R$" + employee.getFormattedSalary());
            System.out.println("Função: " + employee.getRole());
            System.out.println("-----------------------------------------------");
        });

        employees.forEach(employee -> {
            try {
                employee.increaseSalary(10);
            } catch (Exception e) {
                System.out.println("Erro ao aumentar salário de " + employee.getName() + ": " + e.getMessage());
            }
        });

        Map<String, List<Employee>> employeesByRole = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));

        System.out.println("=================================================");
        System.out.println("Imprimir os funcionários, agrupados por função:");
        System.out.println("=================================================");

        employeesByRole.forEach((role, empList) -> {
            System.out.println(role + ":");
            empList.forEach(employee -> System.out.println("  - " + employee.getName()));
        });

        System.out.println("===============================================================");
        System.out.println("Imprimir os funcionários que fazem aniversário no mês 10 e 12:");
        System.out.println("===============================================================");

        System.out.println("Funcionários com aniversário em outubro e dezembro:");
        employees.stream()
                .filter(employee -> employee.getBirthDate().getMonthValue() == 10
                        || employee.getBirthDate().getMonthValue() == 12)
                .forEach(employee -> System.out.println(employee.getName()));

        System.out.println("=============================================================================");
        System.out.println("Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade:");
        System.out.println("=============================================================================");

        Employee oldest = employees.stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("\nO funcionário mais velho é " + oldest.getName() + " com " + oldest.getAge() + " anos.\n");

        System.out.println("=======================================================");
        System.out.println("Imprimir a lista de funcionários por ordem alfabética:");
        System.out.println("=======================================================");

        System.out.println("Funcionários ordenados alfabeticamente:");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee -> System.out.println(employee.getName()));

        System.out.println("================================================");
        System.out.println("Imprimir o total dos salários dos funcionários:");
        System.out.println("================================================");

        BigDecimal totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal dos salários de todos os funcionários: R$" + totalSalary + "\n");

        System.out.println(
                "========================================================================================================");
        System.out.println(
                "Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00:");
        System.out.println(
                "========================================================================================================");

        final BigDecimal minimumWage = BigDecimal.valueOf(1212.00);
        employees.forEach(employee -> {
            BigDecimal salaryInMinWages = employee.getSalary().divide(minimumWage, 2, RoundingMode.HALF_UP);
            System.out.println(employee.getName() + " ganha " + salaryInMinWages + " salários mínimos.");
        });

        System.out.println("\n*********************************************");
        System.out.println("Atividades extras:");
        System.out.println("*********************************************");

        try {
            FileOutputTXT.writeEmployeesToFile(employees);
            FileOutputExcel.writeEmployeesToFile(employees);
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados em arquivo: " + e.getMessage());
        }

        DerbyDatabase.createTable();
        boolean allInserted = true;

        for (Employee employee : employees) {
            try {
                DerbyDatabase.insertEmployee(employee);
            } catch (Exception e) {
                allInserted = false;
                System.out.println("Erro ao inserir o funcionário: " + employee.getName());
            }
        }

        if (allInserted) {
            System.out.println("Dados verificados e inseridos, se necessário, no banco 'employeeDB'.");
        } else {
            System.out.println("Alguns dados não foram inseridos devido a erros.");
        }

    }
}
