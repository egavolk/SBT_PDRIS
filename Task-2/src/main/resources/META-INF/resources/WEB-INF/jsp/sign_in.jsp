<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
</head>
<body>
    <h1>Sign In</h1>
    <a>${message}</a>
    <form action="sign_in" method="post">
        Username:<input type="text" name="username"/><br/>
        Password:<input type="password" name="password"/><br/>
        <input type="submit" value="Sign in"/>
    </form>
    <a href="sign_up">Sign up</a>
</body>
</html>