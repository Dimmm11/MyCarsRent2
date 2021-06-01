<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="my"/>
<%@taglib prefix="ex" uri="/WEB-INF/tlds/myTags" %>
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
    <title>Admin staff</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h1 style="text-decoration: underline"><fmt:message key="Admin"/></h1>
        <br>
        <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
            <button type="submit" class="btn btn-secondary"><fmt:message key="Back_to_menu"/></button>
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
                    <form action="${pageContext.request.contextPath}/adminStaff" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/adminStaff" method="post">
                        <input type="hidden" name="lang" value="en">
                        <input type="submit" value="en">
                    </form>
                </th>
            </table>
        </div>
    </div>
</div>
<%-- ===================================== --%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <c:choose>
                <c:when test="${requestScope.staff.size()>0}">
                    <h2><fmt:message key="Staff"/>:</h2>
                    <table class="fl-table">
                        <tr>
                            <th>Id</th>
                            <th><fmt:message key="Login"/></th>
                            <th><fmt:message key="Password"/></th>
                            <th><fmt:message key="Passport"/></th>
                            <th><fmt:message key="Role"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="worker" items="${requestScope.staff}">
                            <tr>
                                <td><c:out value="${worker.id}"/></td>
                                <td><c:out value="${worker.login}"/></td>
                                <td><c:out value="${worker.password}"/></td>
                                <td><c:out value="${worker.passport}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${worker.role_id==3}">
                                            <p style="font-size: large;color: darkred"><fmt:message key="ADMIN"/></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p style="font-size: large;color: darkslategray"><fmt:message key="Manager"/></p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/setRights" method="post">
                                        <input type="hidden" value="${worker.login}" name="login">
                                        <input type="hidden" value="removeManager" name="adminAction">
                                        <button type="submit" class="btn btn-secondary"><fmt:message key="Remove_rights"/></button>
                                    </form>
                                <td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
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
                    <form action="${pageContext.request.contextPath}/adminStaff" method="post">
                        <input type="hidden" name="page" value="${i}">
                        <button type="submit" class="btn btn-secondary">${i}</button>
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-sm-12">
        <div id="bottom">
            <ex:TimeTag/>
        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>