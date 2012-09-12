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
    private static Project project;
    private static Employee employee;
    private static Task task;

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_task from tasks");
            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                Task task = createTask(resultSet.getInt(1));
                tasks.add(task);
            }

            resultSet.close();
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
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            PreparedStatement preparedStatementProject = connection.prepareStatement("select id_project from " +
                    "projects_tasks where id_task=?");
            preparedStatementProject.setInt(1, id);
            ResultSet rsProject = preparedStatementProject.executeQuery();
            if (rsProject != null) {
                rsProject.next();
                project = HandlerProject.createProject(rsProject.getInt(1));
            } else {
                project = HandlerProject.createEmptyProject();
            }
            PreparedStatement preparedStatementEmployee = connection.prepareStatement("select id_employee from " +
                    "employees_tasks where id_task=?");
            preparedStatementEmployee.setInt(1, id);
            ResultSet rsEmployee = preparedStatementEmployee.executeQuery();
            if (rsEmployee != null) {
                rsEmployee.next();
                employee = HandlerEmployee.createEmployee(rsEmployee.getInt(1));
            }
            resultSet.next();
            task = new Task(resultSet.getInt(1), resultSet.getString(2), project, resultSet.getInt(3),
                    resultSet.getDate(4), resultSet.getDate(5), employee, resultSet.getString(6));

            resultSet.close();
            rsEmployee.close();
            rsProject.close();
            preparedStatement.close();
            preparedStatementEmployee.close();
            preparedStatementProject.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public static void deleteTask(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from tasks where id_task=?");
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addTask(String name, Project project, int hours, Date start, Date finish, Employee employee, String state) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks" +
                    "(task_name, hours, start_date, finish_date, state) values (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, hours);
            preparedStatement.setDate(3, start);
            preparedStatement.setDate(4, finish);
            preparedStatement.setString(5, state);
            int rs = preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT IDENTITY() from tasks");
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

            preparedStatement.close();
            preparedStatementEmployee.close();
            preparedStatementProject.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void changeTask(int id, String name, Project project, int hours, Date start, Date finish, Employee employee, String state) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks " +
                    "SET task_name=?, hours=?, start_date=?, finish_date=?, state=? WHERE id_task=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, hours);
            preparedStatement.setDate(3, start);
            preparedStatement.setDate(4, finish);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, id);
            int rs = preparedStatement.executeUpdate();

            PreparedStatement preparedStatementProject = connection.prepareStatement("UPDATE projects_tasks " +
                    "SET id_project = ? WHERE id_task = ?");
            preparedStatementProject.setInt(1, project.getId());
            preparedStatementProject.setInt(2, id);
            preparedStatementProject.executeUpdate();

            PreparedStatement preparedStatementEmployee = connection.prepareStatement("UPDATE employees_tasks " +
                    "SET id_employee = ? WHERE id_task = ?");
            preparedStatementEmployee.setInt(1, employee.getId());
            preparedStatementEmployee.setInt(2, id);
            preparedStatementEmployee.executeUpdate();

            preparedStatement.close();
            preparedStatementEmployee.close();
            preparedStatementProject.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveChangeTask(List<String> values) {
        int hours = Integer.parseInt(values.get(2));
        Date start = Date.valueOf(values.get(3));
        Date finish = Date.valueOf(values.get(4));
        Project newProject = HandlerProject.createProject(Integer.parseInt(values.get(6)));
        Employee newEmployee = HandlerEmployee.createEmployee(Integer.parseInt(values.get(7)));

        if (values.get(0).equals("") || values.get(0).equals("01")) {
            addTask(values.get(1), newProject, hours, start, finish, newEmployee, values.get(5));
            return;
        }
        int id = Integer.parseInt(values.get(0));
        changeTask(id, values.get(1), newProject, hours, start, finish, newEmployee, values.get(5));
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
