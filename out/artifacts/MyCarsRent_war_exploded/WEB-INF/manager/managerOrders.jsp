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
    <style><%@include file="/CSS/loginPage.css"%></style>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}CSS/loginPage.css"/>--%>
    <title>Orders</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Orders</h2>

    </div>
</div>

<div class="main">
    <div class="col-md-6 col-sm-12">
        <c:if test="${sessionScope.role>0}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-secondary">Logout</button>
            </form>
        </c:if>

<c:choose>
    <c:when test="${sessionScope.role==3}">
        <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
            <input type="submit" value="back to menu"
                   style="background-color: darkseagreen;border-width: medium;font-weight: bold">
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.request.contextPath}/welcomeManager" method="post">
            <input type="submit" value="back to menu"
                   style="background-color: darkseagreen;border-width: medium;font-weight: bold">
        </form>
    </c:otherwise>
</c:choose>
        <c:set var="counter" value="0" scope="page"/>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td>
                    <ul>
                        <li>Order id: <c:out value="${order.id}"/></li>
                        <li>Car marque: <c:out value="${requestScope.cars.get(counter).marque}"/></li>
                        <li>Car model: <c:out value="${requestScope.cars.get(counter).model}"/></li>
                        <li>Driver: <c:out value="${order.driver}"/></li>
                        <li>Term(hours): <c:out value="${order.term}"/></li>
                        <li>
                            <table>
                                <tr>
                                    <th>
                                        Order status: <c:out value="${order.confirmed}"/>
                                    </th>
                                    <th>
                                        <form action="${pageContext.request.contextPath}/setOrderStatus" method="post">
                                            <select name="orderStatus">
                                                <option value="ON CHECK" selected>ON CHECK</option>
                                                <option value="CONFIRMED">CONFIRMED</option>
                                                <option value="REJECTED">REJECTED</option>
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <input type="submit" value="submit">
                                            </select>
                                        </form>
                                    </th>
                                </tr>
                            </table>
                        </li>
                        <li>
                            <table>
                                <tr>
                                    <th>
                                        <p>
                                            Reason: <u
                                                style="font-weight: normal; color: brown; font-size: large">${order.comment}</u>
                                        </p>
                                    </th>
                                    <th>
                                        <form action="${pageContext.request.contextPath}/setReason" method="post">
                                            <input type="text" name="reason" placeholder="comment">
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <input type="submit" value="set comment">
                                        </form>
                                    </th>

                                </tr>
                            </table>
                        </li>
                        <li>
                            <table>
                                <th>
                                    Penalty: <u
                                        style="font-weight: normal; color: brown; font-size: large">${order.penalty}</u>
                                </th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/setPenalty" method="post">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="number" name="penalty" placeholder="penalty">
                                        <input type="submit" value="set penalty">
                                    </form>
                                </th>
                            </table>
                        </li>
                        <li>Rent cost: <c:out value="${order.rent_cost}"/> <c:out value="USD"/></li>
                        <li>Total cost: <c:out value="${order.rent_cost+order.penalty}"/> <c:out value="USD"/></li>
<%--              plase for button to registrate car return          --%>
                         <c:if test="${order.confirmed.equals('CONFIRMED')}">
                             <form method="post" action="${pageContext.request.contextPath}/returnCar">
                                 <input type="hidden" name="orderId" value="${order.id}">
                                 <input type="hidden" name="clientId" value="${order.client_id}">
                                 <input type="submit" value="get car back">
                             </form>

                         </c:if>
                        <hr>
                    </ul>
                </td>
            </tr>
            <c:set var="counter" value="${counter+1}" scope="page"/>
        </c:forEach>


    </div>
</div>
<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

</body>
</html>
