package com.asya.gotovskaya.trainingtask.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author asya
**/
public class MainAction implements IAction {

    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return "/main.jsp";
    }
}
