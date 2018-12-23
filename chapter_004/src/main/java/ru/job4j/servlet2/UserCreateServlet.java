package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCType html>"
                + "<html>"
                + "<head>"
                + "<title>CREATE NEW USER</title>"
                + "</head>"
                + "<body>"
                + "<h2>CREATE NEW USER</h2>"
                + "<form method = 'post'>"
                + "Name:<input type='text' name='name'><br>"
                + "Login:<input type='text' name='login'><br>"
                + "Email:<input type='text' name='email'><br>"
                + "<input type='submit' value='CREATE'>"
                + "</form>"
                + "</body>"
                + "</html>");

        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        writer.append(validateUserStore.add(new User(name, login, email)));
        writer.append("<br>");
        writer.append("<a href='UsersServlet.do'>Back to UsersServlet</a>");
        writer.flush();
        writer.close();
    }
}
