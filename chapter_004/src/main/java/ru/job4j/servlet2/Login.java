package ru.job4j.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {
    private Validate validateUserStore = ValidateUser.getValidateUserObject();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //validateUserStore.add(new User("Anna", "anna", "anna@gmail.com", "aaa", Role.Roles.ADMIN, "Turkey", "Corum"));
        //validateUserStore.add(new User("Janna", "janna", "janna@gmail.com", "jjj", Role.Roles.USER, "Kyrgyzstan", "Bishkek"));
                req.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean permited = validateUserStore.loginPermit(login, password);
        if (permited) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("role", validateUserStore.getRole(login));
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid user");
            doGet(req, resp);
        }
    }
}
