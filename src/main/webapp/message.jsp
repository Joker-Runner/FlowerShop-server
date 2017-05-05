<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 5/4 0004
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传结果</title>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
%>

    <h2><%=message%></h2>
</body>
</html>