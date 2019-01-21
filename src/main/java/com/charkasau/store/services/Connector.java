package com.charkasau.store.services;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    public static final String DB_PROPERTIES_PATH = "/db.properties";

    public static Connection getConnection() {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DB_PROPERTIES_PATH));

            String dbUrl = properties.getProperty("db.url");
            String dbLogin = properties.getProperty("db.login");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driver");

            Class.forName(dbDriverClassName);

            Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPassword);

            return connection;
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
