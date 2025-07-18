/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.user.Student;
import um_lms_javafx.server.model.borrower.Borrower;

/**
 *
 * @author Ravin
 */
public class DBBorrowerDAO {
    DBBookDAO bookDAO = new DBBookDAO();
    DBStudentDAO studentDAO = new DBStudentDAO();

    // INSERT BORROWER
    public boolean insertBorrower(Borrower borrower) {
        String sql = "INSERT INTO `borrowers`(`student_id`, `book_id`, `date_issued`, `return_status`) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrower.getStudentID());
            stmt.setInt(2, borrower.getBook().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(borrower.getDateIssued()));
            stmt.setBoolean(4, borrower.getBorrowerStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Insert Borrower]: " + e.getMessage());
            return false;
        }
    }

    // FIND BORROWER BY ID
    public Borrower findBorrowerByID(int borrowerID) {
        String sql = "SELECT * FROM `borrowers` WHERE `borrower_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowerID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToBorrower(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Find Borrower By ID]: " + e.getMessage());
        }
        return null;
    }

    // FIND BORROWERS BY BOOK TITLE
    public List<Borrower> findBorrowersByBookTitle(String title) {
        String sql = "SELECT b.* FROM `borrowers` b "
                   + "JOIN `books` k ON b.book_id = k.book_id "
                   + "WHERE k.title LIKE ?";

        List<Borrower> borrowers = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                borrowers.add(mapResultSetToBorrower(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Find Borrowers By Title]: " + e.getMessage());
        }
        return borrowers;
    }

    // GET ALL BORROWERS
    public List<Borrower> getAllBorrowers() {
        String sql = "SELECT * FROM `borrowers`";
        List<Borrower> borrowers = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                borrowers.add(mapResultSetToBorrower(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Get All Borrowers]: " + e.getMessage());
        }
        return borrowers;
    }

    // UPDATE RETURN STATUS
    public boolean updateReturnStatus(int borrowerID, boolean status) {
        String sql = "UPDATE `borrowers` SET `return_status` = ? WHERE `borrower_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, status);
            stmt.setInt(2, borrowerID);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Update Return Status]: " + e.getMessage());
            return false;
        }
    }

    // DELETE BORROWER
    public boolean deleteBorrower(int borrowerID) {
        String sql = "DELETE FROM `borrowers` WHERE `borrower_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowerID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Delete Borrower]: " + e.getMessage());
            return false;
        }
    }

    // GRAB THE BORROWER WITH PRIMARY keys STUDENT & BOOK
    private Borrower mapResultSetToBorrower(ResultSet rs) throws SQLException {
        int borrowerID = rs.getInt("borrower id");
        int studentID = rs.getInt("student id");
        int bookID = rs.getInt("book id");
        boolean returnStatus = rs.getBoolean("return status");
        
        LocalDateTime dateIssued = rs.getTimestamp("date_issued").toLocalDateTime();
        
        Student student = studentDAO.findStudentByID(studentID);
        Book book = bookDAO.findBookByID(bookID);
        
        if (student == null || book == null) {return null;}
            
        Borrower borrower = new Borrower(borrowerID, student, book, dateIssued);
        borrower.setBorrowerStatus(returnStatus);
        return borrower;
    }
}
