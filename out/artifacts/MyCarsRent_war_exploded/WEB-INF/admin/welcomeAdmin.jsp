<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="my"/>
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
    <title>Admin page</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key="Welcome"/>,
            <fmt:message key="Admin"/>!</h2>
    </div>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="col-md-4">
        </div>

        <div class="col-md-4">
        </div>

        <div class="col-md-4">

            <table>
                <th>
                    <c:if test="${sessionScope.role>0}">
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Logout"/></button>
                        </form>
                    </c:if>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                        <input type="hidden" name="lang" value="en">
                        <input type="submit" value="en">
                    </form>
                </th>
            </table>
        </div>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <hr>
            <table>
<%--                <caption>Welcome, Admin!</caption>--%>
                <tr>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerCars" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Cars"/></button>
<%--                            <input type="submit" value="all cars">--%>
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerClients" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Clients"/></button>
<%--                            <input type="submit" value="all users">--%>
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/adminStaff" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Staff"/></button>
<%--                            <input type="submit" value="staff">--%>
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerOrders" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Orders"/></button>
<%--                            <input type="submit" value="orders">--%>
                        </form>
                    </th>
                </tr>
            </table>
            <hr>
        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>