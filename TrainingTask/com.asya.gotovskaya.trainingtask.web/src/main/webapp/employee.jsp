<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<form method="POST" action="EmployeAction">
    <select name="selectedId" size="1">
        <c:forEach var="employee" items="${employees}">
            <option value="${employee.id}">${employee.lastName} ${employee.name} ${employee.middleName}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Просмотреть"/>
</form>
<form action="AddEmployeeAction">
    <br><input type="submit" value="Добавить">
</form>
<c:if test="${selectedEmployee != null}">
    <br>Идентификатор <b><c:out value="${selectedEmployee.id}"/></b>
    <br>Фамилия <b><c:out value="${selectedEmployee.lastName}"/></b>
    <br>Имя <b><c:out value="${selectedEmployee.name}"/></b>
    <br>Отчество <b><c:out value="${selectedEmployee.middleName}"/></b>
    <br>Должность <b><c:out value="${selectedEmployee.post}"/></b>
    <form action="DeleteEmployeeAction">
        <input type="hidden" name="id" value="${selectedEmployee.id}">
        <br><input type="submit" value="Удалить">
    </form>
</c:if>
</body>
</html>