package edu.phystech.task1.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmptyNullFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (password == null || username == null || password.isEmpty() || username.isEmpty()) {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.print("Password and username should not be empty");
            RequestDispatcher rd = request.getRequestDispatcher(request.getRequestURI() + ".jsp");
            rd.include(request, response);
            writer.close();
            return;
        }

        chain.doFilter(request, response);
    }
}
