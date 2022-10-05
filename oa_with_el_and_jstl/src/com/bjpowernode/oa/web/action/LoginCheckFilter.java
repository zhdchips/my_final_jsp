package com.bjpowernode.oa.web.action;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        if ("/toLogin.jsp".equals(servletPath) ||
                "/index.jsp".equals(servletPath) ||
                "/user/login".equals(servletPath) ||
                "/welcome".equals(servletPath) ||
                "/error.jsp".equals(servletPath) ||
                "/false.jsp".equals(servletPath) ||
                session != null && session.getAttribute("user") != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/toLogin.jsp");
        }
    }
}
