<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Meals</title>
    <link type="text/css" rel="stylesheet" href="resources/css/meals.css">
</head>
<body>
<header>
    <jsp:include page="WEB-INF/jsp/fragments/header.jsp"/>
</header>
<br>
<jsp:useBean id="meal" class="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}"/>
    <table class="table">
        <thead>
        <tr>
            <th colspan="2" style="text-align: center">Save/Edit meal</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Date/Time</td>
            <td>
                <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input type="text" name="description" value="${meal.description}" required/>
            </td>
        </tr>
        <tr>
            <td>Calories</td>
            <td>
                <input type="text" name="calories" value="${meal.calories}" required/>
            </td>
        </tr>
        </tbody>
    </table>
    <button class="buttons" type="submit">Save</button>
</form>
</body>
</html>