<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mobile Repair | Pracownicy - formularz</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" align="center">
    <h2>Dodawanie nowego pracownika</h2>
    <div>
        <form:form modelAttribute="employeeDto" method="post" action="/employees/form" cssClass="form-horizontal" acceptCharset="UTF-8">
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
                <label class="control-label col-sm-4" for="confirmPassword">Powtórz hasło:</label>
                <div class="col-sm-6">
                    <form:input path="confirmPassword" required="true" type="password" id="confirmPassword"
                                cssClass="form-control"
                                placeholder="Podaj hasło"/>
                    <p><strong><form:errors path="confirmPassword" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="phone">Numer telefonu:</label>
                <div class="col-sm-6">
                    <form:input path="phone" required="true" id="phone" cssClass="form-control"
                                placeholder="Podaj numer telefonu"/>
                    <p><strong><form:errors path="phone" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="workHourCost">Koszt roboczogodziny:</label>
                <div class="col-sm-6">
                    <form:input path="workHourCost" required="true" id="workHourCost" cssClass="form-control"
                                type="number" min="0.0" step="0.01" placeholder="Podaj koszt roboczogodziny"/>
                    <p><strong><form:errors path="workHourCost" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="address">Adres:</label>
                <div class="col-sm-6">
                    <form:input path="address" required="true" id="address" cssClass="form-control"
                                placeholder="Podaj adres"/>
                    <p><strong><form:errors path="address" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="roles">Grupy dostępu:</label>
                <div class="col-sm-6">
                    <form:select multiple="true" items="${employeeDto.appRoles}" path="roles" required="true" id="roles"
                                 cssClass="form-control"/>
                    <p><strong><form:errors path="roles" cssClass="alert alert-danger"/></strong></p>
                </div>
            </div>

            <p><input type="submit" value="Zapisz" class="btn btn-primary"/></p>
        </form:form>
    </div>
</div>
</body>
</html>
