package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserDeleteServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        writer.append(validateUserStore.delete(UUID.fromString(id)));
        writer.append("<br>");
        writer.append("<a href='UsersServlet.do'>Back to UsersServlet</a>");
        writer.flush();
        writer.close();
    }
}
