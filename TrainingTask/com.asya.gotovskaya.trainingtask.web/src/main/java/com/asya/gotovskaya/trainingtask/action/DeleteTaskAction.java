package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class DeleteTaskAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HandlerTask.deleteTask(Integer.parseInt(request.getParameter("id")));
        return "TaskAction";
    }
}
