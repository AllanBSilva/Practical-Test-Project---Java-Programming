package com.example;

import java.sql.*;

public class DerbyDatabase {

    private static final String DB_URL = "jdbc:derby:employeeDB;create=true";

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {

            String checkTableSQL = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'EMPLOYEES'";
            ResultSet rs = stmt.executeQuery(checkTableSQL);
            if (rs.next()) {
                return;
            }

            String createTableSQL = "CREATE TABLE Employees ("
                    + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                    + "Name VARCHAR(100), "
                    + "BirthDate DATE, "
                    + "Salary DECIMAL(15, 2), "
                    + "Role VARCHAR(50), "
                    + "CONSTRAINT unique_employee UNIQUE (Name, BirthDate, Role))";

            stmt.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertEmployee(Employee employee) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            String checkSql = "SELECT ID FROM Employees WHERE Name = ? AND BirthDate = ? AND Role = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, employee.getName());
                checkStmt.setDate(2, Date.valueOf(employee.getBirthDate()));
                checkStmt.setString(3, employee.getRole());

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    return;
                }

                String insertSql = "INSERT INTO Employees (Name, BirthDate, Salary, Role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, employee.getName());
                    insertStmt.setDate(2, Date.valueOf(employee.getBirthDate()));
                    insertStmt.setBigDecimal(3, employee.getSalary());
                    insertStmt.setString(4, employee.getRole());

                    insertStmt.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listEmployees() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Employees")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getString("ID"));
                System.out.println("Nome: " + rs.getString("Name"));
                System.out.println("Data de Nascimento: " + rs.getDate("BirthDate"));
                System.out.println("Salário: " + rs.getBigDecimal("Salary"));
                System.out.println("Função: " + rs.getString("Role"));
                System.out.println("------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
