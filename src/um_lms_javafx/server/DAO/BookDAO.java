/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import um_lms_javafx.server.model.book.Book;
/**
 *
 * @author Ravin
 */
public class BookDAO {
    
    private List<Book> bookList = new ArrayList<>();
    private int id = 1;
    
    //CREATE
    public void insertBook(String title, String author, LocalDateTime publishedDate, String genre, String isbn,
            String edition, String pages, boolean status, int copies, String floor, String shelf) {
        Book book = new Book(id++, title, author, status, copies, publishedDate, genre, isbn, edition, pages, floor, shelf);
        bookList.add(book);
    }
    //READ ALL
    public List<Book> getAllBooks() {
        return bookList;
    }
    //READ by ID
    public Book getBookById(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; 
    }
    //READ by TITLE
    public Book getBookByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle() == title) {
                return book;
            }
        }
        return null; 
    } //CAN BE APPLIED ON OTHERS
    
    //UPDATE by ID
    public boolean updateBookByID(int id, String title, String newTitle, String newAuthor, LocalDateTime newPublishedDate, String newGenre, String newIsbn,
            String newEdition, String newPages, boolean newStatus, int newCopies, String newFloor, String newShelf) {
        Book book = getBookById(id);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPublishedDate(newPublishedDate);
            book.setGenre(newGenre);
            book.setIsbn(newIsbn);
            book.setPages(newPages);
            book.setStatus(newStatus);
            book.setCopies(newCopies);
            book.setFloor(newFloor);
            book.setShelf(newShelf);
            return true;
        }
        return false;
    }
    //UPDATE by TITLE
    public boolean updateBookByTitle(int id, String title, String newTitle, String newAuthor, LocalDateTime newPublishedDate, String newGenre, String newIsbn,
            String newEdition, String newPages, boolean newStatus, int newCopies, String newFloor, String newShelf) {
        Book book = getBookByTitle(title);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPublishedDate(newPublishedDate);
            book.setGenre(newGenre);
            book.setIsbn(newIsbn);
            book.setPages(newPages);
            book.setStatus(newStatus);
            book.setCopies(newCopies);
            book.setFloor(newFloor);
            book.setShelf(newShelf);
            return true;
        }
        return false;
    }
    
    //DELETE by ID
    public boolean deleteBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            return bookList.remove(book);
        }
        return false;
    }
    //DELETE by TITLE
    public boolean deleteBook(String title) {
        Book book = getBookByTitle(title);
        if (book != null) {
            return bookList.remove(book);
        }
        return false;
    }
    
    
}
