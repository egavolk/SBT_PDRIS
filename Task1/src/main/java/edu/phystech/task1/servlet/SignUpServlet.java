package edu.phystech.task1.servlet;

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

        if ("admin".equals(username)) {
            writer.print("Username admin is reserved");
            RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
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

            if (result.next()) {
                writer.print(String.format("Username %s is already used", username));
                RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
                rd.include(request, response);
                writer.close();
                return;
            }

            PreparedStatement insert = con.prepareStatement("insert into users (username, password) values(?, ?)");
            insert.setString(1, username);
            insert.setString(2, password);
            insert.executeUpdate();

            writer.println(String.format("Username %s successfully signed up.", username));
            RequestDispatcher rd = request.getRequestDispatcher("/");
            rd.include(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }

        writer.close();
    }
}
