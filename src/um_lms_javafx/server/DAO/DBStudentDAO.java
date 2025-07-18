/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.user.Student;

/**
 *
 * @author Ravin
 */
public class DBStudentDAO {

    // CREATE STUDENT    
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO `library_users`("
                + "`first_name`, `middle_name`, `last_name`, `email`, `phone_number`, "
                + "`profile_picture`, `admin_access`, `password`, `department_ID`, "
                + "`user_id`, `books_issued`, `current_books_issued`, `date_created`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getMiddleName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhoneNumber());
            statement.setString(6, null);
            statement.setBoolean(7, false);
            statement.setString(8, student.getPassword());
            statement.setInt(9, 0);
            statement.setInt(10, student.getStudentID());
            statement.setInt(11, 0);
            statement.setInt(12, 0);
            statement.setTimestamp(13, Timestamp.from(Instant.now()));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Insert Student]: " + e.getMessage());
            return false;
        }
    }

    //FOR STSUDENTS MANAGER IN ADMIN
    public ObservableList<Student> loadStudents() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();

        String sql = "SELECT user_id,"
                + "       CONCAT_WS(' ', first_name, middle_name, last_name) AS full_name,"
                + "       email,"
                + "       date_created,"
                + "       books_issued FROM library_users";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getTimestamp("date_created").toLocalDateTime(),
                        rs.getInt("books_issued")
                );
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return studentList;
    }

    // FIND STUDENT BY EMAIL
    public Student findStudentByEmail(String email) {
        String sql = "SELECT * FROM `library_users` WHERE email = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("password")
                );
                student.setStudentID(rs.getInt("user_id"));
                student.setAdminAccess(rs.getBoolean("admin_access"));

                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Find Student By Email]: " + e.getMessage());
        }
        return null;
    }

    // AUTHENTICATION
    public AuthenticationState authenticateUser(String email, String password) {
        Student student = findStudentByEmail(email);
        if (student == null) {
            return AuthenticationState.NO_USER_EXISTS;
        }
        if (!student.getPassword().equals(password)) {
            return AuthenticationState.INCORRECT_PASSWORD;
        }
        if (student.getAdminAccess()) {
            return AuthenticationState.ADMIN_ACCESS_GRANTED;
        } else {
            return AuthenticationState.STUDENT_ACCESS_GRANTED;
        }
    }

    // FIND STUDENT BY ID
    public Student findStudentByID(int studentID) {
        String sql = "SELECT * FROM `library_users` WHERE `user_id` = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("password")
                );
                student.setStudentID(rs.getInt("user_id"));
                student.setAdminAccess(rs.getBoolean("admin_access"));

                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Find Student By ID]: " + e.getMessage());
        }
        return null;
    }

    // FIND STUDENTS BY NAME (first, middle, or last) USING LIKE
    public List<Student> findStudentsByName(String nameQuery) {
        String sql = "SELECT * FROM `library_users` WHERE "
                + "`first_name` LIKE ? OR `middle_name` LIKE ? OR `last_name` LIKE ?";
        List<Student> results = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            String likeQuery = "%" + nameQuery + "%";
            stmt.setString(1, likeQuery);
            stmt.setString(2, likeQuery);
            stmt.setString(3, likeQuery);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("password")
                );
                student.setStudentID(rs.getInt("user_id"));
                student.setAdminAccess(rs.getBoolean("admin_access"));
                results.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Find Students By Name]: " + e.getMessage());
        }
        return results;
    }

}
