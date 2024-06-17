package studentmanagementsystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.models.CourseBean;
import studentmanagementsystem.persistant.DAO.CourseDAO;
import studentmanagementsystem.persistant.DTO.CourseResponseDTO;

/**
 * Servlet implementation class DisplayCourseServlet
 */
@WebServlet("/courses")
public class DisplayCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCourseServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();
        List<CourseBean> courses = new ArrayList<>();

        List<CourseResponseDTO> courseDTOs = courseDAO.getAllCourses();
        for (CourseResponseDTO dto : courseDTOs) {
            CourseBean bean = new CourseBean();
            bean.setId(dto.getId());
            bean.setName(dto.getName());
            courses.add(bean);
        }

        request.setAttribute("courses", courses);

        request.getRequestDispatcher("displaycourse.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
