package com.asya.gotovskaya.trainingtask.handler;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;
import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class HandlerProject {
    private static Project project;
    private static int idNewProject;

    public static Project createProject(int idSelect) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from projects where id_project=?");
            preparedStatement.setInt(1, idSelect);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                return null;
            }
            resultSet.next();
            project = new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public static List<Project> getAllProject() {
        List<Project> projects = new ArrayList<Project>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id_project from projects");

            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                Project pr = createProject(resultSet.getInt(1));
                projects.add(pr);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static void deleteProject(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatementEmployee = connection.prepareStatement("select id_task " +
                                "from projects_tasks where id_project=?");
            preparedStatementEmployee.setInt(1, id);
            ResultSet resultSet = preparedStatementEmployee.executeQuery();
            preparedStatementEmployee.close();

            while (resultSet.next()){
                HandlerTask.deleteTask(resultSet.getInt(1));
            }
            resultSet.close();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from projects where id_project=?");
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Project createEmptyProject() {
        Project emptyProject = new Project(00, "", "Проект не указан", "");
        return emptyProject;
    }

    public static List<Task> getAllTasksProject(int projectId) {
        List<Task> tasks = new ArrayList<Task>();
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id_task " +
                    "from projects_tasks where id_project=?");
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                return null;
            }
            int taskId = 0;
            while (resultSet.next()) {
                taskId = resultSet.getInt(1);
                tasks.add(HandlerTask.createTask(taskId));
            }

            resultSet.close();
            preparedStatement.close();

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Project createTemporaryProject(List<String> values) {
        int id = 0;
        if (values.get(0).equals("")) {
            id = 01;
        } else {
            id = Integer.parseInt(values.get(0));
        }
        project = new Project(id, values.get(1), values.get(2), values.get(3));
        return project;
    }

    private static void addProject(String name, String abbreviation, String description) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO projects " +
                    "(project_name, abbreviation, description) values (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, abbreviation);
            preparedStatement.setString(3, description);
            int rs = preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT IDENTITY() from projects");
            resultSet.next();
            int id = resultSet.getInt(1);
            setIdNewProject(id);

            resultSet.close();
            preparedStatement.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void changeProject(int id, String name, String abbreviation, String description) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE projects " +
                    "SET project_name=?, abbreviation=?, description=? WHERE id_project=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, abbreviation);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveChangeProject(List<String> values) {
        if (values.get(0).equals("") || values.get(0).equals("01")) {
            addProject(values.get(1), values.get(2), values.get(3));
            return;
        }
        int id = Integer.parseInt(values.get(0));
        changeProject(id, values.get(1), values.get(2), values.get(3));
    }

    public static int getIdNewProject() {
        return idNewProject;
    }

    public static void setIdNewProject(int idNewProject) {
        HandlerProject.idNewProject = idNewProject;
    }
}
