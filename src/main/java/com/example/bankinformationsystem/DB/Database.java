package com.example.bankinformationsystem.DB;

import java.sql.*;

public abstract class Database {
    public Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        } catch (SQLException e) {
            System.out.println("FAILED" + e);
            return null;
        }
    }
}

