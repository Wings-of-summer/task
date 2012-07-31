<%--
  @author asya
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <b>Произошла ошибка</b>
<form action="MainAction">
    <input type="submit" value="Вернуться на главную страницу">
</form>
<form action="${previousPage}">
    <input type="submit" value="Вернуться на предыдущую страницу">
</form>
</body>
</html>