/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

/**
 *
 * @author jeanv
 */
public class FakeStudent {
    private int studentID;
    private String name;
    private String email;
    private String dateJoined;
    private int totalBooksIssued;
    private int totalFines;
    
    public FakeStudent(){}
    
    public FakeStudent(int studentID, String name, String email, String dateJoined, int totalBooksJoined, int totalFines) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.dateJoined = dateJoined;
        this.totalBooksIssued = totalBooksIssued;
        this.totalFines = totalFines;
    }
    
    public int getID() { return studentID; }
    public void setID(int value) { this.studentID = value; }
    public String getName() { return name; }
    public void setName(String value) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
    public String getDate() { return dateJoined; }
    public void setDate(String value) { this.dateJoined = value; }
    public int getTotalBooks() { return totalBooksIssued; }
    public void setTotalBooks(int value) { this.totalBooksIssued = value; }
    public int getTotalFines() { return totalFines; }
    public void setTotalFines(int value) { this.totalFines = totalFines; }
}
