<%--
  Created by IntelliJ IDEA.
  User: Asya
  Date: 20.07.12
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
    <b>Действие произвведено успешно</b><br>
<form action="MainAction">
    <input type="submit" value="Вернуться на главную страницу">
</form>
<form action="${previousPage}">
    <input type="submit" value="Вернуться на предыдущую страницу">
</form>
</body>
</html>