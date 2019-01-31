<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="container" align="center">
    <h2>Lista pracowników</h2>

    <c:if test="${empty employees}">
        <div>
            <p>---Brak wpisów w bazie danych---</p>
        </div>
    </c:if>
    <c:if test="${not empty employees}">
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
                    <td>Grupy</td>
                    <td>Koszt roboczogodziny</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="employeesTab">
                <c:forEach items="${employees}" var="employee" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.email}</td>
                        <td>
                            <c:forEach items="${employee.roles}" var="role" varStatus="j">
                                ${role.role.name()}<br>
                            </c:forEach>
                        </td>
                        <td>${employee.workHourCost}</td>
                        <td>
                            <a href="/employees/${employee.id}/details" class="btn btn-xs btn-info">Szczegóły</a>
                            <a href="/employees/${employee.id}/edit" class="btn btn-xs btn-primary">Edytuj</a>
                            <a href="/employees/${employee.id}/confirm-delete" class="btn btn-xs btn-warning">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <div align="center">
        <hr>
        <a href="/employees/form" class="btn btn-primary">Dodaj nowego pracownika</a>
    </div>
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
