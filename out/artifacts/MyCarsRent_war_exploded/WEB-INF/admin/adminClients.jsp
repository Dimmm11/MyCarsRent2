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

    <link rel="stylesheet" type="text/css" href="../../CSS/loginPage.css"/>
    <title>Admin clients</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Admin clients</h2>
        <%--        <h2><fmt:message key="Welcome"/></h2>--%>
        <%--        <p><fmt:message key="login_register"/></p>--%>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <c:if test="${sessionScope.role>0}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-secondary">Logout</button>
            </form>
        </c:if>
        <div class="login-form">
            <table>
                <tr>
                    <th>
                        <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                            <input type="submit" value="back to menu"
                                   style="background-color: darkseagreen;border-width: medium;font-weight: bold">
                        </form>
                    </th>
                    <th>
                        <%--                        <form action="${pageContext.request.contextPath}/makeManager" method="post">--%>
                        <%--                            <input type="submit" value="add client">--%>
                        <%--                        </form>--%>
                    </th>
                </tr>
            </table>


            <c:forEach var="client" items="${requestScope.adminClients}">
                <%--                <c:set var="client1" value="${client}" scope="page"/>--%>

                <table>
                    <tr>
                        <th>
                            <form method="post" action="${pageContext.request.contextPath}/deleteClient">
                                <input type="hidden" value="${client.login}" name="login">
                                <input type="submit" value="delete" style="background-color: coral;border-width: medium;font-weight: bold">
                            </form>
                        </th>
                        <th>
                            <form action="${pageContext.request.contextPath}/managers" method="post">
                                <input type="hidden" value="${client.login}" name="login">
                                <input type="hidden" value="makeManager" name="adminAction">
                                <input type="submit" value="make manager" style="background-color: mediumseagreen;border-width: medium;font-weight: bold">
                            </form>
                        </th>
                        <th>
                            <c:choose>
                                <c:when test="${client.status.equals('ACTIVE')}">
                                    <form action="${pageContext.request.contextPath}/ban" method="post">
                                        <input type="hidden" value="${client.login}" name="login">
                                        <input type="submit" value="ban"
                                               style="background-color: darksalmon;border-width: medium;font-weight: bold">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/unBan" method="post">
                                        <input type="hidden" value="${client.login}" name="login">
                                        <input type="submit" value="UNBAN"
                                               style="background-color: darksalmon;border-width: medium;font-weight: bold">
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </th>
                    </tr>
                </table>
                <tr>
                    <td>
                        <ul>
                            <li>id: <c:out value="${client.id}"/></li>
                            <li>login: <c:out value="${client.login}"/></li>
                            <li>password: <c:out value="${client.password}"/></li>
                            <li>passport: <c:out value="${client.passport}"/></li>
                            <li>role: <c:out value="${client.role_id}"/></li>
                            <li>status:
                                <c:choose>
                                    <c:when test="${client.status.equals('ACTIVE')}">
                                        <p style="color: darkgreen;font-weight: 600"><c:out
                                                value="${client.status}"/></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="color: brown;font-weight: 600"><c:out value="${client.status}"/></p>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </td>
                </tr>
                <hr>
            </c:forEach>


        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>