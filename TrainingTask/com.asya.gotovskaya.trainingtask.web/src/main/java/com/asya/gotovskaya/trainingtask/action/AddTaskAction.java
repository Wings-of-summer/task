package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Employee;
import com.asya.gotovskaya.trainingtask.entity.Project;
import com.asya.gotovskaya.trainingtask.handler.HandlerEmployee;
import com.asya.gotovskaya.trainingtask.handler.HandlerProject;
import com.asya.gotovskaya.trainingtask.handler.HandlerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author asya
 */
public class AddTaskAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Project> projects = HandlerProject.getAllProject();
        request.setAttribute("projects", projects);
        List<Employee> employees = HandlerEmployee.getAllEmployees();
        request.setAttribute("employees", employees);
        List<String> states = HandlerTask.getStates();
        request.setAttribute("states", states);
        return "/enterTask.jsp";
    }
}
