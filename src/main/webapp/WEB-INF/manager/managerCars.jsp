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
    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginPage.css"/>--%>
    <title>Cars</title>

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
        <c:choose>
            <c:when test="${sessionScope.role==3}">
                <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                    <input type="submit" value="back to menu"
                           style="background-color: darkseagreen;
                               border-width: medium;
                               font-weight: bold">
                </form>
<%--                <jsp:include page="../admin/carAdd.jsp"/>--%>

            </c:when>
            <c:when test="${sessionScope.role==2}">
                <form action="${pageContext.request.contextPath}/welcomeManager" method="post">
                    <input type="submit" value="back to menu"
                           style="background-color: darkseagreen;
                               border-width: medium;
                               font-weight: bold">
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <c:if test="${sessionScope.role>0}">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="btn btn-secondary">Logout</button>
                </form>
            </c:if>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <c:choose>
                <c:when test="${requestScope.allCars.size()>0}">
                    <h2>Cars:<u style="text-decoration: underline">${requestScope.car_class}</u></h2>
                    <table class="fl-table">
                        <tr>
                            <th>Id</th>
                            <th>Marque</th>
                            <th>Car class</th>
                            <th>Model</th>
                            <th>Price</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="car" items="${requestScope.allCars}">
                            <tr>
                                <td><c:out value="${car.id}"/></td>
                                <td><c:out value="${car.marque}"/></td>
                                <td><c:out value="${car.clazz}"/></td>
                                <td><c:out value="${car.model}"/></td>
                                <td><c:out value="${car.price}"/></td>
                                <td>
                                    <form method="post" action="updatePrice">
                                        <input type="hidden" name="id" value="${car.id}">
                                        <input type="number" placeholder="price" name="price" style="width: 70px">
                                        <input type="submit" value="update">
                                    </form>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.role==3}">
                                            <form action="${pageContext.request.contextPath}/adminDeleteCar">
                                                <input type="hidden" name="carId" value="${car.id}">
                                                <input type="submit" value="delete car">
                                            </form>
                                        </c:when>
                                        <c:when test="${sessionScope.role==2}">
                                            . . .
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="redText">Sorry, no available cars</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="col-md-2">
    </div>

</div>
<div class="main">
    <div class="container" style="position: static;bottom: 30%">
        <table>
            <c:set var="i" value="1" scope="request"/>
            <c:forEach begin="1" end="${requestScope.numPages}">
                <th style="font-size: medium">
                    <form action="${pageContext.request.contextPath}/managerCars" method="post">
                        <input type="hidden" name="page" value="${i}">
                        <input type="submit" value="${i}">
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>
    </div>
    <c:if test="${sessionScope.role==3}">
            <form method="post" action="${pageContext.request.contextPath}/caradd">
                <input type="text" placeholder="marque" name="marque">
                <input type="text" placeholder="class" name="car_class">
                <input type="text" placeholder="model" name="model">
                <input type="number" placeholder="price" name="price">
                <input type="submit" value="add car">
            </form>
    </c:if>
    <div class="col-md-6 col-sm-12">
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