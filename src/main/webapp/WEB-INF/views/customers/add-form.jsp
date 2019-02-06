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
    <h2>Dodawanie/edycja klienta</h2>
    <div>
        <form:form modelAttribute="customer" method="post" action="/customers/add" cssClass="form-horizontal">
            <form:hidden path="id"/>
            <div class="form-group">
                <label class="control-label col-sm-4" for="firstName">Imię:</label>
                <div class="col-sm-6">
                    <form:input path="firstName" required="true" id="firstName" cssClass="form-control"
                                placeholder="Podaj imię"/>
                    <p><strong><form:errors path="firstName" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="lastName">Nazwisko:</label>
                <div class="col-sm-6">
                    <form:input path="lastName" required="true" id="lastName" cssClass="form-control"
                                placeholder="Podaj nazwisko"/>
                    <p><strong><form:errors path="lastName" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="email">Email:</label>
                <div class="col-sm-6">
                    <form:input path="email" required="true" type="email" id="email" cssClass="form-control"
                                placeholder="Podaj adres email"/>
                    <p><strong><form:errors path="email" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="password">Hasło:</label>
                <div class="col-sm-6">
                    <form:input path="password" required="true" type="password" id="password" cssClass="form-control"
                                placeholder="Podaj hasło"/>
                    <p><strong><form:errors path="password" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="phone">Numer telefonu:</label>
                <div class="col-sm-6">
                    <form:input path="phone" required="false" id="phone" cssClass="form-control"
                                placeholder="Podaj numer telefonu"/>
                    <p><strong><form:errors path="phone" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="address">Adres:</label>
                <div class="col-sm-6">
                    <form:input path="address" required="false" id="address" cssClass="form-control"
                                placeholder="Podaj adres"/>
                    <p><strong><form:errors path="address" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>

            <p><input type="submit" value="Zapisz" class="btn btn-primary"/></p>
        </form:form>
    </div>

</body>
</html>
