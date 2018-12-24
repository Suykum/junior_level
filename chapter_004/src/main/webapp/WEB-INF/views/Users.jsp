<%@ page import="ru.job4j.servlet2.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.servlet2.ValidateUser" %>
<html>
    <head>
        <title>Users JSP</title>
    </head>
    <body>
    <%List<User> list = (List<User>)request.getAttribute("users"); %>
    <table border="1">
    <tr>
        <th align='center'>ID</th>
        <th align='center'>Name</th>
        <th align='center'>Login</th>
        <th align='center'>Email</th>
        <th align='center'>CreatedDate</th>
    </tr>
    <%for (User u : list) {%>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getName()%></td>
        <td><%=u.getLogin()%></td>
        <td><%=u.getEmail()%></td>
        <td><%=u.getCreateDate()%></td>
        <td><form method='get' action='<%=request.getContextPath()%>/UserUpdateServlet.do'>
        <input type='hidden' name='id' value=<%=u.getId()%>>
        <input type='submit' value='Update'>
        </form></td>
        <td><form method='get' action="<%=request.getContextPath()%>/UserDeleteServlet.do">
            <input type='hidden' name='id' value=<%=u.getId()%>>
            <input type='submit' value='Delete'>
        </form></td>

    </tr>
    <% }%>
    </table>
    <br>
    <br>
    <form method='get' action='<%=request.getContextPath()%>/'>
        <input type='submit' value='Create New User'>
        </form>
    </body>
</html>