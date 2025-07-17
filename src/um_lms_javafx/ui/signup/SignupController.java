/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.signup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import um_lms_javafx.server.DAO.DBStudentDAO;
import um_lms_javafx.server.model.user.Student;



public class SignupController implements Initializable {
    @FXML TextField studentIDField;
    @FXML TextField firstNameField;
    @FXML TextField middleNameField;
    @FXML TextField lastNameField;
    @FXML TextField emailField;
    @FXML TextField phoneNumberField;
    @FXML TextField passwordField;
    @FXML TextField confirmPasswordField;
    @FXML Button signupButton;
    
    DBStudentDAO studentDAO = new DBStudentDAO();

    private String password;
    private String confirmPassword;
    private String passwordRegexValidation = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;\"'<>,.?/~`|\\\\-]).{8,}$";
    private String emailRegexValidation = "^[a-zA-Z0-9._%+-]+@umindanao\\.edu\\.ph$";
    private String phoneRegexValidation = "^\\d{12}$";
    private String studentIDRegexValidation = "^\\d{6}$";

    
    Student student;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void handleSignup() {
    try {
        password = passwordField.getText();
        confirmPassword = confirmPasswordField.getText();
        
        if (studentIDField == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Student ID is required for registration.");
        } // then search for all studentID if it matches to make sure that there's no collision at all
        
        if (firstNameField == null|| middleNameField == null || 
            lastNameField == null || phoneNumberField == null ||
            emailField == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
        } else if (!emailField.getText().matches(emailRegexValidation)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid institutional email ending in '@umindanao.edu.ph'.");
        } else if (!phoneNumberField.getText().matches(phoneRegexValidation)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid 12-digit Philipine phone number. ");
        } else if (password == null || confirmPassword == null || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in both password fields.");
        } else if (!password.matches(passwordRegexValidation)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password must be at least 8 characters long and include an uppercase letter, a number, and a special character.");
        } else if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
        } else {
            student = new Student(
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                phoneNumberField.getText(),
                password
            );
            
            student.setStudentID(Integer.parseInt(studentIDField.getText()));
            
            System.out.println("Signup success!");
            System.out.println(student.getFirstName());
            System.out.println(student.getPassword());
            
            
            boolean success = studentDAO.insertStudent(student);
            
            if (success) {
                //clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add the book.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "ERROR: Cannot register user."); 
        System.out.println("SIGN UP ERROR: Cannot register user. [SignupController.java]");
    }
}

    
    //POP UP SHOWING IF SUCCESSFULLLLLL OR NOT
    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
