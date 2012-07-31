package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerEmployee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class DeleteEmployeeAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HandlerEmployee.deleteEmployee(Integer.parseInt(request.getParameter("id")));
        return "EmployeeAction";
    }
}
