package studentmanagementsystem.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentmanagementsystem.persistant.DAO.UserDAO;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao = new UserDAO();

    public DeleteUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);

            UserDAO dao = new UserDAO();
            dao.deleteUser(id);

            response.sendRedirect("DisplayUserServlet");
        } else {
            // Handle the case when "id" parameter is missing or empty
            // You can redirect to an error page or display an error message
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter is missing or empty");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
