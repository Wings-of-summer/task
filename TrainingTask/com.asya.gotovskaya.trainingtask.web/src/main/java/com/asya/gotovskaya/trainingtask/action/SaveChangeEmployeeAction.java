package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.checker.Checker;
import com.asya.gotovskaya.trainingtask.entity.Employee;
import com.asya.gotovskaya.trainingtask.handler.HandlerEmployee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author asya
 */
public class SaveChangeEmployeeAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<String> values = new ArrayList<String>();
        values.add(request.getParameter("id"));
        values.add(request.getParameter("lastName"));
        values.add(request.getParameter("name"));
        values.add(request.getParameter("middleName"));
        values.add(request.getParameter("post"));
        if (!Checker.checkValues(values)) {
            String message = "Не все поля заполненны";
            Employee employee = HandlerEmployee.createTemporaryEmployee(values);
            request.setAttribute("message", message);
            request.setAttribute("employee", employee);
            return "/enterEmployee.jsp";
        }
        HandlerEmployee.saveChangeEmployee(values);
        return "EmployeeAction";
    }
}
