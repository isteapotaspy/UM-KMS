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
    public Admin(String firstName, String middleName, String lastName, String email, String phoneNumber, String password) {
        super(firstName, middleName, lastName, email, phoneNumber, password);
        this.adminAccess = true;    
    }
}
