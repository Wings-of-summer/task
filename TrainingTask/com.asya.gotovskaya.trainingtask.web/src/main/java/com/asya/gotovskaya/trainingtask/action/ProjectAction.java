package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.handler.HandlerProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class ProjectAction implements Action {

    private Connection connection;

    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Project> projects = HandlerProject.getAllProject();
        if(projects == null){
            return "/error.jsp";
        }
        request.setAttribute("projects", projects);
        if (request.getParameter("selectProject") != null) {
            int selectedId = Integer.parseInt(request.getParameter("selectProject"));
            Project project = HandlerProject.createProject(selectedId);
            request.setAttribute("selected", project);
        }
        return "/project.jsp";
    }
}
