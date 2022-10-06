<%--去登陆界面，被过滤器过滤掉的请求跳转的页面--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请登录</title>
</head>
<body>
    <h1 align="center">
        您尚未登录，请
        <a href="${pageContext.request.contextPath}/index.jsp">登录</a>
    </h1>
</body>
</html>
