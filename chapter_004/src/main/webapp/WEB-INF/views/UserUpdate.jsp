<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.UUID" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UPDATE USER</title>
        <link rel="stylesheet" type="text/css" href="../../css/main.css"/>
    </head>
    <body>
    <h2>UPDATE USER</h2>
    <form method = 'post' action="/UserUpdateServlet.do">
        <input type='hidden' name='id' value=${user.id}>

        <label>Name:</label>
        <input type='text' name='name' value=${user.name}><br>

        <label>Login:</label>
        <input type='text' name='login' value=${user.login}><br>

        <label>Email:</label>
        <input type='text' name='email' value=${user.email}><br>

        <label>Password:</label>
        <input type="password" name="password" value=${user.password}><br>

        <label>Role:</label>
        <c:choose>
            <c:when test="${sessionScope.role == 'USER'}">
                <input type="text" name="role" value=${user.role} disabled="disabled"><br>
                <input type="hidden" name="role" value=${user.role}><br>
            </c:when>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <select name="role">
                    <option value="admin">ADMIN</option>
                    <option value="user">USER</option>
                </select><br>
            </c:when>
        </c:choose>

        <label>&nbsp;</label>
        <input type='submit' value='UPDATE'>
    </form>
    <c:if test="${not empty error}">
        <c:out value='${error}'></c:out>
    </c:if>
    <br>
    <br>
    <a href="${pageContext.servletContext.contextPath}/">LIST OF ALL USERS</a>
    </body>
</html>