package com.charkasau.store.controllers;

import com.charkasau.store.models.User;
import com.charkasau.store.repositories.UsersRepositoryJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/showHistory")
public class ShowHistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("selectedName");
        UsersRepositoryJdbc usersRepository = new UsersRepositoryJdbc((Connection) getServletContext().getAttribute("connection"));
        User user = usersRepository.getUserByName(name);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/history.jsp").forward(req, resp);
    }
}
