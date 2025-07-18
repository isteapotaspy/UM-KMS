/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.borrower;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author jeanv
 */
public class BorrowHistory {
    private int borrowId;
    private int studentId;
    private int bookId;
    private LocalDateTime dateIssued;
    private LocalDateTime dateReturned;
    private String status;
    private LocalDate expiryDate;
    
    public BorrowHistory(){}
    
    public BorrowHistory(int borrowId, String status, int bookId, LocalDateTime dateIssued, LocalDate expiryDate) {
        this.borrowId = borrowId;
        this.status = status;
        this.bookId = bookId;
        this.dateIssued = dateIssued;
        this.expiryDate = expiryDate;
    }
    
    public BorrowHistory(int borrowId, String status, int studentId, int bookId,
                         LocalDateTime dateIssued, LocalDate expiryDate, LocalDateTime dateReturned) {
        this.borrowId = borrowId;
        this.status = status;
        this.studentId = studentId;
        this.bookId = bookId;
        this.dateIssued = dateIssued;
        this.expiryDate = expiryDate;
        this.dateReturned = dateReturned;
    }
    
    //GETTERS
    public int getBorrowId() { return borrowId; }
    public int getStudentId() { return studentId; }
    public int getBookId() { return bookId; }
    public LocalDateTime getDateIssued() { return dateIssued; }
    public LocalDateTime getDateReturned() { return dateReturned; }
    public String getStatus() { return status; }
    public LocalDate getExpiryDate() { return expiryDate; }
    
    //SETTERS
    public void setBorrowId(int id) { this.borrowId = id; }
    public void setStudentId(int id) { this.studentId = id; }
    public void setBookId(int id) { this.bookId = id; }
    public void setDateIssued(LocalDateTime date) { this.dateIssued = date; }
    public void setDateReturned(LocalDateTime date) { this.dateReturned = date; }
    public void setStatus(String status) { this.status = status; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
}
