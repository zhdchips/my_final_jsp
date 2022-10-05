package com.bjpowernode.javaweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

public class Filter1 implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter1 begin~");
        chain.doFilter(request, response);
        System.out.println("Filter1 end~");
    }

    @Override
    public void destroy() {
    }
}
