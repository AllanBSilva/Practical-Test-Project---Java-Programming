package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOutputTXT {
    public static void writeEmployeesToFile(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_report.txt"))) {
            for (Employee employee : employees) {
                writer.write("Nome: " + employee.getName());
                writer.newLine();
                writer.write("Data de Nascimento: " + employee.getFormattedBirthDate());
                writer.newLine();
                writer.write("Salário: R$" + employee.getFormattedSalary());
                writer.newLine();
                writer.write("Função: " + employee.getRole());
                writer.newLine();
                writer.newLine();
            }
            System.out.println("Relatório gerado com sucesso em 'employee_report.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
