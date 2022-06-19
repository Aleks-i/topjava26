<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Meals</title>
    <link type="text/css" rel="stylesheet" href="resources/css/meals.css">
</head>
<body>
<header>
    <table class="thaeder">
        <thead>
        <tr>
            <jsp:include page="WEB-INF/jsp/fragments/header.jsp"/>
        </tr>
        </thead>
    </table>
</header>
<br>
<jsp:useBean id="mealTo" class="ru.javawebinar.topjava.model.MealTo"/>
<table class="table">
    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr class="${mealTo.excess ? 'excess' : 'normal'}">
                <%--            <td>${f:formatLocalDateTime(mealTo.dateTime, 'dd.MM.yyyy HH:mm')}</td>--%>
            <td>
                <fmt:parseDate value="${mealTo.dateTime}" pattern="y-M-dd'T'H:m" var="parseDate"/>
                <fmt:formatDate value="${parseDate}" pattern="dd.MM.yyyy HH:mm"/>
            </td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=edit&id=${mealTo.id}">Edit meal</a></td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Delete meal</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="meals?action=create" class="buttons">Add meal</a>
</body>
</html>