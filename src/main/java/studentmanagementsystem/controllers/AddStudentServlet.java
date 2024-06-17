package studentmanagementsystem.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import studentmanagementsystem.persistant.DAO.CourseDAO;
import studentmanagementsystem.persistant.DAO.StudentDAO;
import studentmanagementsystem.persistant.DTO.CourseResponseDTO;
import studentmanagementsystem.persistant.DTO.StudentRequestDTO;

@WebServlet("/addstudents")
@MultipartConfig
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();

    public AddStudentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CourseResponseDTO> courses;
        courses = courseDAO.getAllCourses();
		request.setAttribute("courses", courses);
		request.getRequestDispatcher("addstudent.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] courseIds = request.getParameterValues("courses");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phno = request.getParameter("phno");
        String education = request.getParameter("education");
        String attend = request.getParameter("attend");

        if (attend == null || attend.trim().isEmpty()) {
            attend = "default_value"; // Replace with an appropriate default value
        }

        Part filePart = request.getPart("photo");
        byte[] photo = null;

        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                photo = inputStream.readAllBytes();
            }
        }

        StudentRequestDTO student = new StudentRequestDTO();
        student.setName(name);
        student.setDob(dob);
        student.setGender(gender);
        student.setPhno(phno);
        student.setEducation(education);
        student.setAttend(attend);  // Ensure this is not null

        if (courseIds != null) {
            student.setCourse_id(Arrays.asList(courseIds));
        } else {
            student.setCourse_id(new ArrayList<>()); // Set to an empty list if no courses are selected
        }

        student.setPhoto(photo);

        try {
            studentDAO.addStudent(student);
            response.sendRedirect("DisplayStudentServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while adding the student. Please try again.");
            request.getRequestDispatcher("addstudent.jsp").forward(request, response);
        }
    }
}