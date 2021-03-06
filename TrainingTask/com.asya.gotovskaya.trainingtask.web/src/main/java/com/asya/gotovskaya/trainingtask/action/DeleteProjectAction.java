package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class DeleteProjectAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HandlerProject.deleteProject(Integer.parseInt(request.getParameter("id")));
        return "ProjectAction";
    }
}
