/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.login;
import um_lms_javafx.ui.LoginLayoutController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import um_lms_javafx.server.DAO.AuthenticationState;
import um_lms_javafx.server.DAO.DBStudentDAO;
import um_lms_javafx.server.model.user.StudentSession;

public class LoginController implements Initializable {
    
    @FXML TextField emailTextField;
    @FXML PasswordField passwordTextField;
    @FXML Label errorText;
    
    @FXML Button loginButton;
    @FXML Button adminButton;    
    @FXML Hyperlink switchToSignup;
    
    DBStudentDAO authenticator = new DBStudentDAO();
    AuthenticationState authState;
    String email, password;
    
    StudentSession session = new StudentSession();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void loginButtonActionPerformed(ActionEvent e) throws IOException, SQLException {       
        email = emailTextField.getText();
        password = passwordTextField.getText();
        
        if(email.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email field is empty.");
        } else if (password.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password field is empty.");
        } else {
            authState = authenticator.authenticateUser(email, password);
            switch(authState) {
                case(AuthenticationState.NO_USER_EXISTS) -> {
                    showAlert(Alert.AlertType.ERROR, "Error", "No such user exists in the database.");
                    break;
                }
                case(AuthenticationState.INCORRECT_PASSWORD) -> {
                    showAlert(Alert.AlertType.ERROR, "Error", "The entered password is incorrect.");
                    break;
                }
                case(AuthenticationState.STUDENT_ACCESS_GRANTED) -> {
                    int studentId = authenticator.getStudentIdByEmail(email); 
                    String fullName = authenticator.getFullNameByEmail(email); 

                    StudentSession.setStudent(studentId, fullName);

                    Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/user/student/StudentLayout.fxml"));
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                }
                case(AuthenticationState.ADMIN_ACCESS_GRANTED) -> {
                    int id = authenticator.getStudentIdByEmail(email); 
                    String fullName = authenticator.getFullNameByEmail(email); 

                    StudentSession.setStudent(id, fullName);
                    Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/user/admin/AdminLayout.fxml"));
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                }
            }
        }  
    }
    
    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
