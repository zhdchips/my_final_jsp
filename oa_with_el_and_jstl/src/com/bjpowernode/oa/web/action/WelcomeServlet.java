package com.bjpowernode.oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
            return;
        }

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }


        if (username != null && password != null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher(request.getContextPath() + "/user/login").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
