<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: arek
  Date: 29.01.19
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<p>Test page</p><br>
    <c:if test="${Math.random() > 0.5}" >
        <p>Super!</p>
    </c:if>

<div>
    <sec:authorize access="isAuthenticated()">
        <p>Cześć ${loggedUser} </p>
    </sec:authorize>
    <sec:authorize access="hasRole('MANAGER')">
        <p>Cześć ${loggedUser} </p>
    </sec:authorize>
</div>

</body>
</html>
