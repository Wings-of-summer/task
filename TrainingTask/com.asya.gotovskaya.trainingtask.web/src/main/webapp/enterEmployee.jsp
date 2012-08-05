<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="main.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
</head>
<body>
<c:if test="${message != null}">
    ${message}
</c:if>
<form action="SaveChangeEmployeeAction" method="POST">
    <table>
        <tr>
            <td>
                Фамилия
            </td>
            <td>
                <input type="text" name="lastName" size="40" value="${employee.lastName}">
            </td>
        </tr>
        <tr>
            <td>
                Имя
            </td>
            <td>
                <input type="text" name="name" size="40" value="${employee.name}">
            </td>
        </tr>
        <tr>
            <td>
                Отчество
            </td>
            <td>
                <input type="text" name="middleName" size="40" value="${employee.middleName}">
            </td>
        </tr>
        <tr>
            <td>
                Должность
            </td>
            <td>
                <input type="text" name="post" size="40" value="${employee.post}">
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${employee.id}">
    <input type="submit" value="Сохранить">
</form>
<form action="EmployeeAction">
    <input type="submit" value="Отмена">
</form>
</body>
</html>