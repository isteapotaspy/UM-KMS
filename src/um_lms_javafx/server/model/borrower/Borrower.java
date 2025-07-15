/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.borrower;

import java.time.LocalDateTime;
import um_lms_javafx.server.model.user.Student;
import um_lms_javafx.server.model.book.Book;

public class Borrower {
    private int borrowerID;
    private boolean returnStatus;
    
    private Student student;
    private Book book;
    private LocalDateTime dateIssued;
    // NO NEED TO CACHE THIS BITCHHHHHHH CPU GO BRR BRR
    // OR PROBABLY REMOVE THIS LATER
    private LocalDateTime returnDeadline;
    
    public Borrower(int borrowerID, Student student, Book book, LocalDateTime dateIssued) {
        this.returnStatus = false;
        
        this.borrowerID = borrowerID;
        this.student = student;
        this.book = book;        
        this.dateIssued = dateIssued;
    }
    
    public void setStudentBorrower(Student student) { this.student = student; }
    public void setBookBorrowed(Book book) { this.book = book; }
    public void setBorrowerStatus(boolean status) { this.returnStatus = status; }
    
    public boolean getBorrowerStatus() { return returnStatus; }
    public int getBorrowerID() { return borrowerID; }
    public int getStudentID() { return student.getStudentID(); }
    public String getStudentName() { return student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName(); }
    public String getBookTitle() { return book.getTitle(); }
    
    public LocalDateTime getDateIssued() {
        return dateIssued;
    }
    
    // handle holidays in the future
    public LocalDateTime getReturnDeadline() {
        return dateIssued.plusDays(3).withHour(20).withMinute(59);
    }
}
