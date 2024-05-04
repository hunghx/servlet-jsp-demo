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
<h1>Add employee</h1>
<form action="/EmployeeServlet" method="post" enctype="multipart/form-data">
<table border="1" >
    <tr>
        <th>Name</th>
        <td><input type="text" name="name"></td>
    </tr>
    <tr>
        <th>BirthDay</th>
        <td><input type="date" name="dob"></td>
    </tr>
    <tr>
        <th>Sex</th>
        <td>
            <input type="radio" checked name="sex" value="true"><span>Nam</span><br>
            <input type="radio" name="sex" value="false"><span>Nu</span>
        </td>
    </tr>
    <tr>
        <th>Avatar</th>
        <td><input type="file" name="file"></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit" name="action" value="ADD"></td>
    </tr>
</table>
</form>
</body>
</html>
