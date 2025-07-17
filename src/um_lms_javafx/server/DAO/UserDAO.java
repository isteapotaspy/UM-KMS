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
    private T user;
    private boolean signedIn = false;
    
    // USERLIST SHOULD REFER TO THE ENTIRE DATABASE LATER ON
    private List<T> userList = new ArrayList<>();
    private UserFactory<T> factory;
    
    // CREATE NEW USER
    public void addUser(String firstName, String middleName, String lastName) {
        T user = factory.create(firstName, middleName, lastName);
        userList.add(user);
    }
    
    // AUTHENTICATE CURRENT USER
    public boolean authenticateUser(String email, String password) {
        for(T user : userList) {
            if ((user.getEmail().equals(email)) && (user.getPassword().equals(password))) {
                signedIn = true;
                return true;
            }}  return false;
    }
    // DEAUTHENTICATE CURRENT USER
    public void deauthenticateUser() {
        signedIn = false;
    }
    // GRAB ADMIN STATUS IF IT EXISTS FROM CURRENT USER
    public boolean getAdminStatus(T user) {
        return user.getAdminAccess();
    }
    
    // READ ALL USERS
    public List<T> getAllUsers() {
        return (List<T>) userList;
    }
    
    // READ (OR RATHER, SEARCH) SPECIFIC USERS
    public T getUserByFirstName(String firstName) {
        for(T user : userList) {
            if(user.getFirstName().equals(firstName)){ return user; }
        } return null;        
    }
    public T getUserByLastName(String lastName) {
        for(T user : userList) {
            if(user.getLastName().equals(lastName)){ return user; }
        } return null;       
    }   
    public T getUserByID(int studentID) {
        for(T user : userList) {
            //if(user.getStudentID() == studentID){ return user; }
        } return null;       
    }
    
    // DELETE USER
    public void deleteUser(int studentID) {
        for(T user : userList) {
            // CORRECT THIS COMMAND LATER
            //if(user.getStudentID() == studentID){ userList.remove(user); }
        } 
    }
}

class AuthenticationError extends Exception {
    public AuthenticationError(String error) {
        System.out.println("[EXCEPTION - General Authentication Error] User has incorrect email or password credentials upon Signup");
    }
    
    public void deauthenticationError() {}
    public void incorrectCredentialsError() {}
    public void undeletableCredentialsError() {}
}

class UserCRUD extends Exception {}