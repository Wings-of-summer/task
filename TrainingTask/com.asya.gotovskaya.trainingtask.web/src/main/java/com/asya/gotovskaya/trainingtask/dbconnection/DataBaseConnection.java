package com.asya.gotovskaya.trainingtask.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author asya
 */
public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Map<String, String> databaseProperty = PropertyReader.getDatabaseProperties();
            connection = DriverManager.getConnection(databaseProperty.get("database"), databaseProperty.get("user"),
                    databaseProperty.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
