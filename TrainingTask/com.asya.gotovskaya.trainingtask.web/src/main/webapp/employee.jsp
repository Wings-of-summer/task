
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Simple jsp page</title>
</head>
<body>
<form method="POST" action="EmployeeAction">
    <select name="selectedId" size="1">
        <c:forEach var="employee" items="${employees}">
            <option value="${employee.id}">${employee.lastName} ${employee.name} ${employee.middleName}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Просмотреть"/>
</form>
<form action="AddEmployeeAction">
    <input type="submit" value="Добавить">
</form>
<c:if test="${selectedEmployee != null}">
    <table>
        <tr>
            <td>
                Идентификатор
            </td>
            <td>
                <c:out value="${selectedEmployee.id}"/>
            </td>
        </tr>
        <tr>
            <td>
                Фамилия
            </td>
            <td>
                <c:out value="${selectedEmployee.lastName}"/>
            </td>
        </tr>
        <tr>
            <td>
                Имя
            </td>
            <td>
                <c:out value="${selectedEmployee.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                Отчество
            </td>
            <td>
                <c:out value="${selectedEmployee.middleName}"/>
            </td>
        </tr>
        <tr>
            <td>
                Должность
            </td>
            <td>
                <c:out value="${selectedEmployee.post}"/>
            </td>
        </tr>
    </table>
    <form action="DeleteEmployeeAction">
        <input type="hidden" name="id" value="${selectedEmployee.id}">
        <input type="submit" value="Удалить">
    </form>
    <form action="ChangeEmployeeAction">
        <input type="hidden" name="id" value="${selectedEmployee.id}">
        <input type="submit" value="Изменить">
    </form>
</c:if>
</body>
</html>