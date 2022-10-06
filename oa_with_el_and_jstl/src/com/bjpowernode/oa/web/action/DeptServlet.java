package com.bjpowernode.oa.web.action;

import bean.Dept;
import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/dept/list", "/dept/detail", "/dept/delete", "/dept/add", "/dept/edit", "/dept/modify"})
public class DeptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.equals("/dept/list")) {
            doList(request, response);
        } else if (servletPath.equals("/dept/detail")) {
            doDetail(request, response);
        } else if (servletPath.equals("/dept/delete")) {
            doDel(request, response);
        } else if (servletPath.equals("/dept/add")) {
            doAdd(request, response);
        } else if (servletPath.equals("/dept/modify")) {
            doModify(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dept> deptList = new ArrayList<>();

        // 在数据库中查找所有的部门信息，并将它们存入 list 中
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);
                deptList.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 将含有部门信息的 list 转发给 清单页面
        request.setAttribute("deptList", deptList);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String deptno = request.getParameter("deptno");
        PrintWriter out = response.getWriter();
        Dept dept = new Dept();

        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept where deptno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String no = rs.getString("deptno");
                String name = rs.getString("dname");
                String loc = rs.getString("loc");
                dept.setDeptno(no);
                dept.setDname(name);
                dept.setLoc(loc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        request.setAttribute("dept", dept);
        String opera = request.getParameter("opera");
        request.getRequestDispatcher("/" + opera + ".jsp").forward(request, response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 取出 请求中的部门编号
        String deptno = request.getParameter("deptno");

        // 根据部门编号，在数据库中删除相应的部门
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from dept where deptno = ?;";
        int rows = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rows = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (rows == 1) {
            // 如果删除成功，跳转至 页面展示
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 如果删除失败，跳转至 false.jsp 操作失败页面
//            request.getRequestDispatcher("/false.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/false.jsp");
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        // 获取请求提交的参数
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 在数据库中添加 新的部门的数据
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (rows == 1) {
            // 如果添加成功，跳转到 部门展示
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 如果添加失败，false.jsp 跳转到 操作失败页面
//            request.getRequestDispatcher("/false.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/false.jsp");
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求中 修改后的部门信息
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 在数据库中，修改部门的信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        if (rows == 1) {
            // 如果修改成功，跳转到 部门展示
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 如果修改失败，跳转到 false.jsp 操作失败页面
//            request.getRequestDispatcher("/false.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/false.jsp");
        }
    }
}
