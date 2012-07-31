package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Task;
import com.asya.gotovskaya.trainingtask.handler.HandlerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author asya
 */
public class TaskAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Task> tasks = HandlerTask.getAllTasks();
        if(tasks == null){
            return "/error.jsp";
        }
        request.setAttribute("tasks", tasks);
        if (request.getParameter("selectedId") != null) {
            int selectedId = Integer.parseInt(request.getParameter("selectedId"));
            Task task = HandlerTask.createTask(selectedId);
            request.setAttribute("selectedTask", task);
        }
        return "/task.jsp";
    }
}
