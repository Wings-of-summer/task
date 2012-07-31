package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Employee;
import com.asya.gotovskaya.trainingtask.handler.HandlerEmployee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class ChangeEmployeeAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = HandlerEmployee.createEmployee(id);
        request.setAttribute("employee", employee);
        return "/enterEmployee.jsp";
    }
}
