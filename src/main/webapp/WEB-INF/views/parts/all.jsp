<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Części zamienne</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Lista części zamiennych</h2>

    <c:if test="${empty parts}">
        <div>
            <p>---Brak wpisów w bazie danych---</p>
        </div>
    </c:if>
    <c:if test="${not empty parts}">
        <div align="center">
            <p>Wyszukaj w tabeli:</p>
            <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <td>Lp.</td>
                    <td>ID</td>
                    <td>Producent</td>
                    <td>Model</td>
                    <td>Typ</td>
                    <td>Opis</td>
                    <td>Numer seryjny</td>
                    <td>Ilość</td>
                    <td>Cena</td>
                    <td>Czas wymiany</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="employeesTab">
                <c:forEach items="${parts}" var="part" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${part.id}</td>
                        <td>${part.manufacturer}</td>
                        <td>${part.model}</td>
                        <td>${part.type}</td>
                        <td>${part.description}</td>
                        <td>${part.serialNumber}</td>
                        <td>${part.quantity}</td>
                        <td>${part.price}</td>
                        <td>${part.workHours}</td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_MANAGER')">
                                <a href="/parts/${part.id}/edit" class="btn btn-xs btn-primary">Edytuj</a>
                                <a href="/parts/${part.id}/confirm-delete" class="btn btn-xs btn-warning">Usuń</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <sec:authorize access="hasRole('ROLE_MANAGER')">
        <div align="center">
            <hr>
            <a href="/parts/add" class="btn btn-primary">Dodaj nową część</a>
        </div>
    </sec:authorize>
</div>

<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#employeesTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
