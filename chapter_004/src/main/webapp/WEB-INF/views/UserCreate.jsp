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
            <%String msg= (String) request.getAttribute("error");%>
            <%if (msg !=null) {%>
                <%=msg%>
            <%}%>
            <br>
            <br>
            <a href="${pageContext.servletContext.contextPath}/UsersServlet.do">LIST OF ALL USERS</a>
        </form>
    </body>
</html>