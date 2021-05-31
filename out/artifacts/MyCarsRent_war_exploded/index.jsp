<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="my"/>
<%@taglib prefix="ex" uri="/WEB-INF/tlds/myTags" %>
<html>
<body>
<h2>Hello World!</h2>

<a href="login.jsp"><fmt:message key="Login"/></a>

<%--<a href="test.jsp">TEST</a>--%>
<c:if test="${requestScope.error!=null}">
    <c:out value="${requestScope.error.toString()}"/>
</c:if>


<div id="bottom">
    <ex:TimeTag/>
</div>
</body>
</html>
