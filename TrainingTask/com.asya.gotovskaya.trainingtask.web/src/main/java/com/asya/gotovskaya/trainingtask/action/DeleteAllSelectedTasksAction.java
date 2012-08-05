package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author asya
 */
public class DeleteAllSelectedTasksAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("id", request.getParameter("id"));
        Enumeration enumeration = request.getParameterNames();
        if(enumeration.hasMoreElements()){
            while (enumeration.hasMoreElements()){
                int id = Integer.parseInt(request.getParameter(enumeration.nextElement().toString()));
                HandlerTask.deleteTask(id);
            }
        }
        return "ChangeProjectAction";
    }
}
