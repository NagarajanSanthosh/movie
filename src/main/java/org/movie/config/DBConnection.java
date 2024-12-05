package org.movie.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/screenmaster";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mlpzaq987";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        }
        catch(SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }

}
