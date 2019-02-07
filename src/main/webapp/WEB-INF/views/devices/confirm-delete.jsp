<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Urządzenia - Potwierdź usunięcie</title>
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
    <h3>Czy na pewno chcesz usunąć urządzenie:</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>Producent</td>
            <td>Model</td>
            <td>Opis</td>
            <td>Właściciel</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${toRemove.id}</td>
            <td>${toRemove.manufacturer}</td>
            <td>${toRemove.model}</td>
            <td>${toRemove.description}</td>
            <td>${toRemove.owner}</td>
        </tr>
        </tbody>
    </table>
    <div>
        <p class="alert alert-danger" role="alert"><strong>Usunięcie urządzenia spowoduje również usunięcie wszystkich
            zadań przypisanych do tego urządzenia.</strong></p>
    </div>
    <table>
        <tr>
            <td><a href="/devices" class="btn btn-primary" style="margin: 10px">Anuluj</a></td>
            <td><a href="/devices/${toRemove.id}/delete" class="btn btn-danger" style="margin: 10px">Usuń</a></td>
        </tr>
    </table>
</div>
</body>
</html>
