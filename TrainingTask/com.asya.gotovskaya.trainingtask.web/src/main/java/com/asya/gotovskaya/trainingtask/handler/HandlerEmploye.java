package com.asya.gotovskaya.trainingtask.handler;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;
import com.asya.gotovskaya.trainingtask.entity.Employe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class HandlerEmploye {
    private static Employe employee;

    public static List<Employe> getAllEmployees() {
        List<Employe> employees = new ArrayList<Employe>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_employee from employees");
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Employe employe = createEmployee(rs.getInt(1));
                employees.add(employe);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return employees;
    }

    public static Employe createEmployee(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from employees where id_employee=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                return null;
            }
            rs.next();
            employee = new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static int deleteEmployee(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employees where id_employee=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int addEmployee(String lastName, String name, String middleName, String post){
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees" +
                    "(last_name, name, middle_name, post) values (?, ?, ?, ?)");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, post);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
