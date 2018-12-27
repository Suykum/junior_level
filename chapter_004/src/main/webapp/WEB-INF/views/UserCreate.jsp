<!--%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UserCreate</title>
    </head>
    <body>
        <h2>CREATE NEW USER</h2>
        <form method = 'post' action='${pageContext.servletContext.contextPath}/'>
            Name:<input type='text' name='name'><br>
            Login:<input type='text' name='login'><br>
            Email:<input type='text' name='email'><br>
            <input type='submit' value='CREATE'>
            <br>
            <br>
            <c:if test="${not empty error}">
                <c:out value='${error}'></c:out>
            </c:if>
            <br>
            <br>
            <a href="${pageContext.servletContext.contextPath}/UsersServlet.do">LIST OF ALL USERS</a>
        </form>
    </body>
</html>