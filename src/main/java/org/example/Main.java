package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Database.getConn().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
//COMMENT DAW