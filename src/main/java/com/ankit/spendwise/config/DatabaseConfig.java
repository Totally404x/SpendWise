package com.ankit.spendwise.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static final String URL="jdbc:mysql://localhost:3306/spendwise";
    public static final String USERNAME="root";
    public static final String PASSWORD="A_kit1087pc";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD

        );
    }
}
