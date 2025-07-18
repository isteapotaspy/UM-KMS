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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    
    @FXML Label idText;
    @FXML Label firstNameText;
    @FXML Label middleInitialText;
    @FXML Label lastNameText;
    @FXML Label emailText;
    @FXML Label phoneNumberText;
    @FXML Label passwordText;
    @FXML Label confirmPasswordText;
    
    @FXML ToggleButton peekPassword;
    @FXML ToggleButton peekConfirmPassword;
        
    @FXML TextField passwordVisibleField;
    @FXML TextField confirmPasswordVisibleField;
    
    @FXML Button signupButton;
    
    DBStudentDAO studentDAO = new DBStudentDAO();

    private String password;
    private String confirmPassword;
    private String passwordRegexValidation = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;\"'<>,.?/~`|\\\\-]).{8,}$";
    private String emailRegexValidation = "^[a-zA-Z0-9._%+-]+@umindanao\\.edu\\.ph$";
    private String phoneRegexValidation = "^\\d{11}$";
    private String studentIDRegexValidation = "^\\d{6}$";
    
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    
    Student student;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passwordVisibleField.setVisible(false);
        passwordVisibleField.setManaged(false);
        confirmPasswordVisibleField.setVisible(false);
        confirmPasswordVisibleField.setManaged(false);
        
        passwordVisibleField.textProperty().bindBidirectional(passwordField.textProperty());

        // Toggle visibility
        peekPassword.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            passwordVisibleField.setVisible(isSelected);
            passwordVisibleField.setManaged(isSelected);

            passwordField.setVisible(!isSelected);
            passwordField.setManaged(!isSelected);
        });
        
        peekConfirmPassword.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            confirmPasswordVisibleField.setVisible(isSelected);
            confirmPasswordVisibleField.setManaged(isSelected);

            confirmPasswordField.setVisible(!isSelected);
            confirmPasswordField.setManaged(!isSelected);
        });
    }    
    
    @FXML
    public void handleSignup() {
        authenticate();
    }
    
    public void authenticate() {
        try {
        password = passwordField.getText();
        confirmPassword = confirmPasswordField.getText();
        
        if (studentIDField.getText().isBlank()) {
            clearErrorsState();
            idText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Student ID is required for registration.");
        } else if (studentDAO.findStudentByID(Integer.parseInt(studentIDField.getText())) != null) {
            clearErrorsState();
            idText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "You're already registered in the system.");
        } else if (firstNameField.getText().isBlank() || middleNameField.getText().isBlank() ||             
            lastNameField.getText().isBlank() || phoneNumberField.getText().isBlank() ||
            emailField.getText().isBlank()) {
            clearErrorsState();
            firstNameText.getStyleClass().add("errorTextState");
            middleInitialText.getStyleClass().add("errorTextState");
            emailText.getStyleClass().add("errorTextState");
            lastNameText.getStyleClass().add("errorTextState");
            phoneNumberText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter all required details.");
        } else if (!emailField.getText().matches(emailRegexValidation)) {
            clearErrorsState();
            emailText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid institutional email ending in '@umindanao.edu.ph'.");
        } else if (!phoneNumberField.getText().matches(phoneRegexValidation)) {
            clearErrorsState();
            phoneNumberText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid 11-digit Philipine phone number. ");
        } else if (password == null || confirmPassword == null || password.isEmpty() || confirmPassword.isEmpty()) {
            clearErrorsState();
            passwordText.getStyleClass().add("errorTextState");
            confirmPasswordText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in both password fields.");
        } else if (!password.matches(passwordRegexValidation)) {
            clearErrorsState();
            passwordText.getStyleClass().add("errorTextState");
            confirmPasswordText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Password must be at least 8 characters long and include an uppercase letter, a number, and a special character.");
        } else if (!password.equals(confirmPassword)) {
            clearErrorsState();
            passwordText.getStyleClass().add("errorTextState");
            confirmPasswordText.getStyleClass().add("errorTextState");
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
        } else {
            clearErrorsState();
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
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to create account.");
            }
        }
    } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "ERROR: Cannot register user."); 
            System.out.println("SIGN UP ERROR: Cannot register user. [SignupController.java]");
        }
    }
    
    @FXML 
    public void handlePeekPassword() {
        if (isPasswordVisible) {
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
        } else {
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } isPasswordVisible = !isPasswordVisible;
    }
    
    @FXML 
    public void handlePeekConfirmPassword() {
        if (isConfirmPasswordVisible) {
            confirmPasswordField.setText(confirmPasswordVisibleField.getText());
            confirmPasswordField.setVisible(true);
            confirmPasswordField.setManaged(true);
            confirmPasswordVisibleField.setVisible(false);
            confirmPasswordVisibleField.setManaged(false);
        } else {
            confirmPasswordVisibleField.setText(passwordField.getText());
            confirmPasswordVisibleField.setVisible(true);
            confirmPasswordVisibleField.setManaged(true);
            confirmPasswordField.setVisible(false);
            confirmPasswordField.setManaged(false);
        } isConfirmPasswordVisible = !isConfirmPasswordVisible;
    }

    
    
    //POP UP SHOWING IF SUCCESSFULLLLLL OR NOT
    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void clearErrorsState() {
        idText.getStyleClass().remove("errorTextState");
        firstNameText.getStyleClass().remove("errorTextState");
        middleInitialText.getStyleClass().remove("errorTextState");
        lastNameText.getStyleClass().remove("errorTextState");
        emailText.getStyleClass().remove("errorTextState");
        phoneNumberText.getStyleClass().remove("errorTextState");
        passwordText.getStyleClass().remove("errorTextState");
        confirmPasswordText.getStyleClass().remove("errorTextState");
    }
    
    
}
