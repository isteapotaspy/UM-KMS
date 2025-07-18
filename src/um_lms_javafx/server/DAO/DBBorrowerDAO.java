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

public class DBBorrowerDAO {
    DBBookDAO bookDAO = new DBBookDAO();
    DBStudentDAO studentDAO = new DBStudentDAO();

    // INSERT BORROWER
    public boolean insertBorrower(Borrower borrower) {
        String sql = "INSERT INTO `borrow_history`(`student_id`, `book_id`, `issued_date`, `status`) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrower.getStudentID());
            stmt.setInt(2, borrower.getBook().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(borrower.getDateIssued()));
            stmt.setString(4, borrower.getBorrowerStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Insert Borrower]: " + e.getMessage());
            return false;
        }
    }

    // FIND BY ID
    public Borrower findBorrowerByID(int borrowID) {
        String sql = "SELECT * FROM `borrow_history` WHERE `borrow_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowID);
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

    // FIND BY BOOK TITLE
    public List<Borrower> findBorrowersByBookTitle(String title) {
        String sql = "SELECT b.* FROM `borrow_history` b "
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

    // GET ALL
    public List<Borrower> getAllBorrowers() {
        String sql = "SELECT * FROM `borrow_history`";
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

    // UPDATE STATUS
    public boolean updateReturnStatus(int borrowID, String status) {
        String sql = "UPDATE `borrow_history` SET `status` = ? WHERE `borrow_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, borrowID);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Update Return Status]: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteBorrower(int borrowID) {
        String sql = "DELETE FROM `borrow_history` WHERE `borrow_id` = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, borrowID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error [Delete Borrower]: " + e.getMessage());
            return false;
        }
    }

    // MAP RESULTSET
    private Borrower mapResultSetToBorrower(ResultSet rs) throws SQLException {
        int borrowID = rs.getInt("borrow_id");
        int studentID = rs.getInt("student_id");
        int bookID = rs.getInt("book_id");
        String status = rs.getString("status");
        LocalDateTime issuedDate = rs.getTimestamp("issued_date").toLocalDateTime();

        Student student = studentDAO.findStudentByID(studentID);
        Book book = bookDAO.findBookByID(bookID);

        if (student == null || book == null) return null;

        Borrower borrower = new Borrower(student, book, status);
        return borrower;
    }

    // ROW INDEX
    public int getRowIndexByBorrowerId(int borrowId) {
        String sql = "SELECT borrow_id FROM borrow_history ORDER BY borrow_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int index = 1;
            while (rs.next()) {
                if (rs.getInt("borrow_id") == borrowId) return index;
                index++;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving row index: " + e.getMessage());
        }
        return -1;
    }

    // TOTAL ROWS
    public int getTotalBorrowerHistoryRows() {
        String sql = "SELECT COUNT(*) AS total FROM borrow_history";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt("total");
        } catch (SQLException e) {
            System.out.println("Error getting total rows: " + e.getMessage());
        }
        return 0;
    }
}
