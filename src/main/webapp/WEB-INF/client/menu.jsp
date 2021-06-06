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
    <style><%@include file="/CSS/loginPage.css"%></style>
    <title><fmt:message key="Menu"/></title>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key="Welcome"/>, <c:out value="${sessionScope.clientName}"/></h2>
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
                    <form action="${pageContext.request.contextPath}/menu" method="post">
                        <input type="hidden" name="lang" value="ru">
                        <input type="submit" value="ru">
                    </form>
                </th>
                <th>
                    <form action="${pageContext.request.contextPath}/menu" method="post">
                        <input type="hidden" name="lang" value="en">
                        <input type="submit" value="en">
                    </form>
                </th>
            </table>
        </div>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <form action="${pageContext.request.contextPath}/carsByClass" method="post" style="font-family: 'Times New Roman';font-size: 150%">
            <tr>
                <td><fmt:message key="Cars_by_class"/>: </td>
                <td>
                    <select name="car_class">
                        <option value="econom" selected>econom</option>
                        <option value="middle">middle</option>
                        <option value="business">business</option>
                    </select>
                </td>
            </tr>
            <button type="submit" class="btn btn-secondary"><fmt:message key="Submit"/></button>
        </form>
        <form action="${pageContext.request.contextPath}/carsByMarque" method="post" style="font-family: 'Times New Roman';font-size: 150%">
            <tr>
                <td><fmt:message key="Cars_by_marque"/>: </td>
                <td>
                    <select name="marque">
                        <option value="Audi" selected>Audi</option>
                        <option value="Chevrolet">Chevrolet</option>
                        <option value="Ford">Ford</option>
                        <option value="Honda">Honda</option>
                        <option value="Hyundai">Hyundai</option>
                        <option value="Nissan">Nissan</option>
                        <option value="Peugeot">Peugeot</option>
                        <option value="Renault">Renault</option>
                        <option value="Skoda">Skoda</option>
                        <option value="Suzuki">Suzuki</option>
                        <option value="Toyota">Toyota</option>
                        <option value="Volkswagen">Volkswagen</option>
                        <option value="Bentley">Bentley</option>
                        <option value="Supercar">Supercar</option>
                    </select>
                </td>
            </tr>
            <button type="submit" class="btn btn-secondary"><fmt:message key="Submit"/></button>
        </form>
    </div>
    <div class="col-md-6 col-sm-12">
        <div id="bottom">
            <ex:TimeTag/>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
</body>
</html>
