package edu.phystech.task1.servlet;

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

        if (password.isEmpty() || username.isEmpty()) {
            writer.print("Password and username should not be empty");
            RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
            rd.include(request, response);
            writer.close();
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                     "jdbc:mysql://localhost:3306/UserDB?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");

            PreparedStatement findUser = con.prepareStatement("select password from users where username=?");
            findUser.setString(1, username);
            ResultSet result = findUser.executeQuery();

            if (!result.next()) {
                writer.println(String.format("User %s doesn't exist. Please, sign up to proceed", username));
                RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
                rd.include(request, response);
            }
            else if (password.equals(result.getString("password"))) {
                writer.println(String.format("Hello, %s", username));
                RequestDispatcher rd = request.getRequestDispatcher("/content.jsp");
                rd.include(request, response);
            }  else {
                writer.println(String.format("Wrong password", username));
                RequestDispatcher rd = request.getRequestDispatcher("/sign_in.jsp");
                rd.include(request, response);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        writer.close();
    }
}
