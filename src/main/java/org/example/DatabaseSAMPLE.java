package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSAMPLE {
    // this is just a template file for ppl who wanna use my code,
    // I gatekept the actual Database.java file but this is pretty much
    // the exact same file but without the URL and PASSWORD, I assume you already
    // know how to set this up
    private static Connection conn;
    final private static String URL = "";
    final private static String USER = "root";
    final private static String PASSWORD = "";

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