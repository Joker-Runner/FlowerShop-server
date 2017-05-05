<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 5/2 0002
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form action="sign_up" method="post">
    <p class="main">
        <label>用户名: </label>
        <input name="username" value="" />
        <label>密码: </label>
        <input type="password" name="password" value="">
    </p>
    <p class="space">
        <input type="submit" value="注册" class="sign_up" style="cursor: pointer;"/>
    </p>
</form>
</body>
</html>
