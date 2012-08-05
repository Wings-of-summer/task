package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.entity.Task;
import com.asya.gotovskaya.trainingtask.handler.HandlerProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author asya
 */
public class ChangeProjectAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int id = 0;
        if (request.getAttribute("id") == null){
            id = Integer.parseInt(request.getParameter("id"));
        } else {
            id = Integer.parseInt(request.getAttribute("id").toString());
        }
        List<Task> tasks = HandlerProject.getAllTasksProject(id);
        if (tasks.size() == 0){
            String empty = null;
            request.setAttribute("tasks", empty);
        } else {
            request.setAttribute("tasks", tasks);
        }
        Project project = HandlerProject.createProject(id);
        request.setAttribute("project", project);
        return "/enterProject.jsp";
    }
}
