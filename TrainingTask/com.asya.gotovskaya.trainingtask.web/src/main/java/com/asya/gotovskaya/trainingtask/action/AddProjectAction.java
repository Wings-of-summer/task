package com.asya.gotovskaya.trainingtask.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asya
 */
public class AddProjectAction implements IAction {
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String addFlag = "add";
        request.setAttribute("addFlag", addFlag);
        return "/enterProject.jsp";
    }
}
