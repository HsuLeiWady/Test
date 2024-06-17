package studentmanagementsystem.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.models.CourseBean;
import studentmanagementsystem.persistant.DAO.CourseDAO;
import studentmanagementsystem.persistant.DTO.CourseRequestDTO;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("arrived");
		request.getRequestDispatcher("addcourse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("name").equals("")) {

			CourseBean course = new CourseBean();

			course.setName(request.getParameter("name"));

			request.setAttribute("error", "Fields can't be blank!!!");
			request.getRequestDispatcher("addcourse.jsp").forward(request, response);
		} else {

			CourseBean course = new CourseBean();
			course.setName(request.getParameter("name"));

			CourseRequestDTO dto = new CourseRequestDTO();
			dto.setId(course.getId());
			dto.setName(course.getName());

			boolean success = CourseDAO.addCourse(dto);
			if (success) {
				request.setAttribute("message", "Already Registered!!");
				request.getRequestDispatcher("addcourse.jsp").forward(request, response);
			} else {

				// After successfully adding a course
				response.sendRedirect("/StudentRegistration/addcourse.jsp");
			}
		}
	}
}
