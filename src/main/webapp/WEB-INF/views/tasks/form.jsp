<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=task-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Dodawanie/edycja zadania</h2>
    <h4>Urządzenie: ${taskDto.device.manufacturer} ${taskDto.device.model}</h4>
    <h4>Właściciel: ${taskDto.device.owner.email}</h4>
    <hr>
    <div>
        <form:form modelAttribute="taskDto" method="post" action="/tasks/new" cssClass="form-horizontal">
            <form:hidden path="device.id"/>
            <div class="form-group">
                <label class="control-label col-sm-4" for="problemDescription">Opis problemu:</label>
                <div class="col-sm-6">
                    <form:textarea path="problemDescription" required="true" id="problemDescription"
                                   cssClass="form-control"
                                   placeholder="Podaj opis problemu"/>
                    <p><strong><form:errors path="problemDescription" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="employeeEmail">Pracownik:</label>
                <div class="col-sm-6">
                    <form:select path="employeeEmail" items="${taskDto.employeesEmails}" required="true"
                                 id="employeeEmail"
                                 cssClass="form-control selectpicker" data-live-search="true"/>
                    <p><strong><form:errors path="employeeEmail" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="part">Część:</label>
                <div class="col-sm-6">
                    <form:select path="part" items="${taskDto.parts}" itemLabel="type" required="true" id="part"
                                 cssClass="form-control selectpicker" data-live-search="true">
                    </form:select>
                </div>
            </div>
            <p><input type="submit" value="Zapisz" class="btn btn-primary"/></p>
        </form:form>
    </div>
    <br>
    <br>
    <div>
        <h3>Lista dostępnych części zamiennych do wybranego modelu</h3>
        <c:if test="${empty taskDto.parts}">
            <div>
                <p>---Brak dostępnych części---</p>
            </div>
        </c:if>
        <c:if test="${not empty taskDto.parts}">
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
                    <tbody id="partsTab">
                    <c:forEach items="${taskDto.parts}" var="part" varStatus="i">
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
                                <a href="/parts/${part.id}/edit" class="btn btn-xs btn-primary">Dodaj</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
<br>
<div class="container" align="center">
    <h3>Lista pracowników z dostępnym czasem</h3>

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
                    <td>Email</td>
                    <td>Koszt roboczogodziny [zł]</td>
                    <td>Dostępność [h]</td>
                    <td>Opcje</td>
                </tr>
                </thead>
                <tbody id="employeesTab">
                <c:forEach items="${employees}" var="employee" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${employee.id}</td>
                        <td>${employee.email}</td>
                        <td>${employee.workHourCost}</td>
                        <td>${employee.hours}</td>
                        <td>
                            <a href="/" class="btn btn-xs btn-info">Przypisz</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
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