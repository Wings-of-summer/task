<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Asya
  Date: 16.07.12
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
    <form action="servlet/ProjectAction" method="POST">
        <input type="submit" value="Проекты" />
    </form>
    <form action="servlet/EmployeAction" method="POST">
        <input type="submit" value="Сотрудники" />
    </form>
</body>
</html>