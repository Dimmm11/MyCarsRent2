<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


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
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}CSS/loginPage.css"/>--%>
    <title>Orders</title>

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
        <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
            <input type="submit" value="back to menu"
                   style="background-color: darkseagreen;
                               border-width: medium;
                               font-weight: bold">
        </form>

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
<%-- ===================================================== --%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <c:set var="counter" value="0" scope="page"/>
            <c:choose>
                <c:when test="${requestScope.orders.size()>0}">
                    <h2>Orders:</h2>
                    <table class="fl-table">
                        <tr>
                            <th>Order ID</th>
                            <th>Car marque</th>
                            <th>Car model</th>
                            <th>Driver</th>
                            <th>Term(hours)</th>
                            <th>Order status</th>
                            <th>Comment</th>
                            <th>Penalty</th>
                            <th>Rent</th>
                            <th>Total cost</th>
                        </tr>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).marque}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).model}"/></td>
                                <td><c:out value="${order.driver}"/></td>
                                <td><c:out value="${order.term}"/></td>
                                <td>
                                    <c:out value="${order.confirmed}"/>
                                    <form action="${pageContext.request.contextPath}/setOrderStatus" method="post">
                                        <select name="orderStatus">
                                            <option value="ON CHECK" selected>ON CHECK</option>
                                            <option value="CONFIRMED">CONFIRMED</option>
                                            <option value="REJECTED">REJECTED</option>
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <input type="submit" value="set">
                                        </select>
                                    </form>
                                </td>
                                <td>
                                   <p style="color: brown"><c:out value="${order.comment}"/></p>
                                    <form action="${pageContext.request.contextPath}/setReason" method="post">
                                        <input type="text" name="reason" placeholder="comment" style="width: 100px">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="submit" value="set">
                                    </form>
                                </td>
                                <td>
                                    <c:out value="${order.penalty}"/>
                                    <form action="${pageContext.request.contextPath}/setPenalty" method="post">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="number" name="penalty" placeholder="penalty" style="width: 50px">
                                        <input type="submit" value="set">
                                    </form>
                                </td>
                                <td>
                                    <c:out value="${order.rent_cost}"/>
                                </td>
                                <td>
                                    <c:out value="${order.total_cost}"/>
                                </td>
                            </tr>
                            <c:set var="counter" value="${counter+1}" scope="page"/>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="redText">No orders in DB</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="col-md-2">
    </div>
</div>
<%-- ===================================================== --%>
<div class="main">
    <div class="container" style="position: static;bottom: 30%">
        <table>
            <c:set var="i" value="1" scope="request"/>
            <c:forEach begin="1" end="${requestScope.numPages}">
                <th style="font-size: medium">
                    <form action="${pageContext.request.contextPath}/managerOrders" method="post">
                        <input type="hidden" name="page" value="${i}">
                        <input type="submit" value="${i}">
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>

    </div>
    <div class="col-md-6 col-sm-12">

        <div class="login-form">
        </div>
    </div>
    <div class="col-md-6 col-sm-12">
    </div>
</div>
<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

</body>
</html>
