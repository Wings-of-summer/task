<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<form action="SaveChangeProjectAction" method="POST">
    <table>
        <tr>
            <td>
                Название проекта
            </td>
            <td>
                <input type="text" name="name" size="40" value="${project.name}">
            </td>
        </tr>
        <tr>
            <td>
                Сокащенное название
            </td>
            <td>
                <input type="text" name="abbreviation" size="40" value="${project.abbreviation}">
            </td>
        </tr>
        <tr>
            <td>
                Описание
            </td>
            <td>
                <input type="text" name="description" size="40" value="${project.description}">
            </td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${project.id}">
    <c:if test="${addFlag != null}">
        <input type="hidden" name="addFlag" value="${addFlag}">
    </c:if>
    <input type="submit" value="Сохранить">
</form>

<form action="ProjectAction">
    <input type="submit" value="Отмена">
</form>

<c:if test="${addFlag == null}">
    <form action="AddTaskAction" method="POST">
        <input type="hidden" name="imProject" value="${project.id}">
        <input type="submit" value="Добавить задачу">
    </form>

    <c:if test="${tasks != null}">
        <form method="POST" action="DeleteAllSelectedTasksAction">
            <table bgcolor="#F0FFFF" border="1" bordercolor="#838B8B">
                <tr>
                    <td>

                    </td>
                    <td>
                        Идентификатор
                    </td>
                    <td>
                        Название задачи
                    </td>
                    <td>
                        Дата начала
                    </td>
                    <td>
                        Дата окончания
                    </td>
                    <td>
                        Исполнитель
                    </td>
                    <td>
                        Статус
                    </td>
                </tr>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>
                            <input type="checkbox" name="${task.id}" value="${task.id}">
                        </td>
                        <td>
                            <c:out value="${task.id}"/>
                        </td>
                        <td>
                            <a href="ChangeTaskAction?id=${task.id}&imProject=${project.id}">
                                <c:out value="${task.name}"/></a>
                        </td>
                        <td>
                            <c:out value="${task.start}"/>
                        </td>
                        <td>
                            <c:out value="${task.finish}"/>
                        </td>
                        <td>
                            <c:out value="${task.employee.lastName} ${task.employee.name} ${task.employee.middleName}"/>
                        </td>
                        <td>
                            <c:out value="${task.state}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" name="id" value="${project.id}">
            <input type="submit" value="Удалить">
        </form>
    </c:if>
</c:if>

</body>
</html>