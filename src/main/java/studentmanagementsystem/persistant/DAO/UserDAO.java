package studentmanagementsystem.persistant.DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import studentmanagementsystem.models.UserBean;
import studentmanagementsystem.persistant.DTO.CourseRequestDTO;
import studentmanagementsystem.persistant.DTO.UserRequestDTO;
import studentmanagementsystem.persistant.DTO.UserResponseDTO;

public class UserDAO {
    public static Connection con = null;

    static {
        con = MyConnection.getConnection();
    }

    public static int addUser(UserRequestDTO userDTO) {
        int result = 0;
        String sql = "INSERT INTO user(id, name, email, password, role) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userDTO.getId());
            ps.setString(2, userDTO.getName());
            ps.setString(3, userDTO.getEmail());
            ps.setString(4, userDTO.getPassword());
            ps.setString(5, userDTO.getRole());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert error!!! " + e);
        }
        return result;
    }

    public int updateuser(UserRequestDTO user, int id) {
        int success = 0;
        String sql = "UPDATE user SET name=?, email=?, password=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, id);
            success = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update user error: " + e.getMessage());
        }
        return success;
    }

    public UserResponseDTO getUserById(int userId) {
        UserResponseDTO user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserResponseDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public boolean UserCheck(String name, String password) {
        try (Connection conn = MyConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
               return rs.next(); 
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }

    public int deleteUser(int id) {
        int result = 0;
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete error!!! " + e);
        }
        return result;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserResponseDTO user = new UserResponseDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Select all error!!! " + e);
        }
        return users;
    }
    
    

    public boolean checkLogin(String name, String password) {
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return name.equals(rs.getString("name")) && password.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Select error!!! " + e);
        }
        return false;
    }
}
