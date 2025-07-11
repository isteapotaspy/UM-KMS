/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.student.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class DashboardController implements Initializable {

    @FXML
    private Label announcementText;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        announcementText.setText("[1] Break rooms in the library is currently not  available due to maintenance.\n[2] Further, borrowing is temporarily on hold due to update in our library management system.");
        announcementText.setWrapText(true);
    }    
    
}
