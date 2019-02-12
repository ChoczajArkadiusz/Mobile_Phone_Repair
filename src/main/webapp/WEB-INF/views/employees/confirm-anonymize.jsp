<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Pracownicy - Potwierdź anonimizację</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:if test="${deleteError} != null">
    <p>${deleteError}</p>
</c:if>

<div class="container" align="center">
    <h3>Czy na pewno chcesz zanonimizować dane pracownika:</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>Imię</td>
            <td>Naziwsko</td>
            <td>Email</td>
            <td>Telefon</td>
            <td>Adres</td>
            <td>Grupy</td>
            <td>Koszt roboczogodziny</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${toAnonymize.id}</td>
            <td>${toAnonymize.firstName}</td>
            <td>${toAnonymize.lastName}</td>
            <td>${toAnonymize.email}</td>
            <td>${toAnonymize.phone}</td>
            <td>${toAnonymize.address}</td>
            <td>
                <c:forEach items="${employee.roles}" var="role" varStatus="j">
                    ${role.role.name()}<br>
                </c:forEach>
            </td>
            <td>${toAnonymize.workHourCost}</td>
        </tr>
        </tbody>
    </table>
    <div>
        <p class="alert alert-danger" role="alert"><strong>Anonimizacja danych użytkownika spowoduje bezpowrotne
            zastąpinie jego danych osobowych losową wartością.</strong></p>
    </div>
    <table>
        <tr>
            <td><a href="/employees/${toAnonymize.id}/details" class="btn btn-primary" style="margin: 10px">Anuluj</a></td>
            <td><a href="/employees/${toAnonymize.id}/anonymize" class="btn btn-danger" style="margin: 10px">Anonimizuj</a></td>
        </tr>
    </table>
</div>
</body>
</html>
