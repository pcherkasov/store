package com.charkasau.store.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthFilter.
 * create 15.11.2018.
 *
 * @author Pavel Charkasau.
 */

@WebFilter("/order")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession(false);

        if (request.getParameter("confirm") != null) {
            filterChain.doFilter(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/error.html");
    }

    @Override
    public void destroy() {

    }
}
