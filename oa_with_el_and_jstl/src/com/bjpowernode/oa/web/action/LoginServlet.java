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

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 先查看请求的参数中是否有用户和密码，如果有，说明是登录界面提交的 post 请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 如果没有，说明是 WelcomeServlet 转发来的 cookie 中的用户和密码
        if (username == null && password == null) {
            username = request.getAttribute("username").toString();
            password = request.getAttribute("password").toString();
        }

        // 查找数据库中，是否有对应的用户和密码
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

        // 如果在数据库中查找成功，则可以登录成功
        if (success) {
            // 查看提交的参数中是否有 "f"，有的话说明用户勾选了十天内免登录，
            // 则向浏览器发送有效时间为 10 天的 cookie
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
            // 登录成功，在会话 Attribute 中 加入当前用户，表示当前会话已登录
            HttpSession session = request.getSession();
            User user = new User(username, password);
            session.setAttribute("user", user);
            // 重定向至页清单页面
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 密码校验出错，登录失败，跳转到登录失败页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 判断当前会话是否过期
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 如果没有过期，则先清除 cookie 中的用户名和密码
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);
                    // 注意清除时要设置 cookie 的范围
                    // 应该是 具有相同名字 和 作用范围 的 cookie 才视为同一个 cookie
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else if (cookie.getName().equals("password")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
//            session.removeAttribute("user");
            // 然后关闭当前 session，会自动清除 Attribute 中的数据
            session.invalidate();
            // 跳转至登录界面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
