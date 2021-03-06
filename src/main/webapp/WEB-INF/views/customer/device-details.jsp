<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Szczegóły</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Szczegóły urządzenia:</h2>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <td>Producent</td>
            <td>Model</td>
            <td>Opis</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${device.manufacturer}</td>
            <td>${device.model}</td>
            <td>${device.description}</td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <div class="container" align="center">
        <h3>Lista zadań dla wybranego urządzenia</h3>
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
                        <td>Opis problemu</td>
                        <td>Opis naprawy</td>
                        <td>Data odbioru</td>
                        <td>Status zlecenia</td>
                    </tr>
                    </thead>
                    <tbody id="devicesTab">
                    <c:forEach items="${tasks}" var="task" varStatus="i">
                        <tr>
                            <td>${task.registrationDate}</td>
                            <td>${task.scheduledRepairDate}</td>
                            <td>${task.problemDescription}</td>
                            <td>${task.repairDescription}</td>
                            <td>${task.deliveryDate}</td>
                            <td>${task.status.name()}</td>
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
            $("#devicesTab tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

</body>
</html>
