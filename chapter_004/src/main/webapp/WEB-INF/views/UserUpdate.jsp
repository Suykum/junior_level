<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.UUID" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UPDATE USER</title>
    </head>
    <body>
    <h2>UPDATE USER</h2>
    <form method = 'post' action="/UserUpdateServlet.do">
        <input type='hidden' name='id' value=<c:out value="${user.id}"></c:out>>
        Name:<input type='text' name='name' value=<c:out value="${user.name}"></c:out>><br>
        Login:<input type='text' name='login' value=<c:out value="${user.login}"></c:out>><br>
        Email:<input type='text' name='email' value=<c:out value="${user.email}"></c:out>><br>
        <input type='submit' value='UPDATE'>
    </form>
    <c:if test="${not empty error}">
        <c:out value='${error}'></c:out>
    </c:if>
    <br>
    <br>
    <a href="${pageContext.servletContext.contextPath}/UsersServlet.do">LIST OF ALL USERS</a>
    </body>
</html>