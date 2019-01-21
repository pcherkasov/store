package com.charkasau.store.controllers;

import com.charkasau.store.models.User;
import com.charkasau.store.repositories.UsersRepositoryJdbc;
import com.charkasau.store.services.Connector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Class LogInServlet.
 * create 11.11.2018.
 *
 * @author Pavel Charkasau.
 */

@WebServlet("/order")
public class LogInServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("connection", Connector.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UsersRepositoryJdbc usersRepository = new UsersRepositoryJdbc((Connection) getServletContext().getAttribute("connection"));
       String name = req.getParameter("userName");
       User user;
       if (usersRepository.isExist(name)) {
           user = usersRepository.getUserByName(name);
       } else {
           int newId = usersRepository.getLastUserId() + 1;
           user = new User(newId, name);
       }
       getServletContext().setAttribute("user", user);
       req.setAttribute("user", user);
       RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/order.jsp");
       dispatcher.forward(req, resp);
    }
}
