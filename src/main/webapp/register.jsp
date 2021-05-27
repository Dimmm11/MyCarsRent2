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
    <style><%@include file="/CSS/loginPage.css"%></style>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginPage.css"/>--%>
    <title>Registration</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Registration</h2>
        <%--        <h2><fmt:message key="Welcome"/></h2>--%>
        <%--        <p><fmt:message key="login_register"/></p>--%>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Login" name="Login">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Password" name="Password">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Passport" name="Passport">
                </div>
                <table>
                    <tr>
                        <th>
                            <button type="submit" class="btn btn-secondary"
                                    formaction="${pageContext.request.contextPath}/register"
                                    formmethod="post">Submit</button>
                        </th>
                        <th>
                            <button type="submit" class="btn btn-secondary"
                                    formaction="${pageContext.request.contextPath}/login.jsp"
                                    formmethod="post">Login</button>
                        </th>
                    </tr>
                </table>
                <c:if test="${requestScope.error!=null}">
                    <c:out value="${requestScope.error.toString()}"/>
                </c:if>



            </form>
            <c:if test="${requestScope.fail!=null}">
                <c:out value="${requestScope.fail.toString()}"/>
            </c:if>

        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>