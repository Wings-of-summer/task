package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.entity.Employe;
import com.asya.gotovskaya.trainingtask.handler.HandlerEmploye;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author asya
 */
public class EmployeAction implements Action {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Employe> employees = HandlerEmploye.getAllEmployees();
        if(employees == null){
            return "/error.jsp";
        }
        request.setAttribute("employees", employees);
        if (request.getParameter("selectedId") != null) {
            int selectedId = Integer.parseInt(request.getParameter("selectedId"));
            Employe employee = HandlerEmploye.createEmployee(selectedId);
            request.setAttribute("selectedEmployee", employee);
        }
        return "/employee.jsp";
    }
}
