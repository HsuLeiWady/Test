package studentmanagementsystem.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentmanagementsystem.persistant.DAO.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the username and password from the request
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        // Debugging: Print the retrieved username and password
        System.out.println("Retrieved credentials: Username: " + username + ", Password: " + password);

        // Validate the retrieved parameters
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Instantiate the UserDAO to check user credentials
            UserDAO userDao = new UserDAO();
            boolean isValidUser = userDao.UserCheck(username, password);

            if (isValidUser) {
                // Valid credentials: create a new session and set the username
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Forward to the home page
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
            	request.setAttribute("message", "Please check your data again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                }
        } else {
        	request.setAttribute("message", "Please check your data again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
