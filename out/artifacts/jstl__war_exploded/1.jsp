<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 14776
  Date: 2022/10/5
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%--%>
<%--    String str1 = "kk";--%>
<%--    String str2 = "mm";--%>
<%--    pageContext.setAttribute("username", str1);--%>
<%--    request.setAttribute("username", str2);--%>
<%--%>--%>

<%--<c:if test="${empty pageScope.username}" var="v" scope="page">--%>
<%--    <h1>用户名不能为空</h1>--%>
<%--</c:if>--%>

<%--<c:if test="${not empty pageScope.username}" var="v" scope="page">--%>
<%--    <h1>欢迎 ${pageScope.username}</h1>--%>
<%--</c:if>--%>

<%--<c:if test="${empty requestScope.username}" var="v" scope="page">--%>
<%--    <h1>用户名不能为空</h1>--%>
<%--</c:if>--%>

<%--<c:if test="${not empty requestScope.username}" var="v" scope="page">--%>
<%--    <h1>欢迎 ${requestScope.username}</h1>--%>
<%--</c:if>--%>

<%--<c:forEach var="i" begin="1" end="10" step="2">--%>
<%--    ${i}--%>
<%--</c:forEach>--%>
<%--<br>--%>
<%--<%--%>
<%--    List<Integer> list = new ArrayList<>();--%>
<%--    list.add(1);--%>
<%--    list.add(2);--%>
<%--    list.add(3);--%>
<%--    request.setAttribute("list", list);--%>
<%--%>--%>

<%--<c:forEach items="${list}" var="v" varStatus="vs">--%>
<%--    ${vs.count}, ${v}--%>
<%--    <br>--%>
<%--</c:forEach>--%>

<c:choose>
    <c:when test="${param.age < 18}">
        青少年
    </c:when>
    <c:when test="${param.age < 35}">
        青年
    </c:when>
    <c:when test="${param.age < 55}">
        中年
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>

