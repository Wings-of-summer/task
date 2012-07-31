package com.asya.gotovskaya.trainingtask.handler;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;
import com.asya.gotovskaya.trainingtask.entity.Employee;
import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class HandlerTask {
    private static Task task;
    private static Project project;
    private static Employee employee;

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id_task from tasks");
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Task task = createTask(rs.getInt(1));
                tasks.add(task);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static Task createTask(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from tasks where id_task=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                return null;
            }
            PreparedStatement preparedStatementProject = connection.prepareStatement("select id_project from " +
                    "projects_tasks where id_task=?");
            preparedStatementProject.setInt(1, id);
            ResultSet rsProject = preparedStatement.executeQuery();
            if (rsProject != null) {
                rsProject.next();
                project = HandlerProject.createProject(rsProject.getInt(1));
            }
            PreparedStatement preparedStatementEmployee = connection.prepareStatement("select id_employee from " +
                    "employees_tasks where id_task=?");
            preparedStatementEmployee.setInt(1, id);
            ResultSet rsEmployee = preparedStatementEmployee.executeQuery();
            if (rsEmployee != null) {
                rsEmployee.next();
                employee = HandlerEmployee.createEmployee(rsEmployee.getInt(1));
            }
            rs.next();
            task = new Task(rs.getInt(1), rs.getString(2), project, rs.getInt(3), rs.getDate(4), rs.getDate(5), employee, rs.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public static int deleteTask(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from tasks where id_task=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static int addTask(String name, Project project, int hours, Date start, Date finish, Employee employee, String state) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks" +
                    "(task_name, hours, start_date, finish_date, state) values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, hours);
            preparedStatement.setDate(3, start);
            preparedStatement.setDate(4, finish);
            preparedStatement.setString(5, state);
            rs = preparedStatement.executeUpdate();

            PreparedStatement preparedStatementNewId = connection.prepareStatement("SELECT id_task FROM tasks " +
                    "WHERE task_name=? AND hours=? AND start_date=? AND finish_date=? AND state=?");
            preparedStatement.setString(1, name);
            preparedStatementNewId.setInt(2, hours);
            preparedStatementNewId.setDate(3, start);
            preparedStatementNewId.setDate(4, finish);
            preparedStatementNewId.setString(5, state);
            ResultSet resultSet = preparedStatementNewId.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);

            PreparedStatement preparedStatementProject = connection.prepareStatement("INSERT INTO " +
                    "projects_tasks(id_project, id_task) values (?, ?)");
            preparedStatementProject.setInt(1, project.getId());
            preparedStatementProject.setInt(2, id);
            preparedStatementProject.executeUpdate();

            PreparedStatement preparedStatementEmployee = connection.prepareStatement("INSERT INTO " +
                    "employees_tasks(id_employee, id_task) values (?, ?)");
            preparedStatementEmployee.setInt(1, employee.getId());
            preparedStatementEmployee.setInt(2, id);
            preparedStatementEmployee.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static int changeTask(int id, String name, Project project, int hours, Date start, Date finish, Employee employee, String state) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks " +
                    "SET task_name=?, hours=?, start_date=?, finish_date=?, state=? WHERE id_task=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, hours);
            preparedStatement.setDate(3, start);
            preparedStatement.setDate(4, finish);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, id);
            rs = preparedStatement.executeUpdate();

            PreparedStatement preparedStatementProject = connection.prepareStatement("INSERT INTO " +
                    "projects_tasks(id_project, id_task) values (?, ?);");
            preparedStatementProject.setInt(1, project.getId());
            preparedStatementProject.setInt(2, id);
            preparedStatementProject.executeUpdate();

            PreparedStatement preparedStatementEmployee = connection.prepareStatement("INSERT INTO " +
                    "employees_tasks(id_employee, id_task) values (?, ?);");
            preparedStatementEmployee.setInt(1, employee.getId());
            preparedStatementEmployee.setInt(2, id);
            preparedStatementEmployee.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void saveChangeTask(List<String> values) {
        int hours = Integer.parseInt(values.get(2));
        Date start = Date.valueOf(values.get(3));
        Date finish = Date.valueOf(values.get(4));
        project = HandlerProject.createProject(Integer.parseInt(values.get(6)));
        employee = HandlerEmployee.createEmployee(Integer.parseInt(values.get(7)));
        if (values.get(0).equals("") || values.get(0).equals("01")) {
            addTask(values.get(1), project, hours, start, finish, employee, values.get(5));
            return;
        }
        int id = Integer.parseInt(values.get(0));
        changeTask(id, values.get(1), project, hours, start, finish, employee, values.get(5));
    }

    public static Task createTemporaryTask(List<String> values) {
        int id = 0;
        if (values.get(0).equals("")) {
            id = 01;
        } else {
            id = Integer.parseInt(values.get(0));
        }
        int hours = Integer.parseInt(values.get(2));
        Date start = Date.valueOf(values.get(3));
        Date finish = Date.valueOf(values.get(4));
        project = HandlerProject.createProject(Integer.parseInt(values.get(6)));
        employee = HandlerEmployee.createEmployee(Integer.parseInt(values.get(7)));
        task = new Task(id, values.get(1), project, hours, start, finish, employee, values.get(5));
        return task;
    }

    public static List<String> getStates() {
        List<String> states = new ArrayList<String>();
        states.add("Не начата");
        states.add("В процессе");
        states.add("Завершена");
        states.add("Отложена");
        return states;
    }
}
