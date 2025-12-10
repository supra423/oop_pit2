package org.example.Model;
import java.sql.*;

public class DatabaseSAMPLE {
    // this is just a template file for ppl who wanna use my code,
    // I gatekept the actual Database.java file but this is pretty much
    // the exact same file but without the URL and PASSWORD, I assume you already
    // know how to set this up
    // just create a Database.java file and you can just copy paste the contents
    // of this file to the Database.java file and replace the class name and constructor
    // to Database instead of Database.java file, DON'T TOUCH THIS FILE ITSELF
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