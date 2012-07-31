package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.checker.Checker;
import com.asya.gotovskaya.trainingtask.entity.Task;
import com.asya.gotovskaya.trainingtask.handler.HandlerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class SaveChangeTaskAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<String> values = new ArrayList<String>();
        values.add(request.getParameter("id"));
        values.add(request.getParameter("name"));
        values.add(request.getParameter("hours"));
        values.add(request.getParameter("start"));
        values.add(request.getParameter("finish"));
        values.add(request.getParameter("state"));
        values.add(request.getParameter("idProject"));
        values.add(request.getParameter("idEmployee"));
        if (!Checker.checkValues(values)) {
            String message = "Не все поля заполненны";
            Task task = HandlerTask.createTemporaryTask(values);
            request.setAttribute("message", message);
            request.setAttribute("task", task);
            return "/enterTask.jsp";
        }
        HandlerTask.saveChangeTask(values);
        return "TaskAction";
    }
}
