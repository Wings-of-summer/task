<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
</head>
<body>
<c:if test="${message != null}">
    ${message}
</c:if>
<form action="SaveChangeTaskAction" method="POST">
    <table>
        <tr>
            <td>
                Сокращенное название проекта
            </td>
            <td>
                <select name="idProject" size="1">
                    <c:if test="${task != null}">
                        <option selected="selected" value="${task.project.id}">${task.project.abbreviation}</option>
                    </c:if>
                    <c:forEach var="project" items="${projects}">
                        <c:if test="${project != task.project}">
                            <option value="${project.id}">${project.abbreviation}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Название
            </td>
            <td>
                <input type="text" name="name" size="40" value="${task.name}">
            </td>
        </tr>
        <tr>
            <td>
                Колличество часов
            </td>
            <td>
                <input type="text" name="hours" size="40" value="${task.hours}">
            </td>
        </tr>
        <tr>
            <td>
                Дата начала
            </td>
            <td>
                <input type="text" name="start" size="40" value="${task.start}">
            </td>
        </tr>
        <tr>
            <td>
                Дата окончания
            </td>
            <td>
                <input type="text" name="finish" size="40" value="${task.finish}">
            </td>
        </tr>
        <tr>
            <td>
                Исполнитель
            </td>
            <td>
                <select name="idEmployee" size="1">
                    <c:if test="${task != null}">
                        <option selected="selected" value="${task.employee.id}">${task.employee.lastName}
                                ${task.employee.name} ${task.employee.middleName}</option>
                    </c:if>
                    <c:forEach var="employee" items="${employees}">
                        <c:if test="${employee != task.employee}">
                            <option value="${employee.id}"> ${employee.lastName} ${employee.name} ${employee.middleName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Состояние
            </td>
            <td>
                <select name="state" size="1">
                    <c:if test="${task != null}">
                        <option selected="selected" value="${task.state}">${task.state}</option>
                    </c:if>
                    <c:forEach var="state" items="${states}">
                        <c:if test="${state != task.state}">
                            <option value="${state}">${state}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${task.id}">
    <input type="submit" value="Сохранить">
</form>
<form action="EmployeeAction">
    <input type="submit" value="Отмена">
</form>
</body>
</html>