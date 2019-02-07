<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Pracownicy - Szczegóły</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Szczegóły pracownika:</h2>
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
            <td>Koszt roboczogodziny [zł]</td>
            <td>Status konta</td>
            <td>Opcje</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>${employee.phone}</td>
            <td>${employee.address}</td>
            <td>
                <c:forEach items="${employee.roles}" var="role" varStatus="j">
                    ${role.role.name()}<br>
                </c:forEach>
            </td>
            <td>${employee.workHourCost}</td>
            <td>
                <c:if test="${employee.enabled}">Aktywne</c:if>
                <c:if test="${not employee.enabled}">Nieaktywne</c:if>
            </td>
            <td>
                <a href="/employees/form/${employee.id}/edit" class="btn btn-xs btn-primary">Edytuj</a>
                <a href="/employees/${employee.id}/confirm-delete" class="btn btn-xs btn-warning disabled">Usuń</a>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <br>
    <div class="container" align="center">
        <h3>Lista zadań dla wybranego pracownika</h3>
        <c:if test="${empty tasks}">
            <div>
                <p>---Brak wpisów w bazie danych---</p>
            </div>
        </c:if>
        <c:if test="${not empty tasks}">
            <div align="center">
                <p>Wyszukaj w tabeli:</p>
                <input class="form-control" id="searchPhrase" type="text" placeholder="szukana fraza..">
                <br>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <td>Zarejestrowano</td>
                        <td>Planowane rozp. naprawy</td>
                        <td>Urządzenie</td>
                        <td>Właściciel</td>
                        <td>Opis problemu</td>
                        <td>Status</td>
                        <td>Opcje</td>
                    </tr>
                    </thead>
                    <tbody id="tasksTab">
                    <c:forEach items="${tasks}" var="task" varStatus="i">
                        <tr>
                            <td>${task.registrationDate.toLocalDate()} ${task.registrationDate.toLocalTime()}</td>
                            <td>${task.scheduledRepairDate}</td>
                            <td>${task.device.manufacturer} ${task.device.model} </td>
                            <td>${task.device.owner.email}</td>
                            <td>${task.problemDescription}</td>
                            <td>${task.status.name()}</td>
                            <td>
                                <a href="/tasks/${task.id}/details" class="btn btn-xs btn-info disabled">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#searchPhrase").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#tasksTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
