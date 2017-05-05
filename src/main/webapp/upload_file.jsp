<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 5/3 0003
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="create_flower" method="POST" enctype="multipart/form-data" accept-charset="UTF-8" onsubmit="document.en">
    标题：<input type="text" name="title"><br>
    描述：<input type="text" name="introduction"><br>
    价格：<input type="number" name="price"><br>
    图片：<input type="file" name="file" accept="image/*"><br>
    <input type="submit" value="提交"  >
</form>
</body>
</html>
