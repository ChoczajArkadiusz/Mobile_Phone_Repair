<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse" style="margin: 3px">
    <div class="container-fluid m-5">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Mobile Repair</a>
        </div>
        <sec:authorize access="hasRole('ROLE_MANAGER') or hasRole('ROLE_EMPLOYEE')">
            <ul class="nav navbar-nav">
                <li><a href="/customers">Klienci</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/devices">Urządzenia</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/parts">Części zamienne</a></li>
            </ul>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <ul class="nav navbar-nav">
                <li><a href="/tasks">Zadania</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/employees">Pracownicy</a></li>
            </ul>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Zaloguj</a></li>
            </ul>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <ul class="nav navbar-nav navbar-right">
                <p class="navbar-text"><span class="glyphicon glyphicon-user"></span>
                    <strong><%= request.getUserPrincipal().getName() %>
                    </strong></p>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
            </ul>
        </sec:authorize>
    </div>
</nav>
