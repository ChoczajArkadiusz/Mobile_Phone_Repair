<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:if test="${param.added != null}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>dodano nowego klienta!</strong>
        </div>
    </div>
</c:if>
<c:if test="${param.edited != null}">
    <div class="container">
        <br>
        <div class="alert alert-success">
            Pomyślnie <strong>zmieniono dane klienta!</strong>
        </div>
    </div>
</c:if>

<div class="container" align="center">
    <h2>Lista klientów</h2>

    <c:if test="${empty customers}">
        <div>
            <p>---Brak wpisów w bazie danych---</p>
        </div>
    </c:if>
    <c:if test="${not empty customers}">
        <div align="center">
            <p>Wyszukaj w tabeli:</p>
            <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <td>Lp.</td>
                    <td>ID</td>
                    <td>Imię</td>
                    <td>Naziwsko</td>
                    <td>Email</td>
                    <td>Urządzenia</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="customersTab">
                <c:forEach items="${customers}" var="customer" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${customer.id}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                        <td>
                            <c:forEach items="${customer.devices}" var="device" varStatus="j">
                                ${device.manufacturer} ${device.model} <br>
                            </c:forEach>
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_MANAGER')">
                                <a href="/customers/${customer.id}/edit" class="btn btn-xs btn-primary">Edytuj</a>
                                <a href="/customers/${customer.id}/confirm-delete"
                                   class="btn btn-xs btn-warning">Usuń</a>
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
            <a href="/customers/add" class="btn btn-primary">Dodaj nowego klienta</a>
        </div>
    </sec:authorize>
</div>

<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#customersTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
