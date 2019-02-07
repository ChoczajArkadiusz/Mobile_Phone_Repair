<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Klienci - Potwierdź usunięcie</title>
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
    <h3>Czy na pewno chcesz usunąć klienta:</h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <td>ID</td>
            <td>Imię</td>
            <td>Naziwsko</td>
            <td>Email</td>
            <td>Telefon</td>
            <td>Adres</td>
            <td>Urządzenia</td>
        </tr>
        </thead>
        <tbody id="customersTab">
        <tr>
            <td>${toRemove.id}</td>
            <td>${toRemove.firstName}</td>
            <td>${toRemove.lastName}</td>
            <td>${toRemove.email}</td>
            <td>${toRemove.phone}</td>
            <td>${toRemove.address}</td>
            <td>
                <c:forEach items="${toRemove.devices}" var="device" varStatus="j">
                    ${device.manufacturer} ${device.model}<br>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
    <div>
        <p class="alert alert-danger" role="alert"><strong>Usunięcie użytkownika spowoduje również usunięcie wszystkich
            urządzeń powiązanych z użytkownikiem i wszystkich zadań przypisanych do każdego z urządzeń.</strong></p>
    </div>
    <table>
        <tr>
            <td><a href="/customers" class="btn btn-primary" style="margin: 10px">Anuluj</a></td>
            <td><a href="/customers/${toRemove.id}/delete" class="btn btn-danger" style="margin: 10px">Usuń</a></td>
        </tr>
    </table>
</div>
</body>
</html>
