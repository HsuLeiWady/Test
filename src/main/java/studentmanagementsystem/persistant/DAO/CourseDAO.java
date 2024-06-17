package studentmanagementsystem.persistant.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studentmanagementsystem.persistant.DTO.CourseRequestDTO;
import studentmanagementsystem.persistant.DTO.CourseResponseDTO;

public class CourseDAO {
public static Connection con = null;
	
	static {
		con = MyConnection.getConnection();
	}
    //insert
    public static boolean addCourse(CourseRequestDTO courseDTO) {
    	boolean result = true ;
        String sql = "INSERT INTO course(id, name) VALUES(?, ?)";
        try {
        	
        	PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseDTO.getId());
            ps.setString(2, courseDTO.getName());

            result = ps.execute();

        } catch (SQLException e) {
            System.out.println("Insert error!!! " + e);
        }
        return result;
    }

    //selectByCode

	public CourseResponseDTO getCourseById(String id) {
		CourseResponseDTO course = new CourseResponseDTO();
		String sql = "SELECT * FROM course WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			    course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
			
			}
			
		}catch(SQLException e) {
			System.out.println("Select error!!! "+e);
		}
		return course;
	}
	// Get all courses
    public List<CourseResponseDTO> getAllCourses() {
        List<CourseResponseDTO> courses = new ArrayList<>();
        String sql = "SELECT id, name FROM course";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CourseResponseDTO course = new CourseResponseDTO();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}