<html>
    <head>
        <title>UserCreate</title>
    </head>
    <body>
        <h2>CREATE NEW USER</h2>
        <form method = 'post' action="/UserCreateServlet.do">
            Name:<input type='text' name='name'><br>
            Login:<input type='text' name='login'><br>
            Email:<input type='text' name='email'><br>
            <input type='submit' value='CREATE'>
        </form>
    </body>
</html>