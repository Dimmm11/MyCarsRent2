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
    <title><fmt:message key="Cars_by_class"/></title>
</head>
<body>
<div class="sidenav">
    <div class="login-main-text">
        <form action="${pageContext.request.contextPath}/menu" method="post">
            <button type="submit" class="btn btn-secondary"><fmt:message key="Back_to_menu"/></button>
        </form>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4">
            <c:if test="${sessionScope.client!=null}">
                <form action="profile">
                    <button type="submit" class="btn btn-secondary" formmethod="post"><fmt:message key="My_orders"/></button>
                </form>
            </c:if>
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
                    <form action="${pageContext.request.contextPath}/carsByClass" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/carsByClass" method="post">
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
            <c:choose>
                <c:when test="${requestScope.carsByClass.size()>0}">
                    <form action="${pageContext.request.contextPath}/carsByClass" method="post">
                        <select name="column">
                            <option value="price"><fmt:message key="Price"/></option>
                            <option value="marque"><fmt:message key="Marque"/></option>
                        </select>
                        <input type="radio" name="sortOrder" value="ASC"><fmt:message key="ASC"/></input>
                        <input type="radio" name="sortOrder" value="DESC"><fmt:message key="DESC"/></input>
                        <input type="submit" value="<fmt:message key="sort"/>">
                    </form>
                    <h2><fmt:message key="Car_class"/>:<u style="text-decoration: underline">${requestScope.car_class}</u></h2>
                    <table class="fl-table">
                        <tr>
                            <th><fmt:message key="Marque"/></th>
                            <th><fmt:message key="Model"/></th>
                            <th><fmt:message key="Price"/></th>
                            <th><fmt:message key="Driver"/></th>
                            <th><fmt:message key="Term"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="car" items="${requestScope.carsByClass}">
                            <tr>
                                <form action="${pageContext.request.contextPath}/order" method="post">
                                    <td><c:out value="${car.marque}"/></td>
                                    <td><c:out value="${car.model}"/></td>

                                    <c:choose>
                                        <c:when test="${sessionScope.lang.equals('en')}">
                                            <td><c:out value="${car.price}"/><fmt:message key="cur"/></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><c:out value="${car.price*27}"/><fmt:message key="cur"/></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <input type="hidden" name="id" value="${car.id}">
                                    <input type="hidden" name="marque" value="${car.marque}">
                                    <input type="hidden" name="model" value="${car.model}">
                                    <input type="hidden" name="price" value="${car.price}">
                                    <td>
                                        <input type="radio" name="driver" value="yes"><fmt:message key="YES"/></input>
                                        <input type="radio" name="driver" value="no"><fmt:message key="NO"/></input>
                                    </td>
                                    <td>
                                        <input type="number" min="1" max="24*30" name="term" placeholder="<fmt:message key="Term"/>" style="width: 70px">
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-secondary"><fmt:message key="make_order"/></button>
                                    </td>
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
                    <form action="${pageContext.request.contextPath}/carsByClass" method="post">
                        <input type="hidden" name="car_class" value="${requestScope.car_class}">
                        <input type="hidden" name="page" value="${i}">
                        <button type="submit" class="btn btn-secondary">${i}</button>
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
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
</body>
</html>
