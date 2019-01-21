package ru.job4j.cinema;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PaymentServlet extends HttpServlet {
   // Validation validation = Validation.getInstance();
    DataBase dataBase = DataBase.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println(req.getParameter("place"));
        int place = Integer.parseInt(req.getParameter("place"));
        Hall seat = dataBase.getSeatById(place);
        req.setAttribute("seat", seat);
        req.getRequestDispatcher("WEB-INF/views/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("username");
        String telephone = req.getParameter("phone");
        int seatId = Integer.parseInt(req.getParameter("seatId"));

        boolean result = dataBase.makeRezervation(new Account(name, telephone), seatId);
        if (result) {
            List<Account> accounts = dataBase.getAllAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("WEB-INF/views/AccountsWithSeats.jsp").forward(req, resp);
        } else {
            out.println("<h3>Error occurred</h3> "
                    + "<br><a href='/cinema.html'> to main page</a>");
        }
    }

}
