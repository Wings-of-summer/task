package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerEmploye;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class DeleteEmployeeAction implements Action {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int answer = HandlerEmploye.deleteEmployee(Integer.parseInt(request.getParameter("id")));
        if(answer != 0){
            String previousPage = "EmployeAction";
            request.setAttribute("previousPage", previousPage);
            return "/successful.jsp";
        }
        return "/error.jsp";
    }
}
