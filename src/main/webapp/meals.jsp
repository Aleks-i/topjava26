<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <td style="text-align: left"><a href="index.html">Home</a></td>
            <td style="text-align: right">Meals</td>
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
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr style="color: ${mealTo.isExcess() == true ? 'red' : 'green'}">
            <td>${f:formatLocalDateTime(mealTo.dateTime, 'dd.MM.yyyy HH:mm')}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="#">Edit meal</a></td>
            <td><a href="#">Delete meal</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<a href="#" class="buttons">Add meal</a>
</body>
</html>