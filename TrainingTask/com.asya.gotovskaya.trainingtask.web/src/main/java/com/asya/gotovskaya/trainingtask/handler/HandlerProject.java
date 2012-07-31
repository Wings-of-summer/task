package com.asya.gotovskaya.trainingtask.handler;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;
import com.asya.gotovskaya.trainingtask.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class HandlerProject {
    private static Project project;

    public static Project createProject(int idSelect) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from projects where id_project=?");
            preparedStatement.setInt(1, idSelect);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                return null;
            }
            rs.next();
            project = new Project(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
            ResultSet rs = statement.executeQuery("select id_project from projects");
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Project pr = createProject(rs.getInt(1));
                projects.add(pr);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static int deleteProject(int id) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        int rs = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from projects where id_project=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
