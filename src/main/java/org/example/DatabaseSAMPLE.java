package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSAMPLE {
    // this is just a template file for ppl who wanna use my code,
    // I gatekept the actual Database.java file but this is pretty much
    // the exact same file but without the URL and PASSWORD, I assume you already
    // know how to set this up
    // just rename this file, class, and private constructor to Database
    private static Connection conn;
    private static final String URL = "yourURL";
    private static final String USER = "root";
    private static final String PASSWORD = "yourPassword";

    private DatabaseSAMPLE() {}

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