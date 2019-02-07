<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Urządzenia</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:if test="${param.modified != null}">
    <div class="container">
        <br>
        <div class="alert alert-success">Pomyślnie <strong>zapisano dane w bazie danych!</strong></div>
    </div>
</c:if>

<div class="container" align="center">
    <h2>Lista urządzeń klientów</h2>

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
                    <td>Właściciel</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="devicesTab">
                <c:forEach items="${devices}" var="device" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${device.manufacturer}</td>
                        <td>${device.model}</td>
                        <td>${device.description}</td>
                        <td>${device.owner.email}</td>
                        <td>
                            <a href="/devices/${device.id}/details" class="btn btn-xs btn-info">Szczegóły</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <sec:authorize access="hasRole('ROLE_MANAGER')">
    <div align="center">
        <a href="/devices/add" class="btn btn-primary">Dodaj nowe urządzenie</a>
    </div>
    </sec:authorize>
</div>

<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#devicesTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
