package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserUpdateServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        User user = validateUserStore.findById(UUID.fromString(id));
        if (user != null) {
            writer.append("<!DOCType html>"
                    + "<html>"
                    + "<head>"
                    + "<title>UPDATE USER</title>"
                    + "</head>"
                    + "<body>"
                    + "<h2>UPDATE USER</h2>"
                    + "<form method = 'post'>"
                    + "<input type='hidden' name='id' value'=" + user.getId() + "'>"
                    + "Name:<input type='text' name='name' value='" + user.getName() + "'><br>"
                    + "Login:<input type='text' name='login' value='" + user.getLogin() + "'><br>"
                    + "Email:<input type='text' name='email' value='" + user.getEmail() + "'><br>"
                    + "<input type='submit' value='UPDATE'>"
                    + "</form>"
                    + "</body>"
                    + "</html>");
        } else {
            writer.append("User not Exist");
        }


        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html");
        //PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        //writer.append(validateUserStore.update(UUID.fromString(id), new User(name, login, email)));
        //writer.append("<br>");
        //writer.append("<a href='UsersServlet.do'>Back to UsersServlet</a>");
        //writer.flush();
        //writer.close();
        validateUserStore.update(UUID.fromString(id), new User(name, login, email));
        resp.sendRedirect(String.format("%s/Users.jsp", req.getContextPath()));
    }
}
