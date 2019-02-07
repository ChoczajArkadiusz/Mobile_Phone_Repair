<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Pracownicy - Homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h3>Twoje zadania na dzisiaj:</h3>
    <div>
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
                        <td>Zmiana statusu</td>
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
                                <c:choose>
                                    <c:when test="${task.status.name() == 'IN_REPAIR'}">
                                        <a href="" class="btn btn-xs btn-primary disabled" aria-disabled="true">w naprawie</a>
                                        <a href="/tasks/${task.id}/set-status?status=REPAIRED" class="btn btn-xs btn-primary">naprawiono</a>
                                    </c:when>
                                    <c:when test = "${task.status.name() == 'REPAIRED' || task.status.name() == 'DELIVERED' || task.status.name() == 'CANCELED'}">
                                        <a href="" class="btn btn-xs btn-primary disabled" aria-disabled="true">w naprawie</a>
                                        <a href="" class="btn btn-xs btn-primary disabled" aria-disabled="true">naprawiono</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/tasks/${task.id}/set-status?status=IN_REPAIR" class="btn btn-xs btn-primary">w naprawie</a>
                                        <a href="" class="btn btn-xs btn-primary disabled" aria-disabled="true">naprawiono</a>
                                    </c:otherwise>
                                </c:choose>
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
