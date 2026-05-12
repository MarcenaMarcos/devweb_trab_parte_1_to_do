package com.todo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Verifique se o nome do banco no MySQL é 'todo_db' ou ajuste abaixo
    private static final String URL = "jdbc:mysql://localhost:3306/todo_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // Senha do seu MySQL (vazio no XAMPP padrão)

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro na conexão com o banco: " + e.getMessage());
            return null;
        }
    }
}
