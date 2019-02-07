<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Części zamienne - formularz</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Dodawanie/edycja części zamiennych</h2>
    <div>
        <form:form modelAttribute="part" method="post" action="/parts/edit" cssClass="form-horizontal">
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
                <label class="control-label col-sm-4" for="type">Typ:</label>
                <div class="col-sm-6">
                    <form:input path="type" required="true" id="type" cssClass="form-control"
                                placeholder="Podaj typ"/>
                    <p><strong><form:errors path="type" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="description">Opis:</label>
                <div class="col-sm-6">
                    <form:input path="description" required="false" id="description" cssClass="form-control"
                                placeholder="Podaj opis"/>
                    <p><strong><form:errors path="description" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="serialNumber">Numer seryjny:</label>
                <div class="col-sm-6">
                    <form:input path="serialNumber" required="false" id="serialNumber" cssClass="form-control"
                                placeholder="Podaj numer seryjny"/>
                    <p><strong><form:errors path="serialNumber" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="quantity">Ilość [szt.]:</label>
                <div class="col-sm-6">
                    <form:input path="quantity" required="true" id="quantity" cssClass="form-control"
                                placeholder="Podaj ilość sztuk" type="number" min="0" step="1"/>
                    <p><strong><form:errors path="quantity" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="price">Cena [zł]:</label>
                <div class="col-sm-6">
                    <form:input path="price" required="true" id="price" cssClass="form-control"
                                placeholder="Podaj cenę jednostkową" type="number" min="0.01" step="0.01"/>
                    <p><strong><form:errors path="price" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="workHours">Czas wymiany [h]:</label>
                <div class="col-sm-6">
                    <form:input path="workHours" required="true" id="workHours" cssClass="form-control"
                                placeholder="Podaj ilość roboczogodziny" type="number" min="0.25" step="0.25"/>
                    <p><strong><form:errors path="workHours" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>

            <p><input type="submit" value="Zapisz" class="btn btn-primary"/></p>
        </form:form>
    </div>

</body>
</html>
