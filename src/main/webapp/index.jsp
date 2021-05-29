<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="my"/>
<html>
<body>
<h2>Hello World!</h2>

<a href="login.jsp?lang=en"><fmt:message key="Login"/></a>
<%--<a href="test.jsp">TEST</a>--%>
<c:if test="${requestScope.error!=null}">
    <c:out value="${requestScope.error.toString()}"/>
</c:if>



</body>
</html>
