package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class UsersServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> list = validateUserStore.findAll();
       // validateUserStore.add(new User("Anna", "annish", "anna@gmail.com"));

        StringBuilder sb = new StringBuilder("<table border='1'>");
        sb.append("<tr><td align='center'>ID</td><td align='center'>Name</td><td align='center'>Login</td><td align='center'>Email</td><td align='center'>CreatedDate</td></tr>");
        if (list.size() != 0) {
            for (User u : list) {
                sb.append("<tr>"
                        + "<td>" + u.getId() + "</td>"
                        + "<td>" + u.getName() + "</td>"
                        + "<td>" + u.getLogin() + "</td>"
                        + "<td>" + u.getEmail() + "</td>"
                        + "<td>" + u.getCreateDate() + "</td>"
                        + "<td><form method='get' action='/UserUpdateServlet.do'>"
                        + "<input type='hidden' name='id' value='" + u.getId() + "'>"
                        + "<input type='submit' value='Update'>"
                        + "</form></td>"
                        + "<td><form method='get' action='/UserDeleteServlet.do'>"
                        + "<input type='hidden' name='id' value='" + u.getId() + "'>"
                        + "<input type='submit' value='Delete'>"
                        + "</form></td>"
                        + "</tr>");
            }
        }

        sb.append("</table>");

        writer.append("<!DOCType html>"
                 + "<html>"
                 + "<head>"
                 + "<title> Table</title>"
                 + "</head>"
                 + "<body>"
                 + sb.toString()
                 + "<br>"
                 + "<br>"
                 + "<form method='get' action='/UserCreateServlet.do'>"
                 + "<input type='submit' value='Create New User'>"
                 + "</form>"
                 + "</body>"
                 + "</html>");

        writer.flush();
        writer.close();
    }
}
