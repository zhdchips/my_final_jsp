<%--登录页面，提供待填写用户和密码的表单--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>用户登录</title>
	</head>
	<body>
		<h3>
			<a>欢迎来到 kk 的第一个 web 小项目~</a>
		</h3>
		<h1 align="center">
			用户登录<br>
		</h1>
		<hr>
        <h1 align="center">
            <form action="${pageContext.request.contextPath}/user/login" method="post">
                username: <input type="text" name="username"><br>
                password: <input type="password" name="password"><br>
				<input type="checkbox" name="f" value="1">十天内免登录<br>
                <input type="submit" value="login">
            </form>
        </h1>
	</body>
</html>