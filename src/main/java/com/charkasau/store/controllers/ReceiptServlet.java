package com.charkasau.store.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class ReceiptServlet.
 * create 16.11.2018.
 *
 * @author Pavel Charkasau.
 */
@WebServlet("/receipt")
public class ReceiptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/receipt.jsp").forward(req, resp);
    }
}
