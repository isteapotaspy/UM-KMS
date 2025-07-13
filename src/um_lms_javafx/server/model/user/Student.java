/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

/**
 *
 * @author Ravin
 */
final public class Student extends User {
    private int studentID;
    private String department;
    private String program;
    
    public Student(String firstName, String middleName, String lastName, String email, String phoneNumber) {
        super(firstName, middleName, lastName, email, phoneNumber);
        this.adminAccess = false;
    }
    
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public void setDepartment(String department) { this.department = department; }
    public void setProgram(String program) { this.program = program; }
    
    public int getStudentID() { return studentID; }
    public String getDepartment() { return department; }
    public String getProgram() { return program; }
}
