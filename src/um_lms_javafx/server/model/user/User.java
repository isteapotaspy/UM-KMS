/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

/**
 *
 * @author Ravin
 * @param <T>
 */



public abstract class User {
    private String firstName;
    private String middleName;
    private String lastName;
    
    private byte[] profilePicture;
    
    private String email;
    private String phoneNumber;
    
    private String password;
    protected boolean adminAccess;
    
    public User(String firstName, String middleName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        
        this.email = email;
        this.phoneNumber = phoneNumber;
        
        this.adminAccess = false;
    }
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setPassword(String password) {  } // ALWAYS HASH THIS SHIT SO THAT IT ISN'T EXPOSED
    public void setProfilePicture(byte[] profilePicture) { this.profilePicture = profilePicture; }
   
    public String getFirstName() {return firstName; }
    public String getMiddleName() {return middleName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public byte[] getProfilePicture() { return profilePicture; } 
    public boolean getAdminAccess() { return adminAccess; }
}
