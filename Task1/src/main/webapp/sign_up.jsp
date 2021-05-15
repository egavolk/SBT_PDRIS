<%--
  Created by IntelliJ IDEA.
  User: egavolk
  Date: 07.12.2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
</head>
<body>
<h1>Sign Up</h1>
<form action="sign_up" method="get">
    Username:<input type="text" name="username"/><br/>
    Password:<input type="password" name="password"/><br/>
    <input type="submit" value="Sign Up"/>
</form>
<a href="sign_in.jsp">Sign in</a>
</body>
</html>
