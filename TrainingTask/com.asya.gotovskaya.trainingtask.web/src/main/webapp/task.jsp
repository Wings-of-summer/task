<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="main.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Simple jsp page</title>
</head>
<body>
<form action="DeleteTaskAction" method="post">
    <table bgcolor="#F0FFFF" border="1" bordercolor="#838B8B">
        <tr>
            <td>

            </td>
            <td>
                Идентификатор
            </td>
            <td>
                Сокращенное название проекта
            </td>
            <td>
                Название
            </td>
            <td>
                Колличество часов
            </td>
            <td>
                Дата начала
            </td>
            <td>
                Дата окончания
            </td>
            <td>
                Исполнитель
            </td>
            <td>
                Состояние
            </td>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>
                    <input type="checkbox" name="${task.id}" value="${task.id}">
                </td>
                <td>
                    <c:out value="${task.id}"/>
                </td>
                <td>
                    <c:out value="${task.project.abbreviation}"/>
                </td>
                <td>
                    <a href="ChangeTaskAction?id=${task.id}"><c:out value="${task.name}"/></a>
                </td>
                <td>
                    <c:out value="${task.hours}"/>
                </td>
                <td>
                    <c:out value="${task.start}"/>
                </td>
                <td>
                    <c:out value="${task.finish}"/>
                </td>
                <td>
                    <c:out value="${task.employee.lastName} ${task.employee.name} ${task.employee.middleName}"/>
                </td>
                <td>
                    <c:out value="${task.state}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Удалить">
</form>

<form action="AddTaskAction">
    <input type="submit" value="Добавить">
</form>

</body>
</html>