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
    <h2>Wersja demo</h2>
    <h3>Prywracanie bazy danych do wersji domyślnej</h3>
    <br>
    <div>
        <p class="alert alert-danger" role="alert"><strong>Uwaga!</strong> Po przywróceniu bazy danych do wersji
            domyślnej wszystkie dokonane zmiany zostaną bezpowrotnie utracone</p>
    </div>
    <br>
    <div>
        <a href="/" class="btn btn-info" style="margin: 20px">Anuluj</a>
        <a href="/demo/restore" class="btn btn-danger" style="margin: 20px">Przywróć bazę danych</a>
    </div>
</div>
</body>
</html>
