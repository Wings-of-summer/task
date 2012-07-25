package com.asya.gotovskaya.trainingtask.action;

import com.asya.gotovskaya.trainingtask.handler.HandlerEmploye;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class AddEmployeeAction implements Action {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String lastName = request.getParameter("lastName");
        String name = request.getParameter("name");
        String middleName = request.getParameter("middleName");
        String post = request.getParameter("post");
        if(lastName == null && name == null && middleName == null && post == null){
            return "/addEmployee.jsp";
        }
        if(lastName == null || name == null || middleName == null || post == null){
            String message = "Не все поля заполненны";
            request.setAttribute("newLastName", lastName);
            request.setAttribute("newName", name);
            request.setAttribute("newMiddleName", middleName);
            request.setAttribute("newPost", post);
            return "/addEmployee.jsp";
        }
        int answer = HandlerEmploye.addEmployee(lastName, name, middleName, post);
        if(answer != 0){
            String previousPage = "EmployeAction";
            request.setAttribute("previousPage", previousPage);
            return "/successful.jsp";
        }
        return "/error.jsp";
    }
}
