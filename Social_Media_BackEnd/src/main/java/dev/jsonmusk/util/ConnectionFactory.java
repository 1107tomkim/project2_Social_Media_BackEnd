package dev.jsonmusk.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        // for Alec

        // this is a test
        ////////////////////////////////////////////////////////////////////////////////////
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=p5";
        String username = "postgres";
        String password = "password";

        // for not Alec
//        //////////////////////////////////////////////
//        String url = System.getenv("POSTGRES_SQL_DB");
//        String username = System.getenv("DB_USERNAME");
//        String password = System.getenv("PASSWORD");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
