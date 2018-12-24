package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UserUpdateServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = validateUserStore.findById(UUID.fromString(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        String update = validateUserStore.update(UUID.fromString(id), new User(name, login, email));
        if (!update.contains("cannot")) {
            resp.sendRedirect(String.format("%s/UsersServlet.do", req.getContextPath()));
        } else {
            req.setAttribute("error", "User cannot be updated");
            doGet(req, resp);

        }
    }
}
