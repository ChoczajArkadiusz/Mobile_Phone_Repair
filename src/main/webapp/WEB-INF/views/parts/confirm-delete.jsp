<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Potwierdź usunięcie</title>
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
    <h3>Czy na pewno chcesz usunąć część:</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>Producent</td>
            <td>Model</td>
            <td>Typ</td>
            <td>Opis</td>
            <td>Numer seryjny</td>
            <td>Ilość [szt.]</td>
            <td>Cena [zł]</td>
            <td>Czas wymiany [h]</td>
        </tr>
        </thead>
        <tbody id="employeesTab">
        <tr>
            <td>${toRemove.id}</td>
            <td>${toRemove.manufacturer}</td>
            <td>${toRemove.model}</td>
            <td>${toRemove.type}</td>
            <td>${toRemove.description}</td>
            <td>${toRemove.serialNumber}</td>
            <td>${toRemove.quantity}</td>
            <td>${toRemove.price}</td>
            <td>${toRemove.workHours}</td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <br>
    <table cellpadding="50" cellspacing="20" align="center">
        <tr>
            <td><a href="/parts" class="btn btn-primary">Anuluj</a></td>
            <td><a href="/parts/${toRemove.id}/delete" class="btn btn-danger">Usuń</a></td>
        </tr>
    </table>
</div>
</body>
</html>
