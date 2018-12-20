package ru.job4j.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    private ValidateUser validateUserStore = ValidateUser.getValidateUserObject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(validateUserStore.findAll());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");


        switch (action) {
            case "add":
                out.println(validateUserStore.add(new User(id, name, login, email)));
                break;
            case "update":
                out.println(validateUserStore.update(id, new User(id, name, login, email)));
                break;
            case "delete":
                out.println(validateUserStore.delete(id));
                break;
            case "find":
               out.println(validateUserStore.findById(id));
                break;
        }
    }
}
