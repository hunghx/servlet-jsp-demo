<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 5/4/2024
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sach nhan vien</h1>
<a href="/EmployeeServlet?action=ADD">Them moi</a>
<table border="5" cellspacing="10" cellpadding="10">
    <thead>
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Birthday</th>
        <th>Sex</th>
        <th>Avatar</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="em" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${em.name}</td>
            <td>${em.formatDate()}</td>
            <td>${em.sex?"Nam":"Nu"}</td>
            <td><img src="<%=request.getContextPath()%>${em.avatar}"  alt="#" width="100" height="100" style="object-fit: cover"></td>
            <td><a href="">Edit</a></td>
            <td><a href="">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
