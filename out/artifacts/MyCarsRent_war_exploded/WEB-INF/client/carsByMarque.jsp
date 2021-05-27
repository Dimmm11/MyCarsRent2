<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="main.java.service.loginPage"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style><%@include file="/CSS/loginPage.css"%></style>
<%--    <link rel="stylesheet" type="text/css" href="CSS/loginPage.css"/>--%>
    <title>Cars by marque</title>

</head>
<body>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Cars by marque</h2>

    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <c:if test="${sessionScope.role>0}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-secondary">Logout</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.client!=null}">
            <form action="profile">
                <button type="submit" class="btn btn-secondary" formmethod="post">Profile</button>
            </form>
        </c:if>
        <form action="${pageContext.request.contextPath}/menu" method="post">
            <input type="submit" value="back to menu" style="background-color: darkseagreen;border-width: medium;font-weight: bold">
        </form>
        <h2><c:out value="${requestScope.marque.toString()}"/></h2>
        <c:forEach var="car" items="${requestScope.carsByMarque}">
            <tr>
                <td>
                    <ul>
                        <li><c:out value="${car.model}"/></li>
                        <li><c:out value="${car.price}"/></li>
                        <hr>
                    </ul>
                </td>
                <td>
<%--               ===========================================     --%>
                    <form action="${pageContext.request.contextPath}/order">
                        <input type="hidden" name="id" value="${car.id}">
                        <input type="hidden" name="marque" value="${car.marque}">
                        <input type="hidden" name="model" value="${car.model}">
                        <input type="hidden" name="price" value="${car.price}">

                <td>Need driver?</td>
                <td>
                    <input type="radio" name="driver" value="yes">yes</input>
                    <input type="radio" name="driver" value="no">no</input>
                </td>
                <br>
                <br>
                <input type="number" min="1" name="term" placeholder="term">
                <input type="submit" value="make order">
                </form>
<%--                    <form action="order">--%>
<%--                        <input type="hidden" name="id" value="${car.id}">--%>
<%--                        <input type="hidden" name="marque" value="${car.marque}">--%>
<%--                        <input type="hidden" name="model" value="${car.model}">--%>
<%--                        <input type="hidden" name="price" value="${car.price}">--%>
<%--                        <input type="submit" value="make order">--%>
<%--                    </form>--%>
                </td>
            </tr>
        </c:forEach>

    </div>
</div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

</body>
</html>
