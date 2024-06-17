package studentmanagementsystem.persistant.DAO;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentmanagementsystem.persistant.DTO.CourseResponseDTO;
import studentmanagementsystem.persistant.DTO.StudentRequestDTO;
import studentmanagementsystem.persistant.DTO.StudentResponseDTO;

public class StudentDAO {
    public static Connection con = MyConnection.getConnection();

    // Insert
    public int addStudent(StudentRequestDTO student) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO student(name, dob, gender, phno, education, attend, photo) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getPhno());
            ps.setString(5, student.getEducation());
            ps.setString(6, student.getAttend());

            if (student.getPhoto() != null) {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(student.getPhoto());
                ps.setBlob(7, blob);
            } else {
                ps.setNull(7, java.sql.Types.BLOB);
            }

            result = ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();
            int studentId = 0;
            if (generatedKeys.next()) {
                studentId = generatedKeys.getInt(1);
            }

            if (result != 0 && studentId != 0) {
                for (String courseId : student.getCourse_id()) {
                    sql = "INSERT INTO student_has_course(student_id, course_id) VALUES(?, ?)";
                    try (PreparedStatement psCourse = con.prepareStatement(sql)) {
                        psCourse.setInt(1, studentId);
                        psCourse.setInt(2, Integer.parseInt(courseId));
                        psCourse.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // rethrow the exception after logging it
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (ps != null) ps.close();
        }
        return result;
    }
    
    public int newUpdateStudent(StudentRequestDTO student , String id) {
    	int success = 0;
    	String sql = "UPDATE student SET name=?, dob=?, gender=?, "
    			+ "phno=?, education=?, attend=?, photo=? WHERE id=?";
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getDob());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getPhno());
            ps.setString(5, student.getEducation());
            ps.setString(6, student.getAttend());
            if (student.getPhoto() != null) {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(student.getPhoto());
                ps.setBlob(7, blob);
            } else {
                ps.setNull(7, java.sql.Types.BLOB);
            }
            ps.setString(8, id);
            success = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update student : "+ e.getMessage());
		}
    	return success;
    }

    // Update student details including courses
    public int updateStudent(StudentRequestDTO student) throws SQLException {
        String updateStudentSql = "UPDATE student SET name=?, dob=?, gender=?, phno=?, education=?, attend=?, photo=? WHERE id=?";
        String deleteCoursesSql = "DELETE FROM student_has_course WHERE student_id=?";
        String insertCourseSql = "INSERT INTO student_has_course(student_id, course_id) VALUES(?, ?)";

        int updateResult = 0;

        try {
            con.setAutoCommit(false);  // Start transaction

            try (PreparedStatement ps = con.prepareStatement(updateStudentSql)) {
                ps.setString(1, student.getName());
                ps.setString(2, student.getDob());
                ps.setString(3, student.getGender());
                ps.setString(4, student.getPhno());
                ps.setString(5, student.getEducation());
                ps.setString(6, student.getAttend());
                if (student.getPhoto() != null) {
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(student.getPhoto());
                    ps.setBlob(7, blob);
                } else {
                    ps.setNull(7, java.sql.Types.BLOB);
                }

                ps.setInt(8, student.getId());

                updateResult = ps.executeUpdate();
            }

            if (updateResult > 0) {
                try (PreparedStatement psDelete = con.prepareStatement(deleteCoursesSql)) {
                    psDelete.setInt(1, student.getId());
                    psDelete.executeUpdate();
                }

                if (student.getCourse_id() != null) {
                    for (String courseId : student.getCourse_id()) {
                        try (PreparedStatement psInsert = con.prepareStatement(insertCourseSql)) {
                            psInsert.setInt(1, student.getId());
                            psInsert.setInt(2, Integer.parseInt(courseId));
                            psInsert.executeUpdate();
                        }
                    }
                }
            }

            con.commit();  // Commit transaction
        } catch (SQLException e) {
            con.rollback();  // Rollback transaction on error
            e.printStackTrace();
            throw e;
        } finally {
            con.setAutoCommit(true);  // Reset to default
        }

        return updateResult;
    }

    // Select by ID
    public StudentResponseDTO getStudentById(int studentId) throws SQLException {
        StudentResponseDTO student = null;
        String sql = "SELECT s.*, c.* " +
                     "FROM student s " +
                     "LEFT JOIN student_has_course shc ON s.id = shc.student_id " +
                     "LEFT JOIN course c ON shc.course_id = c.id " +
                     "WHERE s.id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (student == null) {
                    student = new StudentResponseDTO();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setDOB(rs.getString("dob"));
                    student.setGender(rs.getString("gender"));
                    student.setPhno(rs.getString("phno"));
                    student.setEducation(rs.getString("education"));
                    student.setAttend(rs.getString("attend"));
                    student.setPhoto(rs.getBinaryStream("photo"));
                }

                String courseName = rs.getString("c.name");
                if (courseName != null) {
                    CourseResponseDTO course = new CourseResponseDTO();
                    course.setName(courseName);
                    if (student.getCourses() == null) {
                        student.setCourses(new ArrayList<>());
                    }
                    student.getCourses().add(course);
                }
            }
        }
        return student;
    }

    // Delete
    public int deleteStudent(int id) {
        int result = 0;
        String deleteCoursesSql = "DELETE FROM student_has_course WHERE student_id = ?";
        String deleteStudentSql = "DELETE FROM student WHERE id = ?";
        
        try {
            con.setAutoCommit(false); // Start transaction

            // Delete associated records in student_has_course table
            try (PreparedStatement psDeleteCourses = con.prepareStatement(deleteCoursesSql)) {
                psDeleteCourses.setInt(1, id);
                psDeleteCourses.executeUpdate();
            }

            // Delete student record
            try (PreparedStatement psDeleteStudent = con.prepareStatement(deleteStudentSql)) {
                psDeleteStudent.setInt(1, id);
                result = psDeleteStudent.executeUpdate();
            }

            con.commit(); // Commit transaction
        } catch (SQLException e) {
            try {
                con.rollback(); // Rollback transaction on error
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true); // Reset to default
            } catch (SQLException resetException) {
                resetException.printStackTrace();
            }
        }
        return result;
    }

    // Get all students with pagination
    public List<StudentResponseDTO> getAllStudents(int page, int recordsPerPage) throws SQLException {
        List<StudentResponseDTO> students = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.dob, s.gender, s.phno, s.education, s.attend, s.photo, c.id AS course_id, c.name AS course_name " +
                     "FROM student s " +
                     "LEFT JOIN student_has_course shc ON s.id = shc.student_id " +
                     "LEFT JOIN course c ON shc.course_id = c.id " +
                     "LIMIT ? OFFSET ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, recordsPerPage);
            ps.setInt(2, (page - 1) * recordsPerPage);
            ResultSet rs = ps.executeQuery();
            Map<Integer, StudentResponseDTO> studentMap = new HashMap<>();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                StudentResponseDTO student = studentMap.get(studentId);
                if (student == null) {
                    student = new StudentResponseDTO();
                    student.setId(studentId);
                    student.setName(rs.getString("name"));
                    student.setDOB(rs.getString("dob"));
                    student.setGender(rs.getString("gender"));
                    student.setPhno(rs.getString("phno"));
                    student.setEducation(rs.getString("education"));
                    student.setAttend(rs.getString("attend"));
                    student.setPhoto(rs.getBinaryStream("photo"));
                    student.setCourses(new ArrayList<>());
                    students.add(student);
                    studentMap.put(studentId, student);
                }
                if (rs.getString("course_name") != null) {
                    CourseResponseDTO course = new CourseResponseDTO();
                    course.setId(rs.getInt("course_id"));
                    course.setName(rs.getString("course_name"));
                    student.getCourses().add(course);
                }
            }
        }
        return students;
    }

    // Get total number of students
    public int getTotalNumberOfStudents() throws SQLException {
        int totalRecords = 0;
        String sql = "SELECT COUNT(*) AS total FROM student";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalRecords = rs.getInt("total");
            }
        }
        return totalRecords;
    }

    // Search students
    public List<StudentResponseDTO> searchStudents(StudentResponseDTO criteria) throws SQLException {
        List<StudentResponseDTO> students = new ArrayList<>();
        String sql = "SELECT s.*, c.* FROM student s " +
                     "LEFT JOIN student_has_course shc ON s.id = shc.student_id " +
                     "LEFT JOIN course c ON shc.course_id = c.id " +
                     "WHERE s.id IS NOT NULL";

        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            sql += " AND s.name LIKE ?";
        }
        if (criteria.getAttend() != null && !criteria.getAttend().isEmpty()) {
            sql += " AND s.attend LIKE ?";
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int paramIndex = 1;
            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                ps.setString(paramIndex++, "%" + criteria.getName() + "%");
            }
            if (criteria.getAttend() != null && !criteria.getAttend().isEmpty()) {
                ps.setString(paramIndex++, "%" + criteria.getAttend() + "%");
            }

            ResultSet rs = ps.executeQuery();
            Map<Integer, StudentResponseDTO> studentMap = new HashMap<>();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                StudentResponseDTO student = studentMap.get(studentId);
                if (student == null) {
                    student = new StudentResponseDTO();
                    student.setId(studentId);
                    student.setName(rs.getString("name"));
                    student.setDOB(rs.getString("dob"));
                    student.setGender(rs.getString("gender"));
                    student.setPhno(rs.getString("phno"));
                    student.setEducation(rs.getString("education"));
                    student.setAttend(rs.getString("attend"));
                    student.setPhoto(rs.getBinaryStream("photo"));
                    student.setCourses(new ArrayList<>());
                    students.add(student);
                    studentMap.put(studentId, student);
                }
                if (rs.getString("c.name") != null) {
                    CourseResponseDTO course = new CourseResponseDTO();
                    course.setId(rs.getInt("c.id"));
                    course.setName(rs.getString("c.name"));
                    student.getCourses().add(course);
                }
            }
        }
        return students;
    }
}
