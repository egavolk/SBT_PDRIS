package edu.phystech.task1.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String username = request.getParameter("username");

        if ("admin".equals(username)) {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.print("Username admin is reserved");
            RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
            rd.include(request, response);
            writer.close();
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
