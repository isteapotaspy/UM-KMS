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
//   public Student findStudentByEmail(String email) {
//        String sql = "SELECT FROM `library_books` WHERE email = ?";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, email);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new Book(
//                            rs.getBytes("bookCover"),
//                            rs.getInt("id"),
//                            rs.getString("title"),
//                            rs.getString("author"),
//                            rs.getDate("publishedDate"),
//                            rs.getString("genre"),
//                            rs.getString("isbn"),
//                            rs.getInt("edition"),
//                            rs.getInt("pages"),
//                            rs.getString("description"),
//                            rs.getBoolean("status"),    
//                            rs.getInt("copies"),
//                            rs.getString("floor"),
//                            rs.getString("shelf")
//                    );
//                }
//            }
//        }
//   }
}
