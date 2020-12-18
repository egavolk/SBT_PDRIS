package edu.phystech.task1.servlet;

import edu.phystech.task1.storage.User;
import edu.phystech.task1.storage.UserStorage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        if (!UserStorage.isUsernameExist(username)) {
            writer.println(String.format("User %s doesn't exist. Please, sign up to proceed", username));
            RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
            rd.include(request, response);
        }
        else if (password.equals(UserStorage.getUserPassword(username))) {
            writer.println(String.format("Hello, %s", username));
            RequestDispatcher rd = request.getRequestDispatcher("/content.jsp");
            rd.include(request, response);
        }  else {
            writer.println(String.format("Wrong password", username));
            RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
            rd.include(request, response);
        }

        writer.close();
    }
}
