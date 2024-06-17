package studentmanagementsystem.controllers;

import studentmanagementsystem.persistant.DAO.StudentDAO;
import studentmanagementsystem.persistant.DTO.StudentRequestDTO;
import studentmanagementsystem.persistant.DTO.StudentResponseDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/updateStudent")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateStudentServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            StudentResponseDTO student = studentDAO.getStudentById(studentId);
            request.setAttribute("student", student);
            request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            throw new ServletException("Cannot obtain student from DB", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String phno = request.getParameter("phno");
            String education = request.getParameter("education");
            String attend = request.getParameter("attend");
            String[] courseIds = request.getParameterValues("courses");
            System.out.println(id+" , "+name+" , "+gender);
            InputStream inputStream = null;
            Part filePart = request.getPart("photo");
            if (filePart != null && filePart.getSize() > 0) {
                inputStream = filePart.getInputStream();
            }

            StudentRequestDTO student = new StudentRequestDTO();
            student.setId(Integer.parseInt(id));
            student.setName(name);
            student.setDob(dob);
            student.setGender(gender);
            student.setPhno(phno);
            student.setEducation(education);
            student.setAttend(attend);
            student.setCourse_id(courseIds);
System.out.println(student.getId());
            if (inputStream != null) {
                byte[] photoBytes = inputStream.readAllBytes();
                student.setPhoto(photoBytes);
            }

            int result = studentDAO.newUpdateStudent(student , id);
            if (result > 0) {
                response.sendRedirect("/StudentRegistration/DisplayStudentServlet");
            } else {
                throw new ServletException("Failed to update student.");
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Error updating student", e);
        }
    }
}