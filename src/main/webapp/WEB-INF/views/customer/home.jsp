<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:if test="${param.edited != null}">
    <div class="container">
        <br>
        <div class="alert alert-success">Pomyślnie <strong>zmieniono dane.</strong></div>
    </div>
</c:if>
<c:if test="${param.failed != null}">
    <div class="container">
        <br>
        <div class="alert alert-danger"><strong>Niepowodzenie</strong></div>
    </div>
</c:if>

<div class="container" align="center">
    <h3>Twoje dane:</h3>
    <div>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <td>Imię</td>
                <td>Naziwsko</td>
                <td>Email</td>
                <td>Telefon</td>
                <td>Opcje</td>
            </tr>
            </thead>
            <tbody id="customersTab">
            <tr>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.phone}</td>
                <td>
                    <a href="/customer/edit" class="btn btn-xs btn-primary">Edytuj</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <br>
    <h3>Lista Twoich urządzeń</h3>

    <c:if test="${empty devices}">
        <div>
            <p>---Brak wpisów w bazie danych---</p>
        </div>
    </c:if>
    <c:if test="${not empty devices}">
        <div align="center">
            <p>Wyszukaj w tabeli:</p>
            <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <td>Lp.</td>
                    <td>Producent</td>
                    <td>Model</td>
                    <td>Opis</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="employeesTab">
                <c:forEach items="${devices}" var="device" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${device.manufacturer}</td>
                        <td>${device.model}</td>
                        <td>${device.description}</td>
                        <td>
                            <a href="/customer/device-details/${device.id}" class="btn btn-xs btn-info">Szczegóły</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
