<!--%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>UserCreate</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../css/main.css">
        <script src="../../scripts/userServlets.js">

        </script>
    </head>
    <body>
        <h2>CREATE NEW USER</h2>
        <form method = 'post' action='${pageContext.servletContext.contextPath}/UserCreateServlet.do'>
            <label for="name">Name:</label>
            <input type='text' name='name' id="name"><br>

            <label for="login">Login:</label>
            <input type='text' name='login' id="login"><br>

            <label for="email">Email:</label>
            <input type='text' name='email'id="email"><br>

            <label for="pass">Password:</label>
            <input type="password" name="password" id="pass"><br>

            <label for="country">Country</label>
            <select id="country" name="country" onchange="getCity(this.value)" size="10" style="width: 13em">
                <option value="">--Select Country--</option>
            </select>
            <input type='button' value='List Countries' onclick="return getCountry();"><br>

            <label for="newCountry">&nbsp;</label>
            <input type="text" name="newCountry" id="newCountry">
            <input type='button' value='Add New Country' onclick="return addCountry();"><br>

            <label for="city">City</label>
            <select name="city" id="city" size="10" style="width: 13em">
                <option value="">--Select City--</option>
            </select>
            <br>

            <label for="newCity">&nbsp;</label>
            <input type="text" name="newCity" id="newCity">
            <input type="button" value="Add New City" onclick="return addCity();"><br>

            <label for="role">Role:</label>
            <select name="role" id="role">
                <option value="">--Select Role--</option>
                <option value="admin">ADMIN</option>
                <option value="user">USER</option>
            </select><br>

            <label>&nbsp;</label>
            <input type='submit' value='CREATE' onclick="return validateUserInput()">
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