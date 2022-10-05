package com.bjpowernode.oa.web.action;

import bean.User;
import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet({"/user/login", "/user/exit"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.equals("/user/login")) {
            doLogin(request, response);
        } else if(servletPath.equals("/user/exit")) {
            doExit(request, response);
        }

    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else if (cookie.getName().equals("password")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
//            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null && password == null) {
            username = request.getAttribute("username").toString();
            password = request.getAttribute("password").toString();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean success = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        if (success) {
            String f = request.getParameter("f");
            if (f != null && f.equals("1")) {
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);

                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());

                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            HttpSession session = request.getSession();
            User user = new User(username, password);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }

    }
}