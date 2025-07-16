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
import um_lms_javafx.server.model.book.Book;

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
            statement.setDate(5, new java.sql.Date(book.getPublishedDate().getTime()));
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
                        rs.getDate("publishedDate"),
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
                            rs.getDate("publishedDate"),
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
    public boolean updateBook(byte[] newBookCover, int id, String newTitle, String newAuthor, Date newPublishedDate, String newGenre,
            String newIsbn, int newEdition, int newPages, String newDescription, boolean newStatus,
            int newCopies, String newFloor, String newShelf) throws SQLException {
        String sql = "UPDATE `library_books` SET `bookCover`= ?, `title`= ?,`author`= ?,"
                + "`publishedDate`= ?,`genre`= ?,`ISBN`= ?,`edition`= ?,`pages`= ?,"
                + "`description`= ?,`status`= ?,`copies`= ?,`floor`= ?,`shelf`= ? WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBytes(1, newBookCover);
            statement.setString(2, newTitle);
            statement.setString(3, newAuthor);
            statement.setDate(4, newPublishedDate);
            statement.setString(5, newGenre);
            statement.setString(6, newIsbn);
            statement.setInt(7, newEdition);
            statement.setInt(8, newPages);
            statement.setString(9, newDescription);
            statement.setBoolean(10, newStatus);
            statement.setInt(11, newCopies);
            statement.setString(12, newFloor);
            statement.setString(13, newShelf);
            statement.setInt(14, id);
            return statement.executeUpdate() > 0;
        }
    }

    //DELETE
    public boolean deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM `library_books` WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
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
                        rs.getDate("publishedDate"),
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
}
