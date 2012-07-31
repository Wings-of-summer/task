package com.asya.gotovskaya.trainingtask.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author asya
 */
public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
