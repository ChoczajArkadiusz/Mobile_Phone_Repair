<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="header.jsp"/>

<div class="container" align="center">
    <h2>Zaloguj się</h2>
    <div>
        <form method="post" action="/login" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-4" for="email">Email:</label>
                <div class="col-sm-6">
                    <input name="email" required="true" type="email" id="email" class="form-control"
                           placeholder="Podaj adres email"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="password">Hasło:</label>
                <div class="col-sm-6">
                    <input name="password" required="true" type="password" id="password" class="form-control"
                           placeholder="Podaj hasło"/>
                </div>
            </div>
            <div>
                <c:if test="${param.error != null}">
                    <p class="alert alert-danger" role="alert">Błędne dane logowania</p>
                </c:if>
            </div>

            <p><input type="submit" value="Zaloguj" class="btn btn-primary"/></p>
        </form>
    </div>
</div>
</body>
</html>
