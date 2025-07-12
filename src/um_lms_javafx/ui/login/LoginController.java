/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    Button loginButton;
    @FXML
    Button adminButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void loginButtonActionPerformed(ActionEvent e) throws IOException {
        // NOTE: This is the code on how to change the stage (similar to JFrame) to another one
        // Use relative pathing to make sure you grab the correct .fxml
        // Just copy paste this code, there's no need to modify anything here except the path

        Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/user/student/StudentLayout.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void adminButtonActionPerformed(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/user/admin/AdminLayout.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
