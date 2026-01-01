package com.bankease.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bankease\r\n" + //
                                "?useSSL=false\r\n" + //
                                "&serverTimezone=UTC\r\n" + //
                                "&allowPublicKeyRetrieval=true\r\n" + //
                                "";
    private static final String USER = "root";
    private static final String PASSWORD = "sharvesh";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to connect to database", e);
        }
    }
}
