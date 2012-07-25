<%--
  Created by IntelliJ IDEA.
  User: Asya
  Date: 25.07.12
  Time: 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="AddEmployeeAction">
        <br>Фамилия<input type="text" name="lastName" size="40" value="${newLastName}">
        <br>Имя<input type="text" name="name" size="40" value="${newName}">
        <br>Отчество<input type="text" name="middleName" size="40" value="${newMiddleName}">
        <br>Должность<input type="text" name="post" size="40" value="${newPost}">
        <br><input type="submit" value="Сохранить">
    </form>
</body>
</html>