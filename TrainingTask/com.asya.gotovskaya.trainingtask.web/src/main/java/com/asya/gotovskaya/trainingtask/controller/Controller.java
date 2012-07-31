package com.asya.gotovskaya.trainingtask.controller;

import com.asya.gotovskaya.trainingtask.action.IAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author asya
 */
public class Controller extends HttpServlet{
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String name = req.getPathInfo();
        name = name.substring(1);
        String viewName = "/error.jsp";
        try{
            name = "com.asya.gotovskaya.trainingtask.action." + name;
            Class c = getClass().getClassLoader().loadClass(name);
            IAction action = (IAction)c.newInstance();
            viewName = action.process(req, resp);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewName);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
