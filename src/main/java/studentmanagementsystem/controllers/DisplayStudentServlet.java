package studentmanagementsystem.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentmanagementsystem.models.StudentBean;
import studentmanagementsystem.persistant.DAO.StudentDAO;
import studentmanagementsystem.persistant.DTO.StudentResponseDTO;

@WebServlet("/DisplayStudentServlet")
public class DisplayStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public DisplayStudentServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO();  // Initialize StudentDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;

        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            List<StudentBean> students = new ArrayList<>();
            int totalRecords = studentDAO.getTotalNumberOfStudents();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
            List<StudentResponseDTO> studentDTO = studentDAO.getAllStudents(page, recordsPerPage);

            for(StudentResponseDTO dto : studentDTO) {
                StudentBean bean = new StudentBean();
                bean.setId(dto.getId());
                bean.setName(dto.getName());
                bean.setDob(dto.getDOB());
                bean.setGender(dto.getGender());
                bean.setPhno(dto.getPhno());
                bean.setEducation(dto.getEducation());

                if (dto.getPhoto() != null) {
                    byte[] photoBytes = dto.getPhoto().readAllBytes();
                    String base64Photo = java.util.Base64.getEncoder().encodeToString(photoBytes);
                    bean.setPhoto(base64Photo);
                }

                bean.setAttend(dto.getAttend());
                students.add(bean);
            }

            request.setAttribute("students", students);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.getRequestDispatcher("displaystudent.jsp").forward(request, response);
        } catch (SQLException e) {
            System.out.println("Error in DisplayStudentServlet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
