
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>

<body>
<h2>USER LOGIN</h2>
<form method = 'post' action='${pageContext.servletContext.contextPath}/login.do'>
    <label> Login:</label>
   <input type='text' name='login'><br>

    <label>Password:</label>
   <input type='password' name='password'><br>

    <label>&nbsp;</label>
    <input type='submit' value='LOGIN'>
</form>
<c:if test="${not empty error}">
    <div style="background-color: red">
    <c:out value='${error}'></c:out>
    </div>
</c:if>

</body>
</html>
