<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.UUID" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UPDATE USER</title>
        <link rel="stylesheet" type="text/css" href="../../css/main.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="../../scripts/userServlets.js"></script>
    </head>
    <body>
    <h2>UPDATE USER</h2>
    <form method = 'post' action="/UserUpdateServlet.do">
        <input type='hidden' name='id' value=${user.id}>

        <label for="name">Name:</label>
        <input type='text' name='name' id="name" value=${user.name}><br>

        <label for="login">Login:</label>
        <input type='text' name='login' id="login" value=${user.login}><br>

        <label for="email">Email:</label>
        <input type='text' name='email' id="email" value=${user.email}><br>

        <label for="pass">Password:</label>
        <input type="password" name="password" id="pass" value=${user.password}><br>

        <!--input type="text" name="country" id="country" value="${user.country}"><br-->
        <label for="country">Country</label>
        <select id="country" name="country" onchange="getCity(this.value)" size="10" style="width: 13em">
            <option value="">--Select Country--</option>
            <option value="${user.country}">${user.country}</option>
        </select>
        <input type='button' value='List Countries' onclick="return getCountry();"><br>

        <label for="newCountry">&nbsp;</label>
        <input type="text" name="newCountry" id="newCountry">
        <input type='button' value='Add New Country' onclick="return addCountry();"><br>

        <label for="city">City</label>
        <!--input type="text" name="city" id="city" value="${user.city}"><br-->
        <select name="city" id="city" size="10" style="width: 13em">
            <option value="">--Select City--</option>
            <option value="${user.city}">${user.city}</option>
        </select>
        <br>

        <label for="newCity">&nbsp;</label>
        <input type="text" name="newCity" id="newCity">
        <input type="button" value="Add New City" onclick="return addCity();"><br>

        <label for="role">Role:</label>
        <c:choose>
            <c:when test="${sessionScope.role == 'USER'}">
                <input type="text" name="role" value=${user.role} disabled="disabled"><br>
                <input type="hidden" name="role" value=${user.role}><br>
            </c:when>
            <c:when test="${sessionScope.role == 'ADMIN'}">
                <select name="role" id="role">
                    <option value="">--Select Role--</option>
                    <option value="admin">ADMIN</option>
                    <option value="user">USER</option>
                </select><br>
            </c:when>
        </c:choose>

        <label>&nbsp;</label>
        <input type='submit' value='UPDATE' onclick="return validateUserInput()">
    </form>
    <c:if test="${not empty error}">
        <c:out value='${error}'></c:out>
    </c:if>
    <br>
    <br>
    <a href="${pageContext.servletContext.contextPath}/">LIST OF ALL USERS</a>
    </body>
</html>