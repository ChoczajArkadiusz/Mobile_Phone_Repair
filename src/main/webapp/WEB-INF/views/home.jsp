<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container" align="center">
    <h3>Homepage</h3>

    <div>
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <p> ANONYMOUS </p>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <p> ADMIN </p>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <p> MANAGER </p>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
            <p> EMPLOYEE </p>
        </sec:authorize>

    </div>
</div>
</body>
</html>
