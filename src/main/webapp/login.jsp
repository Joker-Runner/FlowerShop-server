<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 4/11 0011
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login" method="post">
    <p class="main">
        <label>用户名: </label>
        <input name="email" value="" />
        <label>密码: </label>
        <input type="password" name="password" value="">
    </p>
    <p class="space">
        <input type="submit" value="登录" class="login" style="cursor: pointer;"/>
    </p>
</form>
</body>
</html>
