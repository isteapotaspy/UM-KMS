/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.book;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jeanv
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String genre; //LIST TO BE MADE, THIS IS JUST AN EXAMPLE
    private String isbn;
    private int edition;
    private int pages;
    private String description;

    public boolean status; //isAvailable or not
    private int copies;
    private String floor;
    private String shelf;
    private byte[] bookCover;

    public Book() {}
    
    public Book(String title, int id, int copies) {
        this.title = title;
        this.id = id;
        this.copies = copies;
    }

    public Book(String title, String author, LocalDate publishedDate, String genre, String isbn, int edition, int pages, String description, boolean status, 
            int copies, String floor, String shelf) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.genre = genre;
        this.isbn = isbn;
        this.edition = edition;
        this.pages = pages;
        this.status = status;
        this.copies = copies;
        this.floor = floor;
        this.shelf = shelf;
    }

    public Book(int id, String title, String author, LocalDate publishedDate, String genre, String isbn, int edition, int pages, String description, boolean status, 
            int copies, String floor, String shelf) {
        this(title, author, publishedDate, genre, isbn, edition, pages, description, status, copies, floor, shelf);
        this.id = id;
    }
    
    public Book(byte[] bookCover, int id, String title, String author, LocalDate publishedDate, String genre, String isbn, int edition, int pages, String description, boolean status, 
            int copies, String floor, String shelf) {
        this(id, title, author, publishedDate, genre, isbn, edition, pages, description, status, copies, floor, shelf);
        this.bookCover = bookCover;
    }
    
    //GETTERS
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public LocalDate getPublishedDate() { return publishedDate; }
    public String getGenre() { return genre; }
    public String getIsbn() { return isbn; }
    public int getEdition() { return edition; }
    public int getPages() { return pages; }
    public String getDescription() { return description; }
    public boolean getStatus() { return status; }
    public int getCopies() { return copies; }
    public String getFloor() { return floor; }
    public String getShelf() { return shelf; }
    public byte[] getBookCover() { return bookCover; }
    
    //SETTERS
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setEdition(int edition) { this.edition = edition; }
    public void setPages(int pages) { this.pages = pages; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(boolean status) { this.status = status; }
    public void setCopies(int copies) { this.copies = copies; }
    public void setFloor(String floor) { this.floor = floor; }
    public void setShelf(String shelf) { this.shelf = shelf; }
    public void setBookCover(byte[] bookCover) { this.bookCover = bookCover; }
    
}
