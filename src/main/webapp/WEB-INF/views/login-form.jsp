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
    <div style="color: #757575">
        <h3>Wersja demo</h3>
        <h4>przykładowe dane do zalogowania</h4>
        <div class="row justify-content-center">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                    <table class="table table-sm" style="color: #757575">
                        <thead>
                        <tr>
                            <th>poziom dostępu</th>
                            <th>email</th>
                            <th>hasło</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Kierownik</td>
                            <td>marcin.janik@mobile.pl</td>
                            <td>demo</td>
                        </tr>
                        <tr>
                            <td>Pracownik</td>
                            <td>jan.nowak@mobile.pl</td>
                            <td>demo</td>
                        </tr>
                        <tr>
                            <td>Klient</td>
                            <td>michal.p@wp.pl</td>
                            <td>demo</td>
                        </tr>
                        </tbody>
                    </table>
            </div>
        </div>
    </div>
    <div align="center">
        <a href="/demo/confirm-restore" class="btn btn-warning btn-xs">
            Przywróć bazę danych wersji demo do domyślnej postaci</a>
    </div>
    <br>
    <c:if test="${param.restored != null}">
        <div class="alert alert-success" role="alert">
            Baza danych wersji demo została pomyślnie przywrócona do domyślnej postaci.
        </div>
    </c:if>
    <hr>
    <h2>Zaloguj się</h2>
    <div>
        <form method="post" action="/login" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-4" for="email">Email:</label>
                <div class="col-sm-5">
                    <input name="email" required="true" type="email" id="email" class="form-control"
                           placeholder="Podaj adres email"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" for="password">Hasło:</label>
                <div class="col-sm-5">
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
