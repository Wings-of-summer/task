<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="main.jsp" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<form method="POST" action="ProjectAction">
    <select name="selectProject" size="1">
        <c:forEach var="project" items="${projects}">
            <option value="${project.id}">${project.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Просмотреть"/>
</form>
<form action="AddProjectAction">
    <input type="submit" value="Добавить">
</form>
<c:if test="${selected != null}">
    <table>
        <tr>
            <td>
                Идентификатор
            </td>
            <td>
                <c:out value="${selected.id}"/>
            </td>
        </tr>
        <tr>
            <td>
                Название проекта
            </td>
            <td>
                <c:out value="${selected.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                Сокащенное название
            </td>
            <td>
                <c:out value="${selected.abbreviation}"/>
            </td>
        </tr>
        <tr>
            <td>
                Описание
            </td>
            <td>
                <c:out value="${selected.description}"/>
            </td>
        </tr>
    </table>
    <form action="DeleteProjectAction">
        <input type="hidden" name="id" value="${selected.id}">
        <input type="submit" value="Удалить">
    </form>
    <form method="POST" action="ChangeProjectAction">
        <input type="hidden" name="id" value="${selected.id}">
        <input type="submit" value="Изменить">
    </form>
</c:if>
</body>
</html>