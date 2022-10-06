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

        // 校验当前会话是否登录
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // 若已登录，直接重定向至 清单页面
            response.sendRedirect(request.getContextPath() + "/dept/list");
            return;
        }

        // 查看本地是否有登录缓存
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
            // 若有缓存，直接尝试使用缓存登录，这里通过转发给 LoginServlet 来实现
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher(request.getContextPath() + "/user/login").forward(request, response);
        } else {
            // 若没有缓存，则进入登录界面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
