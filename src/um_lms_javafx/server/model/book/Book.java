/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.book;

/**
 *
 * @author jeanv
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String publishedDate;
    private String genre; //LIST TO BE MADE, THIS IS JUST AN EXAMPLE
    private String isbn;
    private String edition;
    private String pages;
    private String description;

    public boolean status; //isAvailable or not
    private String copies;
    private String floor;
    private String shelf;
    private byte[] bookCover;

    public Book() {}

    public Book(String title, String author, String publishedDate, String genre, String isbn,
            String edition, String pages, boolean status, String copies, String floor, String shelf) {
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

    public Book(int id, String title, String author, String publishedDate, String genre, String isbn, String edition, String pages, 
            boolean status, String copies, String floor, String shelf) {
        this(title, author, publishedDate, genre, isbn, edition, pages, status, copies, floor, shelf);
        this.id = id;
    }
    
    //GETTERS
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublishedDate() { return publishedDate; }
    public String getGenre() { return genre; }
    public String getIsbn() { return isbn; }
    public String getEdition() { return edition; }
    public String getPages() { return pages; }
    public String getDescription() { return description; }
    public boolean getStatus() { return status; }
    public String getCopies() { return copies; }
    public String getFloor() { return floor; }
    public String getShelf() { return shelf; }
    public byte[] getBookCover() { return bookCover; }
    
    //SETTERS
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setEdition(String edition) { this.edition = edition; }
    public void setPages(String pages) { this.pages = pages; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(boolean status) { this.status = status; }
    public void setCopies(String copies) { this.copies = copies; }
    public void setFloor(String floor) { this.floor = floor; }
    public void setShelf(String shelf) { this.shelf = shelf; }
    public void setBookCover(byte[] bookCover) { this.bookCover = bookCover; }
    
}
