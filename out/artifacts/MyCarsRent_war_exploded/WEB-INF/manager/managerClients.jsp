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
    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <title>Manager clients</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <c:choose>
            <c:when test="${sessionScope.role==3}">
                <h1 style="text-decoration: underline"><fmt:message key="ADMIN"/></h1>
                <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                    <button type="submit" class="btn btn-secondary"><fmt:message key="Back_to_menu"/></button>
                </form>
            </c:when>
            <c:when test="${sessionScope.role==2}">
                <h1 style="text-decoration: underline"><fmt:message key="Manager"/></h1>
                <form action="${pageContext.request.contextPath}/welcomeManager" method="post">
                    <button type="submit" class="btn btn-secondary"><fmt:message key="Back_to_menu"/></button>
                </form>
            </c:when>
        </c:choose>
        <br>





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
                    <form action="${pageContext.request.contextPath}/managerClients" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/managerClients" method="post">
                        <input type="hidden" name="lang" value="en">
                        <input type="submit" value="en">
                    </form>
                </th>
            </table>
        </div>
    </div>
</div>
<%-- =============================================================--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <c:choose>
                <c:when test="${requestScope.adminClients.size()>0}">
                    <h2><fmt:message key="Clients"/>:<u style="text-decoration: underline">${requestScope.car_class}</u>
                    </h2>
                    <table class="fl-table">
                        <tr>
                            <th>Id</th>
                            <th><fmt:message key="Login"/></th>
                            <th><fmt:message key="Password"/></th>
                            <th><fmt:message key="Passport"/></th>
                            <th><fmt:message key="Status"/></th>
                            <th></th>  <!-- make manager -->
                            <th></th>  <!-- delete -->
                            <th></th>    <!-- ban -->
                        </tr>
                        <c:forEach var="client" items="${requestScope.adminClients}">
                            <tr>
                                <td><c:out value="${client.id}"/></td>
                                <td><c:out value="${client.login}"/></td>
                                <td><c:out value="${client.password}"/></td>
                                <td><c:out value="${client.passport}"/></td>
                                <td>
                                    <fmt:message key="${client.status}"/>
                                        <%--                                    <c:out value="${client.status}"/>--%>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${client.status.equals('ACTIVE')}">
                                            <form action="${pageContext.request.contextPath}/ban" method="post">
                                                <input type="hidden" value="${client.login}" name="login">
                                                <button type="submit" class="btn btn-secondary"><fmt:message
                                                        key="Ban"/></button>
                                                    <%--                                                <input type="submit" value="ban" style="background-color: darksalmon;border-width: medium;font-weight: bold">--%>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="${pageContext.request.contextPath}/unBan" method="post">
                                                <input type="hidden" value="${client.login}" name="login">
                                                <button type="submit" class="btn btn-secondary"><fmt:message
                                                        key="unban"/></button>
                                                    <%--                                                <input type="submit" value="UNBAN" style="background-color: darksalmon;border-width: medium;font-weight: bold">--%>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.role==3}">
                                        <form method="post" action="${pageContext.request.contextPath}/deleteClient">
                                            <input type="hidden" value="${client.login}" name="login">
                                            <button type="submit" class="btn btn-secondary"><fmt:message
                                                    key="Delete"/></button>
                                                <%--                                            <input type="submit" value="delete" style="background-color: coral;border-width: medium;font-weight: bold">--%>
                                        </form>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.role==3}">
                                        <form action="${pageContext.request.contextPath}/setRights" method="post">
                                            <input type="hidden" value="${client.login}" name="login">
                                            <input type="hidden" value="makeManager" name="adminAction">
                                            <button type="submit" class="btn btn-secondary"><fmt:message
                                                    key="Make_manager"/></button>
                                                <%--                                            <input type="submit" value="make manager" style="background-color: mediumseagreen;border-width: medium;font-weight: bold">--%>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="redText">No clients in DB</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="col-md-2">
    </div>
</div>


<%-- =============================================================--%>
<div class="main">
    <div class="container" style="position: static;bottom: 30%">
        <table>
            <c:set var="i" value="1" scope="request"/>
            <c:forEach begin="1" end="${requestScope.numPages}">
                <th style="font-size: medium">
                    <form action="${pageContext.request.contextPath}/managerClients" method="post">
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