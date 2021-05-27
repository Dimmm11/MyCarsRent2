<%--
  Created by IntelliJ IDEA.
  User: panda
  Date: 22.05.2021
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" placeholder="login" name="Login">
    <input type="text" placeholder="pass" name="Password">
    <input type="submit" value="submit">
</form>
</body>
</html>
