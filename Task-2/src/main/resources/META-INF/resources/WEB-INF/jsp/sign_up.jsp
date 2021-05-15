<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
</head>
<body>
    <h1>Sign Up</h1>
    <a>${message}</a>
    <form action="sign_up" method="post">
        Username:<input type="text" name="username"/><br/>
        Password:<input type="password" name="password"/><br/>
        <input type="submit" value="Sign Up"/>
    </form>
    <a href="sign_in">Sign in</a>
</body>
</html>