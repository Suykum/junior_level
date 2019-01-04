<!--%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UserCreate</title>
        <link rel="stylesheet" type="text/css" href="../../css/main.css">
    </head>
    <body>
        <h2>CREATE NEW USER</h2>
        <form method = 'post' action='${pageContext.servletContext.contextPath}/UserCreateServlet.do'>
            <label>Name:</label>
            <input type='text' name='name'><br>

            <label>Login:</label>
            <input type='text' name='login'><br>

            <label>Email:</label>
            <input type='text' name='email'><br>

            <label>Password:</label>
            <input type="password" name="password"><br>

            <label>Role:</label>
            <select name="role">
                <option value="admin">ADMIN</option>
                <option value="user">USER</option>
            </select><br>

            <label>&nbsp;</label>
            <input type='submit' value='CREATE'>
            <br>
            <br>
            <c:if test="${not empty error}">
                <c:out value='${error}'></c:out>
            </c:if>
            <br>
            <br>
            <a href="${pageContext.servletContext.contextPath}/">LIST OF ALL USERS</a>
        </form>
    </body>
</html>