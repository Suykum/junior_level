package ru.job4j.servlet2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserCreateServlet extends HttpServlet {
    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //resp.sendRedirect(String.format("%s/UserCreate.jsp", req.getContextPath()));
        req.getRequestDispatcher(String.format("%s/UserCreate.jsp", req.getContextPath())).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        validateUserStore.createTable();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String add = validateUserStore.add(new User(name, login, email));
        if (!add.contains("Cannot")) {
            resp.sendRedirect(String.format("%s/Users.jsp", req.getContextPath()));
            //req.getRequestDispatcher(String.format("%s/Users.jsp", req.getContextPath())).forward(req, resp);
        } else {
            req.setAttribute("error", "Cannot be added");
            doGet(req, resp);

        }

    }
}
