package com.example.bankinformationsystem.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

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

