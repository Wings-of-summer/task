<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Simple jsp page</title>
</head>
<body>
<form method="POST" action="TaskAction">
    <select name="selectedId" size="1">
        <c:forEach var="task" items="${tasks}">
            <option value="${task.id}">${task.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Просмотреть"/>
</form>
<form action="AddTaskAction">
    <input type="submit" value="Добавить">
</form>
<c:if test="${selectedTask != null}">
    <table>
        <tr>
            <td>
                Идентификатор
            </td>
            <td>
                <c:out value="${selectedTask.id}"/>
            </td>
        </tr>
        <tr>
            <td>
                Сокращенное название проекта
            </td>
            <td>
                <c:out value="${selectedTask.project.abbreviation}"/>
            </td>
        </tr>
        <tr>
            <td>
                Название
            </td>
            <td>
                <c:out value="${selectedTask.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                Колличество часов
            </td>
            <td>
                <c:out value="${selectedTask.hours}"/>
            </td>
        </tr>
        <tr>
            <td>
                Дата начала
            </td>
            <td>
                <c:out value="${selectedTask.start}"/>
            </td>
        </tr>
        <tr>
            <td>
                Дата окончания
            </td>
            <td>
                <c:out value="${selectedTask.finish}"/>
            </td>
        </tr>
        <tr>
            <td>
                Исполнитель
            </td>
            <td>
                <c:out value="${selectedTask.employee.lastName} ${selectedTask.employee.name} ${selectedTask.employee.middleName}"/>
            </td>
        </tr>
        <tr>
            <td>
                Состояние
            </td>
            <td>
                <c:out value="${selectedTask.state}"/>
            </td>
        </tr>
    </table>
    <form action="DeleteTaskAction">
        <input type="hidden" name="id" value="${selectedTask.id}">
        <input type="submit" value="Удалить">
    </form>
    <form action="ChangeTaskAction">
        <input type="hidden" name="id" value="${selectedTask.id}">
        <input type="submit" value="Изменить">
    </form>
</c:if>
</body>
</html>