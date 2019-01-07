package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UsersServlet extends HttpServlet {
    private Validate validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<User> list = validateUserStore.findAll();
        req.setAttribute("users", list);
        req.getRequestDispatcher("WEB-INF/views/Users.jsp").forward(req, resp);

    }
}
