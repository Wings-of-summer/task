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
            ResultSet resultSet = statement.executeQuery("select id_employee from employees");

            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet.getInt(1));
                employees.add(employee);
            }
            resultSet.close();
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
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                return null;
            }
            resultSet.next();
            employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5));
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void deleteEmployee(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatementEmployee = connection.prepareStatement("select id_task " +
                    "from employees_tasks where id_employee=?");
            preparedStatementEmployee.setInt(1, id);
            ResultSet resultSet = preparedStatementEmployee.executeQuery();

            preparedStatementEmployee.close();

            while (resultSet.next()) {
                HandlerTask.deleteTask(resultSet.getInt(1));
            }
            resultSet.close();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from employees where id_employee=?");
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(String lastName, String name, String middleName, String post) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees" +
                    "(last_name, name, middle_name, post) values (?, ?, ?, ?)");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, post);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void changeEmployee(int id, String lastName, String name, String middleName, String post) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees " +
                    "SET last_name=?, name=?, middle_name=?, post=? WHERE id_employee=?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, middleName);
            preparedStatement.setString(4, post);
            preparedStatement.setInt(5, id);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveChangeEmployee(List<String> values) {
        if (values.get(0).equals("") || values.get(0).equals("01")) {
            addEmployee(values.get(1), values.get(2), values.get(3), values.get(4));
            return;
        }
        int id = Integer.parseInt(values.get(0));
        changeEmployee(id, values.get(1), values.get(2), values.get(3), values.get(4));
    }

    public static Employee createTemporaryEmployee(List<String> values) {
        int id = 0;
        if (values.get(0).equals("")) {
            id = 01;
        } else {
            id = Integer.parseInt(values.get(0));
        }
        employee = new Employee(id, values.get(1), values.get(2), values.get(3), values.get(4));
        return employee;
    }
}
