/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import um_lms_javafx.server.model.user.User;
import java.util.ArrayList;
import java.util.List;


interface UserFactory<T extends User> {
    T create(String firstName, String middleName, String lastName);
}

public class UserDAO<T extends User> {    
    // USERLIST SHOULD REFER TO THE ENTIRE DATABASE LATER ON
    private List<T> userList = new ArrayList<>();
    private UserFactory<T> factory;
    
    // CREATE NEW USER
    public void addUser(String firstName, String middleName, String lastName) {
        T user = factory.create(firstName, middleName, lastName);
        userList.add(user);
    }
    
    // AUTHENTICATE A USER
    
    
    // READ ALL USERS
    public List<T> getAllUsers() {
        return (List<T>) userList;
    }
    
    // READ (OR RATHER, SEARCH) SPECIFIC USERS
    public void getUserByFirstName() {
        
    }
    
    public void getUserByLastName() {
        
    }
    
    public void getUserByName() {
        
    }
}

class AuthenticationError extends Exception {}
