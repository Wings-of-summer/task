package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Employee;
import com.asya.gotovskaya.trainingtask.handler.HandlerEmployee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author asya
 */
public class EmployeeAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Employee> employees = HandlerEmployee.getAllEmployees();
        if(employees == null){
            return "/error.jsp";
        }
        request.setAttribute("employees", employees);
        return "/employee.jsp";
    }
}
