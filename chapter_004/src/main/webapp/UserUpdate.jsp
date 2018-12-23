<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.UUID" %>
<html>
    <head>
        <title>UPDATE USER</title>
    </head>
    <body>
    <%ValidateUser validateUserStore = ValidateUser.getValidateUserObject();%>
    <%User user = validateUserStore.findById(UUID.fromString(request.getParameter("id")));%>
    <h2>UPDATE USER</h2>
    <form method = 'post' action="/UserUpdateServlet.do">
        <input type='hidden' name='id' value=<%=user.getId()%>>
        Name:<input type='text' name='name' value=<%=user.getName()%>><br>
        Login:<input type='text' name='login' value=<%=user.getLogin()%>><br>
        Email:<input type='text' name='email' value=<%=user.getEmail()%>><br>
        <input type='submit' value='UPDATE'>
    </form>
    </body>
</html>