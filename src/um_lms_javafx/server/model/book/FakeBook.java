/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.book;

/**
 *
 * @author jeanv
 */

public class FakeBook {
    private String id;
    private String title;
    private String author;
    private String publishedDate;
    private String genre;

    public FakeBook(String id, String title, String author, String publishedDate, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.genre = genre;
    }
    
    public String getId() { return id; }
    public void setId(String value) { this.id = value; }
    
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getAuthor() { return author; }
    public void setAuthor(String value) { this.author = value; }
    
    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String value) { this.publishedDate = value; }
    
    public String getGenre() { return genre; }
    public void setGenre(String value) { this.genre = value; }
}

