package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.checker.Checker;
import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.handler.HandlerProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class SaveChangeProjectAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<String> values = new ArrayList<String>();
        values.add(request.getParameter("id"));
        values.add(request.getParameter("name"));
        values.add(request.getParameter("abbreviation"));
        values.add(request.getParameter("description"));
        if (!Checker.checkValues(values)) {
            String message = "Не все поля заполненны";
            Project project = HandlerProject.createTemporaryProject(values);
            request.setAttribute("message", message);
            request.setAttribute("project", project);
            return "/enterEmployee.jsp";
        }
        HandlerProject.saveChangeProject(values);
        if (request.getParameter("addFlag") != null){
            int id = HandlerProject.getIdNewProject();
            request.setAttribute("id", id);
            return "ChangeProjectAction";
        }
        return "ProjectAction";
    }
}
