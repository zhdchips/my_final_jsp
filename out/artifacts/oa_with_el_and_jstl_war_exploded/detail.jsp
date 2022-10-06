<%--部门详情页面，展示具体部门的部门编号、部门名称和部门位置--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详情</title>
	</head>
	<body>
		<h1>部门详情</h1>
		<hr >
		部门编号：${dept.deptno} <br>
		部门名称：${dept.dname} <br>
		部门位置：${dept.loc}<br>
		
		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>