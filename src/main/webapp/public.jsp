<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 5/4/2024
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Trang công khai</h1>
<h4>Username : ${username}</h4>
<h4>Password : ${password}</h4>
<c:if test="${status!=null && status == true}">
    <p>Heloo</p>
</c:if>
<a href="/HomeServlet">Toggle</a>

<%--<img src="/uploads/${url}"  alt="#" width="300" height="300" style="object-fit: cover">--%>
<audio src="/uploads/${url}" controls autoplay ></audio>
</body>
</html>
