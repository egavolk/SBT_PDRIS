package edu.phystech.task1.servlet;

import edu.phystech.task1.storage.User;
import edu.phystech.task1.storage.UserStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserStorage.isUsernameExist(username)) {
            writer.print(String.format("Username %s is already used", username));
            RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
            rd.include(request, response);
            writer.close();
            return;
        }

        UserStorage.addUser(new User(username, password));

        writer.println(String.format("Username %s successfully signed up.", username));
        RequestDispatcher rd = request.getRequestDispatcher("/");
        rd.include(request, response);

        writer.close();
    }
}
