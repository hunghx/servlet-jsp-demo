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
<%--Nhúng jsp--%>
<jsp:include page="../layout/header.jsp"/>

<h1>Không công khai</h1>
<p>Tên : ${name}</p>
<span>Tuổi : ${age}</span>
<p>Ngày sinh : ${dob}</p>



<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
