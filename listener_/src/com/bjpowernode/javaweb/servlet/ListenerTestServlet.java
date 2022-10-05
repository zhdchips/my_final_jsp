package com.bjpowernode.javaweb.servlet;

import bean.User1;
import bean.User2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/test")
public class ListenerTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User1 kk = new User1("1", "kk", "123");
        User2 mm = new User2("2", "mm", "321");

        HttpSession session = request.getSession();

        session.setAttribute("kk", kk);
        session.setAttribute("mm", mm);

        session.setAttribute("kk", kk);
        session.removeAttribute("mm");

        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
