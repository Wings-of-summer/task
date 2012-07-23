package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.dbconnection.DataBaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Project implements Action{

    private Connection connection;

    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//        Connection connection = null;
        try {
            connection = DataBaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select project_name from projects");
            List<String> projects = new ArrayList<String>();
            if(rs == null){
                return "/error.jsp";
            }
            while (rs.next()){
                projects.add(rs.getString(1));
            }
            statement.close();
            request.setAttribute("projects", projects);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(request.getParameter("nameProject") != null){
            try {
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("select*from projects where project_name=?");
                preparedStatement.setString(1, (String)request.getParameter("nameProject"));
                ResultSet rs = preparedStatement.executeQuery();
                int id = 0;
                String name = null;
                String abbreviation = null;
                String description = null;
                if(rs == null){
                    return "/error.jsp";
                }
                rs.next();
                    id = rs.getInt(1);
                    name = rs.getString(2);
                    abbreviation = rs.getString(3);
                    description = rs.getString(4);

                //request.getSession().setAttribute("projects", projects);
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.setAttribute("abbreviation", abbreviation);
                request.setAttribute("description", description);
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return "/project.jsp";
    }
}
