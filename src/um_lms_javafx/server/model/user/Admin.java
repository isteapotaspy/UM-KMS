/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

/**
 *
 * @author Ravin
 */
final public class Admin extends User{
    
    public Admin(String firstName, String middleName, String lastName, String email, String phoneNumber) {
        super(firstName, middleName, lastName, email, phoneNumber);
        this.adminAccess = true;    
    }
}
