package com.asya.gotovskaya.trainingtask.handler;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;
import com.asya.gotovskaya.trainingtask.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class HandlerEmployee {
    private static Employee employee;

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_employee from employees");
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Employee employee = createEmployee(rs.getInt(1));
                employees.add(employee);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee createEmployee(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from employees where id_employee=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                return null;
            }
            rs.next();
            employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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

    private static int addEmployee(String lastName, String name, String middleName, String post){
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

    private static int changeEmployee(int id, String lastName, String name, String middleName, String post){
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees " +
                    "SET last_name=?, name=?, middle_name=?, post=? WHERE id_employee=?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, post);
            preparedStatement.setInt(5, id);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void saveChangeEmployee(List<String> values){
        if(values.get(0).equals("") || values.get(0).equals("01")){
           addEmployee(values.get(1), values.get(2), values.get(3), values.get(4));
            return;
        }
        int id = Integer.parseInt(values.get(0));
        changeEmployee(id, values.get(1), values.get(2), values.get(3), values.get(4));
    }

    public static Employee createTemporaryEmployee(List<String> values){
        int id = 0;
        if(values.get(0).equals("")){
            id = 01;
        } else {
            id = Integer.parseInt(values.get(0));
        }
        employee = new Employee(id, values.get(1), values.get(2), values.get(3), values.get(4));
        return employee;
    }
}
