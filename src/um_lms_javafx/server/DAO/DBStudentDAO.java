/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.user.Student;

/**
 *
 * @author Ravin
 */
public class DBStudentDAO {
   
    // CREATE STUDENT    
   public boolean insertStudent(Student student) {
       String sql = "INSERT INTO `library_users`(`first name`, `middle name`, `last name`, `email`, `phone number`,"
               + " `profile picture`, `admin access`, `password`, `department ID`, `user id`) "
               + "VALUES (?,?,?,?,?,NULL,0, ?,NULL,?)";
       
       try (Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getMiddleName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhoneNumber());
            statement.setString(6, student.getPassword());
            statement.setInt(7, student.getStudentID());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
   }
   
//   // READ
    public Student findStudentByEmail(String email) {
        String sql = "SELECT * FROM `library_users` WHERE email = ?";
        try (Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Student student = new Student(
                    rs.getString("first name"),
                    rs.getString("middle name"),
                    rs.getString("last name"),
                    rs.getString("email"),
                    rs.getString("phone number"),
                    rs.getString("password")
            );
            student.setStudentID(rs.getInt("user id"));
            student.setAdminAccess(rs.getBoolean("admin access"));

            return student;
        }
    } catch (SQLException e) {
            e.printStackTrace(); // You might want to log this properly
        } return null;
    }
    
    
    public AuthenticationState authenticateUser(String email, String password) {
        Student student = findStudentByEmail(email);
        if (student == null) {
            return AuthenticationState.NO_USER_EXISTS;
        }
        if (!student.getPassword().equals(password)) {
            return AuthenticationState.INCORRECT_PASSWORD;
        }
        if(student.getAdminAccess()){
            return AuthenticationState.ADMIN_ACCESS_GRANTED;
        } else return AuthenticationState.STUDENT_ACCESS_GRANTED;    
    }
}
