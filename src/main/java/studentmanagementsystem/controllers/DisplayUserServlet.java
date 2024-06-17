package studentmanagementsystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.models.UserBean;
import studentmanagementsystem.persistant.DAO.UserDAO;
import studentmanagementsystem.persistant.DTO.UserResponseDTO;

@WebServlet("/DisplayUserServlet")
public class DisplayUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public DisplayUserServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserBean> users = new ArrayList<>();
        try {
            List<UserResponseDTO> userDTOs = userDAO.getAllUsers();
            for (UserResponseDTO dto : userDTOs) {
                UserBean bean = new UserBean();
                bean.setId(dto.getId());
                bean.setName(dto.getName());
                bean.setEmail(dto.getEmail());
                bean.setPassword(dto.getPassword());
                bean.setRole(dto.getRole());
                users.add(bean);
            }
            request.setAttribute("userList", users);
            request.getRequestDispatcher("displayuser.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching users.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
