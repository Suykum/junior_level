<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Users JSP</title>
    </head>
    <body>
    <table border="1">
    <tr>
        <th align='center'>ID</th>
        <th align='center'>Name</th>
        <th align='center'>Login</th>
        <th align='center'>Email</th>
        <th align='center'>CreatedDate</th>
    </tr>

        <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.id}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
        <td><form method='get' action='${pageContext.servletContext.contextPath}/UserUpdateServlet.do'>
        <input type='hidden' name='id' value=${user.id}>
        <input type='submit' value='Update'>
        </form></td>
        <td><form method='get' action='${pageContext.servletContext.contextPath}/UserDeleteServlet.do'>
            <input type='hidden' name='id' value=${user.id}>
            <input type='submit' value='Delete'>
        </form></td>

    </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <form method='get' action='${pageContext.servletContext.contextPath}/'>
        <input type='submit' value='Create New User'>
        </form>
    </body>
</html>