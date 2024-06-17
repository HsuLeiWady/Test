package studentmanagementsystem.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.models.UserBean;
import studentmanagementsystem.persistant.DAO.UserDAO;
import studentmanagementsystem.persistant.DTO.UserResponseDTO;
import studentmanagementsystem.persistant.DTO.UserRequestDTO;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new UserDAO();  // Assuming default constructor initializes the database connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID");
            return;
        }

        UserResponseDTO dto = dao.getUserById(id);
        if (dto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        UserBean viewModel = new UserBean();
        viewModel.setId(dto.getId());
        viewModel.setName(dto.getName());
        viewModel.setEmail(dto.getEmail());
        viewModel.setPassword(dto.getPassword());
      

        request.setAttribute("user", viewModel);
        request.getRequestDispatcher("updateuser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        int id = Integer.parseInt(idParam);
        System.out.println("string : " + idParam + " " + "integer : " + id);
System.out.println("update servlet: " + id);
        UserBean user = new UserBean();
        user.setId(id);
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
       

        UserRequestDTO dto = new UserRequestDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
       

        dao.updateuser(dto,id);
        response.sendRedirect("DisplayUserServlet");
    }
}
