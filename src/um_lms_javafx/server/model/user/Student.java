/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

import java.time.LocalDateTime;

/**
 *
 * @author Ravin
 */
final public class Student extends User {
    private int studentID;
    private String department;
    private String program;
    private int booksIssued;
    
    public Student(String firstName, String middleName, String lastName, int studentID, int booksIssued) {
        super(firstName, middleName, lastName);
        this.studentID = studentID;
        this.booksIssued = booksIssued;
    }
    public Student (String fullName, int studentID, int booksIssued) {
        super(fullName);
        this.studentID = studentID;
        this.booksIssued = booksIssued;
        
    }
    public Student(int studentID, String fullName, String email, LocalDateTime dateJoined, int booksIssued) {
        super(fullName, email, dateJoined);
        this.studentID = studentID;
        this.booksIssued = booksIssued;
    }
    
    
 
    public Student(String firstName, String middleName, String lastName, String email, String phoneNumber, String password) {
        super(firstName, middleName, lastName, email, phoneNumber, password);
        this.adminAccess = false;
    }
    
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public void setDepartment(String department) { this.department = department; }
    public void setProgram(String program) { this.program = program; }
    public void setAdminAccess(boolean adminAccess) { this.adminAccess = adminAccess; }
    
    public int getStudentID() { return studentID; }
    public boolean getAdminAccess() { return adminAccess; }
    public String getDepartment() { return department; }
    public String getProgram() { return program; }
    public int getBooksIssued() { return booksIssued; }
    public void setBooksIssued(int booksIssued) { this.booksIssued = booksIssued; }

}
