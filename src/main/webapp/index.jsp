<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="my"/>
<%@taglib prefix="ex" uri="/WEB-INF/tlds/myTags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <title>Starter page</title>
</head>
<body>
<div style="text-align: center;margin-top: 10%">
    <div class="container-fluid">
        <h2>Cars rent</h2>
        <form action="login.jsp">
            <button type="submit" class="btn btn-secondary"><fmt:message key="Login"/></button>
        </form>
    </div>
    <div>
        <ex:TimeTag/>
            <c:out value="${requestScope.error}"/>
    </div>
</div>

</body>
</html>
