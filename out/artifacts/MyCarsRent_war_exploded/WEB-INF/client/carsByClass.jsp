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

    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <title>Cars by class</title>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Cars</h2>

    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4">
            <c:if test="${sessionScope.client!=null}">
                <form action="profile">
                    <button type="submit" class="btn btn-secondary" formmethod="post">Profile</button>
                </form>
            </c:if>
            <form action="${pageContext.request.contextPath}/menu" method="post">
                <input type="submit" value="back to menu" style="background-color: darkseagreen;border-width: medium;font-weight: bold">
            </form>
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
                <c:when test="${requestScope.carsByClass.size()>0}">
                    <h2>Class:<u style="text-decoration: underline">${requestScope.car_class}</u></h2>
                    <table class="fl-table">
                        <tr>
                            <th>Marque</th>
                            <th>Model</th>
                            <th>Price</th>
                            <th>Driver</th>
                            <th>Term</th>
                            <th></th>

                        </tr>
                        <c:forEach var="car" items="${requestScope.carsByClass}">
                            <tr>
                                <form action="${pageContext.request.contextPath}/order">
                                    <td><c:out value="${car.marque}"/></td>
                                    <td><c:out value="${car.model}"/></td>
                                    <td><c:out value="${car.price}"/></td>

                                    <input type="hidden" name="id" value="${car.id}">
                                    <input type="hidden" name="marque" value="${car.marque}">
                                    <input type="hidden" name="model" value="${car.model}">
                                    <input type="hidden" name="price" value="${car.price}">
                                    <td><input type="radio" name="driver" value="yes">yes</input>
                                        <input type="radio" name="driver" value="no">no</input> </td>

                                    <td><input type="number" min="1" name="term" placeholder="term" style="width: 70px">
                                    </td>
                                    <td><input type="submit" value="make order"></td>

                                </form>
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
    <div class="container" style="position: static; bottom: 30%">
        <table>
            <c:set var="i" value="1" scope="page"/>
            <c:forEach begin="1" end="${requestScope.numPages}">
                <th style="font-size: medium">
                    <form action="${pageContext.request.contextPath}/carSelect" method="post">
                        <input type="hidden" name="car_class" value="${requestScope.car_class}">
                        <input type="hidden" name="page" value="${i}">
                        <input type="submit" value="${i}">
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-sm-12">
    </div>

</div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

</body>
</html>
