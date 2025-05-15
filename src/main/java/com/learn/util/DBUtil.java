package com.learn.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;
    
    static {
        Properties props = new Properties();
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(input);
            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            DB_PASSWORD = props.getProperty("db.password");

            // Load driver class from properties
            Class.forName(props.getProperty("db.driver"));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	throw new RuntimeException("Failed to close database connection", e);
            }
        }
    }
}