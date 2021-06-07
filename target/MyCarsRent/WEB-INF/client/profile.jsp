<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="my"/>
<%@taglib prefix="ex" uri="/WEB-INF/tlds/myTags" %>
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
    <title>Profile</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h1>${sessionScope.clientName}</h1>
        <form action="${pageContext.request.contextPath}/menu" method="post">
            <button type="submit" class="btn btn-secondary" formmethod="post"><fmt:message key="Back_to_menu"/></button>
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
            <table>
                <th>
                    <c:if test="${sessionScope.role>0}">
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button type="submit" class="btn btn-secondary"><fmt:message key="Logout"/></button>
                        </form>
                    </c:if>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="lang" value="en">
                        <input type="submit" value="en">
                    </form>
                </th>
            </table>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <h1 style="color: darkslategray"><fmt:message key="My_orders"/>:</h1>
            <c:choose>
                <c:when test="${requestScope.orders.size()>0}">
                    <c:set var="counter" value="0" scope="page"/>
                    <table class="fl-table">
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="Marque"/></th>
                            <th><fmt:message key="Model"/></th>
                            <th><fmt:message key="Driver"/></th>
                            <th><fmt:message key="Term"/></th>
                            <th><fmt:message key="Order_status"/></th>
                            <th><fmt:message key="Comment"/></th>
                            <th><fmt:message key="Rent"/></th>
                            <th><fmt:message key="Penalty"/></th>
                            <th><fmt:message key="Total_cost"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).marque}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).model}"/></td>
                                <td>
                                    <fmt:message key="${order.driver}"/>
                                </td>
                                <td><c:out value="${order.term}"/></td>
                                <td>
                                    <fmt:message key="${order.confirmed}"/>
                                </td>
                                <td><c:out value="${order.comment}"/></td>
                                <c:choose>
                                    <c:when test="${sessionScope.lang.equals('en')}">
                                        <td><c:out value="${order.rent_cost}"/><fmt:message key="cur"/></td>
                                        <td><c:out value="${order.penalty}"/><fmt:message key="cur"/></td>
                                        <td><c:out value="${order.total_cost}"/><fmt:message key="cur"/></td>
                                    </c:when>
                                    <c:otherwise>
<%--                                        <td><c:out value="${car.price*27}"/><fmt:message key="cur"/></td>--%>
                                        <td><c:out value="${order.rent_cost*27}"/><fmt:message key="cur"/></td>
                                        <td><c:out value="${order.penalty*27}"/><fmt:message key="cur"/></td>
                                        <td><c:out value="${order.total_cost*27}"/><fmt:message key="cur"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.confirmed.equals('CONFIRMED')}">
                                            <form>
                                                <button type="submit" class="btn btn-secondary"><fmt:message key="Pay"/></button>
                                            </form>
                                        </c:when>
                                        <c:when test="${order.confirmed.equals('ON CHECK')}">
                                            <form action="${pageContext.request.contextPath}/cancelOrder" method="post">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <button type="submit" class="btn btn-secondary"><fmt:message key="cancel"/></button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:set var="counter" value="${counter+1}" scope="page"/>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="redText">You have no orders</p>
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
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="car_class" value="${requestScope.car_class}">
                        <input type="hidden" name="page" value="${i}">
                        <button type="submit" class="btn btn-secondary">${i}</button>
<%--                        <input type="submit" value="${i}">--%>
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-sm-12">
    </div>
    <div class="col-md-6 col-sm-12">
        <div id="bottom">
            <ex:TimeTag/>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>

</body>
</html>
