<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="main.java.service.loginPage"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">



<%--    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>
<%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="CSS/loginPage.css"/>
    <title>Login page</title>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Cars rent</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
<%--            <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--                <ul class="navbar-nav me-auto mb-2 mb-lg-0">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Link</a>--%>
<%--                    </li>--%>
<%--                    --%>
<%--                    <li class="nav-item dropdown">--%>
<%--                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"--%>
<%--                           role="button" data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--                            Dropdown--%>
<%--                        </a>--%>
<%--                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                            <li><a class="dropdown-item" href="#">Action</a></li>--%>
<%--                            <li><a class="dropdown-item" href="#">Another action</a></li>--%>
<%--                            <li><hr class="dropdown-divider"></li>--%>
<%--                            <li><a class="dropdown-item" href="#">Something else here</a></li>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
<%--                </ul>--%>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Login page</h2>
<%--        <h2><fmt:message key="Welcome"/></h2>--%>
<%--        <p><fmt:message key="login_register"/></p>--%>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">

            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" name="Login">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Password" name="Password">
                </div>
<%--                <input type="hidden" name="lang" value="<%=request.getParameter("lang")%>">--%>

                <button type="submit" class="btn btn-black">Login</button>
            </form>

            <form>
<%--                <input type="hidden" name="lang" value="<%=request.getParameter("lang")%>">--%>
                <button type="submit" class="btn btn-secondary" formaction="${pageContext.request.contextPath}/register.jsp" formmethod="post">Registration</button>
            </form>

        </div>
    </div>
</div>

<%--<div class="main">--%>
<%--    <div class="container">--%>
<%--        <a style="font-size: large" href="${pageContext.request.contextPath}/welcome.jsp?lang=ru">ru</a>--%>
<%--        <a style="font-size: large" href="${pageContext.request.contextPath}/welcome.jsp?lang=en">en</a>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

<!-- Вариант 1: Bootstrap в связке с Popper -->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>--%>


</body>
</html>