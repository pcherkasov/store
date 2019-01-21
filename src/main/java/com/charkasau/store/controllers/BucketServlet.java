package com.charkasau.store.controllers;

import com.charkasau.store.models.Order;
import com.charkasau.store.models.User;
import com.charkasau.store.repositories.ItemsRepository;
import com.charkasau.store.repositories.ItemsRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Class orderServlet.
 * create 12.11.2018.
 *
 * @author Pavel Charkasau.
 */
@WebServlet("/bucket")
public class BucketServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemsRepository itemsRepository = new ItemsRepositoryJdbc((Connection) getServletContext().getAttribute("connection"));
        User user = (User) getServletContext().getAttribute("user");
        Order order = user.getOrder();
        String[] names = req.getParameterValues("selectedItems");

        if (names != null) {

            for (String name : names) {
                order.addItem(itemsRepository.getItem(name));
            }

            user.setOrder(order);
            getServletContext().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/bucket");

        } else {
            PrintWriter out = resp.getWriter();
            out.write("Please, select anything in our WonderStore!");
        }

    }
}
