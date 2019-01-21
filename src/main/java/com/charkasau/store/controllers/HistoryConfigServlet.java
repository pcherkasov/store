package com.charkasau.store.controllers;

import com.charkasau.store.models.User;
import com.charkasau.store.repositories.OrdersRepository;
import com.charkasau.store.repositories.OrdersRepositoryJdbc;
import com.charkasau.store.repositories.UsersRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/goToHistoryConfig")
public class HistoryConfigServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRepositoryJdbc usersRepository = new UsersRepositoryJdbc((Connection) getServletContext().getAttribute("connection"));
        OrdersRepository ordersRepository = new OrdersRepositoryJdbc((Connection) getServletContext().getAttribute("connection"));

        usersRepository.addUser((User) getServletContext().getAttribute("user"));
        ordersRepository.saveUsersOrder((User) getServletContext().getAttribute("user"));
        req.getRequestDispatcher("/jsp/historyConfig.jsp").forward(req, resp);
    }
}
