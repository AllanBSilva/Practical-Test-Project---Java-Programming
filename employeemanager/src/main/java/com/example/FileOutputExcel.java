package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileOutputExcel {

    public static void writeEmployeesToFile(List<Employee> employees) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome");
        header.createCell(1).setCellValue("Data Nascimento");
        header.createCell(2).setCellValue("Salário");
        header.createCell(3).setCellValue("Função");

        CellStyle currencyStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        currencyStyle.setDataFormat(format.getFormat("R$ #,##0.00"));

        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd/MM/yyyy"));

        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getName());

            Cell birthDateCell = row.createCell(1);
            birthDateCell.setCellValue(employee.getBirthDate());
            birthDateCell.setCellStyle(dateStyle);

            Cell salaryCell = row.createCell(2);
            salaryCell.setCellValue(employee.getSalary().doubleValue());
            salaryCell.setCellStyle(currencyStyle);

            row.createCell(3).setCellValue(employee.getRole());
        }

        try (FileOutputStream fileOut = new FileOutputStream("employee_report.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Relatório gerado com sucesso em 'employee_report.xlsx'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
