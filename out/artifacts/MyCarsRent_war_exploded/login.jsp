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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <title>Login page</title>

</head>
<body>
<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key="Welcome"/></h2>
    </div>
</div>
<div class="main">
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a style="font-size: xx-large" class="navbar-brand" href="#"><fmt:message key="Cars_rent"/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </header>
<%--    ===========================--%>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
                <table>
                    <th>
                    </th>
                    <th>
                        <form action="${pageContext.request.requestURI}" method="post">
                            <input type="hidden" name="lang" value="ru">
                            <input type="submit" value="ru">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.requestURI}" method="post">
                            <input type="hidden" name="lang" value="en">
                            <input type="submit" value="en">
                        </form>
                    </th>
                </table>
            </div>
        </div>
    </div>
<%--    ===========================--%>
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="<fmt:message key="Username"/>" name="Login">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="<fmt:message key="Password"/>" name="Password">

                    <c:if test="${sessionScope.loginError!=null}">
                        <c:out value="${sessionScope.loginError}"/>
                    </c:if>


                </div>
                <button type="submit" class="btn btn-black">
                    <fmt:message key="Login"/>
                </button>
            </form>
            <form>
                <button type="submit"
                        class="btn btn-secondary"
                        formaction="${pageContext.request.contextPath}/register.jsp"
                        formmethod="post" name="lang" value="${sessionScope.lang}">
                    <fmt:message key="Registration"/>
                </button>
            </form>
        </div>
    </div>
    <div class="col-md-6 col-sm-12">
        <div id="bottom">
            <ex:TimeTag/>
        </div>
    </div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

<!-- Вариант 1: Bootstrap в связке с Popper -->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>--%>


</body>
</html>