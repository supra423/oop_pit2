package org.example.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;
    final private static String URL = "jdbc:mysql://127.0.0.1:3306/junkshop";
    final private static String USER = "root";
    final private static String PASSWORD = "bongisreal23";

    private Database() {}

    public static Connection getConn() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}