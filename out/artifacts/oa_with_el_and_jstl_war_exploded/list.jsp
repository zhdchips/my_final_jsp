<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
</head>
<body>


<script type="text/javascript">
    function del(deptno) {
        if (window.confirm("亲，删了不可恢复哦!")) {
            document.location.href = "${pageContext.request.contextPath}/dept/delete?deptno=" + deptno;
        }
    }
</script>

<h3>欢迎用户${user.username},当前在线人数${onlineCount}人</h3>
<a href="${pageContext.request.contextPath}/user/exit">[退出系统]</a>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${deptList}" var="v" varStatus="vs">
        <tr>
            <td>${vs.count}
            </td>
            <td>${v.deptno}
            </td>
            <td>${v.dname}
            </td>
            <td>
                <a href="javascript:void(0)" onclick="del(${v.deptno})">删除</a>
                <a href="${pageContext.request.contextPath}/dept/detail?opera=edit&deptno=${v.deptno}">修改</a>
                <a href="${pageContext.request.contextPath}/dept/detail?opera=detail&deptno=${v.deptno}">详情</a>
            </td>
        </tr>
    </c:forEach>

</table>

<h1 align="center">
    <a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>
</h1>
</body>
</html>