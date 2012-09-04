<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                Фамилия
            </td>
            <td>
                Имя
            </td>
            <td>
                Отчество
            </td>
            <td>
                Должность
            </td>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>
                    <input type="checkbox" name="${employee.id}" value="${employee.id}">
                </td>
                <td>
                    <c:out value="${employee.id}"/>
                </td>
                <td>
                    <a href="ChangeEmployeeAction?id=${employee.id}"><c:out value="${employee.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${employee.name}"/>
                </td>
                <td>
                    <c:out value="${employee.middleName}"/>
                </td>
                <td>
                    <c:out value="${employee.post}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Удалить">
</form>
<form action="AddEmployeeAction">
    <input type="submit" value="Добавить">
</form>
</body>
</html>