<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Users JSP</title>
        <link rel="stylesheet" href="../../css/main.css" type="text/css">
    </head>
    <body>
    <h2>USERS LIST</h2>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>CreatedDate</th>
        <th>Role</th>
    </tr>
        <c:forEach var="user" items="${users}">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>${user.createDate}</td>
        <td>${user.role}</td>
        <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.login == user.login}">
        <td><form method='get' action='${pageContext.servletContext.contextPath}/UserUpdateServlet.do'>
            <input type='hidden' name='id' value=${user.id}>
            <input type='submit' value='Update'>
        </form></td>
        </c:if>
        <c:if test="${sessionScope.role == 'ADMIN'}">
        <td><form method='get' action='${pageContext.servletContext.contextPath}/UserDeleteServlet.do'>
            <input type='hidden' name='id' value=${user.id}>
            <input type='submit' value='Delete'>
        </form></td>
        </c:if>
    </tr>
        </c:forEach>
    </table>

    <br>
    <br>
    <c:if test="${sessionScope.role == 'ADMIN'}">
    <form method='get' action='${pageContext.servletContext.contextPath}/UserCreateServlet.do'>
        <input type='submit' value='Create New User'>
    </form>
    </c:if>

    <br>
    <br>
    <form method='get' action='${pageContext.servletContext.contextPath}/Logout.do'>
        <input type='submit' value='Logout'>
    </form>
    </body>
</html>