<%--部门修改页面，提供待修改部门信息的表单--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改部门</title>
	</head>
	<body>
		<h1>修改部门</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/dept/modify" method="get">
			部门编号<input type="text" name="deptno" value="${dept.deptno}" readonly /><br>
			部门名称<input type="text" name="dname" value="${dept.dname}"/><br>
			部门位置<input type="text" name="loc" value="${dept.loc}"/><br>
			<input type="submit" value="修改"/><br>
		</form>
	</body>
</html>