    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.borrower.BorrowHistory;

/**
 *
 * @author jeanv
 */
public class DBBookDAO {

    //CREATE
    public boolean insertBook(Book book) {
        String sql = "INSERT INTO `library_books`(`bookCover`, `id`, `title`, `author`, "
                + "`publishedDate`, `genre`, `ISBN`, `edition`, `pages`, `description`, "
                + "`status`, `copies`, `floor`, `shelf`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBytes(1, book.getBookCover());
            statement.setInt(2, book.getId());
            statement.setString(3, book.getTitle());
            statement.setString(4, book.getAuthor());
            statement.setDate(5, java.sql.Date.valueOf(book.getPublishedDate()));
            statement.setString(6, book.getGenre());
            statement.setString(7, book.getIsbn());
            statement.setInt(8, book.getEdition());
            statement.setInt(9, book.getPages());
            statement.setString(10, book.getDescription());
            statement.setBoolean(11, book.getStatus());
            statement.setInt(12, book.getCopies());
            statement.setString(13, book.getFloor());
            statement.setString(14, book.getShelf());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    //READ ALL
    public ObservableList<Book> getAllBooks() throws SQLException {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM library_books";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookList.add(new Book(
                        rs.getBytes("bookCover"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publishedDate").toLocalDate(),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("edition"),
                        rs.getInt("pages"),
                        rs.getString("description"),
                        rs.getBoolean("status"),
                        rs.getInt("copies"),
                        rs.getString("floor"),
                        rs.getString("shelf")
                ));
            }
        }
        return bookList;
    }

    //Read by id
    public Book getBookById(int id) throws SQLException {
        String sql = "SELECT * FROM `library_books` WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getBytes("bookCover"),
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getDate("publishedDate").toLocalDate(),
                            rs.getString("genre"),
                            rs.getString("isbn"),
                            rs.getInt("edition"),
                            rs.getInt("pages"),
                            rs.getString("description"),
                            rs.getBoolean("status"),
                            rs.getInt("copies"),
                            rs.getString("floor"),
                            rs.getString("shelf")
                    );
                }
            }
        }
        return null;
    }

    //UPDATE
    public boolean updateBook(Book book) {
        String sql = "UPDATE `library_books` SET `bookCover`= ?, `title`= ?, `author`= ?, "
                + "`publishedDate`= ?, `genre`= ?, `ISBN`= ?, `edition`= ?, `pages`= ?, "
                + "`description`= ?, `status`= ?, `copies`= ?, `floor`= ?, `shelf`= ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setBytes(1, book.getBookCover());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setDate(4, java.sql.Date.valueOf(book.getPublishedDate())); // converts java.util.Date
            statement.setString(5, book.getGenre());
            statement.setString(6, book.getIsbn());
            statement.setInt(7, book.getEdition());
            statement.setInt(8, book.getPages());
            statement.setString(9, book.getDescription());
            statement.setBoolean(10, book.getStatus());
            statement.setInt(11, book.getCopies());
            statement.setString(12, book.getFloor());
            statement.setString(13, book.getShelf());
            statement.setInt(14, book.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //DELETE
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM `library_books` WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TableView
    public static ObservableList<Book> loadBooks() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `library_books`";

        try (
                Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getBytes("bookCover"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publishedDate").toLocalDate(),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("edition"),
                        rs.getInt("pages"),
                        rs.getString("description"),
                        rs.getBoolean("status"),
                        rs.getInt("copies"),
                        rs.getString("floor"),
                        rs.getString("shelf")
                );
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return bookList;
    }

    //Borrow Book
    public void borrowBook(int studentID, int bookID) throws SQLException {
        Connection conn = DBConnection.getConnection();

        //inserting into borrow history
        String borrowSQL = "INSERT INTO `borrow_history`(`status`, `student_id`, `book_id`, "
                + "`issued_date`, expiry_date) VALUES ('Borrowed', ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 3 DAY) ";

        PreparedStatement stmtBorrow = conn.prepareStatement(borrowSQL);
        stmtBorrow.setInt(1, studentID);
        stmtBorrow.setInt(2, bookID);
        stmtBorrow.executeUpdate();

        //incrementing current and total books issued
        String updateStudentSQL = "UPDATE library_users SET current_books_issued = current_books_issued + 1, books_issued = books_issued + 1 WHERE id = ?";
        PreparedStatement stmtStudent = conn.prepareStatement(updateStudentSQL);
        stmtStudent.setInt(1, studentID);
        stmtStudent.executeUpdate();

        //Decrementing copies of a book when borrowed
        String updateBookSQL = "UPDATE library_books SET copies = copies - 1 WHERE id = ?";
        PreparedStatement stmtBook = conn.prepareStatement(updateBookSQL);
        stmtBook.setInt(1, bookID);
        stmtBook.executeUpdate();

        conn.close();
    }

    //Return Book
    public void returnBook(int studentID, int bookID) throws SQLException {
        Connection conn = DBConnection.getConnection();

        String returnSQL = "UPDATE borrow_history SET returned_date = CURDATE(), status = 'Returned' "
                + "WHERE student_id = ? AND book_id = ? AND status = 'Borrowed'";
        PreparedStatement stmtReturn = conn.prepareStatement(returnSQL);
        stmtReturn.setInt(1, studentID);
        stmtReturn.setInt(2, bookID);
        stmtReturn.executeUpdate();

        //decrementing current books issued
        String updateStudentSQL = "UPDATE library_users SET current_books_issued = current_books_issued - 1 WHERE id = ?";
        PreparedStatement stmtStudent = conn.prepareStatement(updateStudentSQL);
        stmtStudent.setInt(1, studentID);
        stmtStudent.executeUpdate();

        //incrementing copies of a book when returned
        String updateBookSQL = "UPDATE library_books SET copies = copies + 1 WHERE id = ?";
        PreparedStatement stmtBook = conn.prepareStatement(updateBookSQL);
        stmtBook.setInt(1, bookID);
        stmtBook.executeUpdate();

        conn.close();
    }

    //Setting Up Borrow History
    public ObservableList<BorrowHistory> getBorrowHistory(int studentID) throws SQLException {
        ObservableList<BorrowHistory> historyList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT bh.id AS borrow_id, bh.student_id, bh.book_id, bh.issued_date, bh.returned_date, bh.expiry_date, bh.status "
                + "FROM borrow_history bh "
                + "JOIN library_users u ON bh.student_id = u.id "
                + "JOIN library_books lb ON bh.book_id = lb.id "
                + "WHERE bh.student_id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            historyList.add(new BorrowHistory(
                    rs.getInt("borrowId"),
                    rs.getString("status"),
                    rs.getInt("studentId"),
                    rs.getInt("bookId"),
                    rs.getTimestamp("dateIssued").toLocalDateTime(),
                    rs.getDate("expiryDate").toLocalDate(),
                    rs.getTimestamp("dateReturned") != null ? rs.getTimestamp("dateReturned").toLocalDateTime() : null
            ));
        }

        conn.close();
        return historyList;
    }

    //Load Books to New Books Table in Dashboard Admin Page
    public ObservableList<Book> getNewBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "SELECT title, id, copies FROM library_books WHERE date_created >= CURDATE() - INTERVAL 7 DAY ORDER BY date_created DESC";
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            books.add(new Book(rs.getString("title"), rs.getInt("id"), rs.getInt("copies")));
        }
        return books;
    }

    //Count Total Issued Books
    public int countIssuedBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM borrow_history WHERE status = 'Borrowed'";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    //Count Returned Books
    public int countReturnedBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM borrow_history WHERE status = 'Returned'";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    //Count Total Books in the Library
    public int countTotalBooks() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM library_books";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    //FOR DASHBOARD IN ADMIN (LINE GRAPH)
    public List<XYChart.Data<String, Number>> getBooksIssuedThisWeek() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = """
        SELECT DATE(issued_date) as day, COUNT(*) as total
        FROM borrow_history
        WHERE issued_date >= CURDATE() - INTERVAL 6 DAY AND status = 'Borrowed'
        GROUP BY DATE(issued_date)
        ORDER BY DATE(issued_date)
    """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<XYChart.Data<String, Number>> data = new ArrayList<>();
        while (rs.next()) {
            data.add(new XYChart.Data<>(rs.getString("day"), rs.getInt("total")));
        }
        return data;
    }

    public List<XYChart.Data<String, Number>> getBooksReturnedThisWeek() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = """
        SELECT DATE(returned_date) AS day, COUNT(*) AS total
        FROM borrow_history
        WHERE returned_date >= CURDATE() - INTERVAL 6 DAY AND status = 'Returned'
        GROUP BY day
        ORDER BY day
    """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<XYChart.Data<String, Number>> data = new ArrayList<>();
        while (rs.next()) {
            data.add(new XYChart.Data<>(rs.getString("day"), rs.getInt("total")));
        }
        return data;
    }

    public List<XYChart.Data<String, Number>> getNewBooksThisWeek() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = """
        SELECT DATE(date_created) AS day, COUNT(*) AS total
        FROM library_books
        WHERE date_created >= CURDATE() - INTERVAL 6 DAY
        GROUP BY day
        ORDER BY day
    """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<XYChart.Data<String, Number>> data = new ArrayList<>();
        while (rs.next()) {
            data.add(new XYChart.Data<>(rs.getString("day"), rs.getInt("total")));
        }
        return data;
    }
    
    public Book findBookByID(int bookID) throws SQLException {
        String sql = "SELECT * FROM library_books WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                return new Book(
                        rs.getBytes("bookCover"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publishedDate").toLocalDate(),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("edition"),
                        rs.getInt("pages"),
                        rs.getString("description"),
                        rs.getBoolean("status"),
                        rs.getInt("copies"),
                        rs.getString("floor"),
                        rs.getString("shelf")
                );
            }
        }
            }
        return null; // not found
    }
}
