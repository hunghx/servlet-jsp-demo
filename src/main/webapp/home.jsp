<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 5/4/2024
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach items="${list}" var="str" varStatus="loop">
        <li id="${loop.count}" class="${loop.count%2!=0?'odd':'even'}">${str}</li>
    </c:forEach>
</ul>

<%-- lặp biết trước số lần --%>
    <c:forEach begin="1" end="10" step="1" var="num" varStatus="loop">
        <li>${loop.count}</li>
    </c:forEach>
</body>
</html>
