package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.handler.HandlerProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @author asya
 */
public class ProjectAction implements IAction {

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
