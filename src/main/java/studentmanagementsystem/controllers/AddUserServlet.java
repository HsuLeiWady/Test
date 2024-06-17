package studentmanagementsystem.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.models.UserBean;
import studentmanagementsystem.persistant.DAO.UserDAO;
import studentmanagementsystem.persistant.DTO.UserRequestDTO;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("adduser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (id == null || id.isEmpty() ||
            name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            role == null || role.isEmpty()) {

            request.setAttribute("error", "Fields can't be blank!!!");
            request.getRequestDispatcher(".jsp").forward(request, response);
        } else {
            UserBean user = new UserBean();
            user.setId(Integer.parseInt(id));
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            UserRequestDTO dto = new UserRequestDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setRole(user.getRole());

            UserDAO.addUser(dto);

            // After successfully adding a user
            response.sendRedirect("adduser.jsp?successMessage=User added successfully");
        }
    }
}
