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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Dodawanie/edycja urządzeń</h2>
    <div>
        <form:form modelAttribute="deviceDto" method="post" action="/devices/edit" cssClass="form-horizontal">
            <form:hidden path="id"/>
            <div class="form-group">
                <label class="control-label col-sm-4" for="manufacturer">Producent:</label>
                <div class="col-sm-6">
                    <form:input path="manufacturer" required="true" id="manufacturer" cssClass="form-control"
                                placeholder="Podaj producenta"/>
                    <p><strong><form:errors path="manufacturer" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="model">Model:</label>
                <div class="col-sm-6">
                    <form:input path="model" required="true" id="model" cssClass="form-control"
                                placeholder="Podaj model"/>
                    <p><strong><form:errors path="model" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="description">Opis:</label>
                <div class="col-sm-6">
                    <form:input path="description" required="true" id="description" cssClass="form-control"
                                placeholder="Podaj opis"/>
                    <p><strong><form:errors path="description" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="description">Właściciel:</label>
                <div class="col-sm-6">
                    <form:select path="owner" items="${deviceDto.customersEmails}" required="true" id="owner"
                                 cssClass="form-control selectpicker" data-live-search="true"/>
                    <p><strong><form:errors path="owner" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <p><input type="submit" value="Zapisz" class="btn btn-primary"/></p>
        </form:form>
    </div>
</div>
</body>
</html>