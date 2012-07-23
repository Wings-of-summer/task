package com.asya.gotovskaya.trainingtask.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Asya
 * Date: 16.07.12
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public interface Action {
    public String process(HttpServletRequest request, HttpServletResponse response)
          throws ServletException;
}
