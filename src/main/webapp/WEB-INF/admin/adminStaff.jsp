<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style><%@include file="/CSS/loginPage.css"%></style>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginPage.css"/>--%>
    <title>Admin staff</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <c:choose>
            <c:when test="${sessionScope.role==3}">
                <h1 style="text-decoration: underline">ADMIN</h1>
            </c:when>
            <c:when test="${sessionScope.role==2}">
                <h1 style="text-decoration: underline">MANAGER</h1>
            </c:when>
        </c:choose>
        <br>
        <h2>Admin staff</h2>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <c:if test="${sessionScope.role>0}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-secondary">Logout</button>
            </form>
        </c:if>

            <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                <input type="submit" value="back to menu" style="background-color: darkseagreen;border-width: medium;font-weight: bold">
            </form>
            <c:forEach var="worker" items="${requestScope.staff}">
                <tr>
                    <td>
                        <ul>
                            <li>id: <c:out value="${worker.id}"/></li>
                            <li>login: <c:out value="${worker.login}"/></li>
                            <li>password: <c:out value="${worker.password}"/></li>
                            <li>passport: <c:out value="${worker.passport}"/></li>

                                <c:choose>
                                    <c:when test="${worker.role_id==3}">
                            <li>role: <p style="font-size: x-large"><c:out value="ADMIN"/></p></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>role: <c:out value="${worker.role_id}"/></li>
                                    </c:otherwise>
                                </c:choose>
                            <li>status: <c:out value="${worker.status}"/></li>
                            <form action="${pageContext.request.contextPath}/managers" method="post">
                                <input type="hidden" value="${worker.login}" name="login">
                                <input type="hidden" value="removeManager" name="adminAction">
                                <input type="submit" value="remove rights">
                            </form>
                        </ul>
                    </td>
                </tr>
                <hr>
            </c:forEach>

        <div class="login-form">
        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>