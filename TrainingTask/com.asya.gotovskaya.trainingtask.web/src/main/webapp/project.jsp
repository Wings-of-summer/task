<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<form method="POST" action="ProjectAction">
   <select name="selectProject" size="1" >
            <c:forEach var="project" items="${projects}">
                <option value="${project.id}">${project.name}</option>
            </c:forEach>
   </select>
   <input type="submit" value="Просмотреть"/>
</form>
<c:if test="${selected != null}">
    <br>Идентификатор <b><c:out value="${selected.id}"/></b>
    <br>Название проекта <b><c:out value="${selected.name}"/></b>
    <br>Сокащенное название <b><c:out value="${selected.abbreviation}"/></b>
    <br>Описание <b><c:out value="${selected.description}"/></b>
    <form action="DeleteProjectAction">
        <input type="hidden" name="id" value="${selected.id}">
        <br><input type="submit" value="Удалить">
    </form>
</c:if>
</body>
</html>