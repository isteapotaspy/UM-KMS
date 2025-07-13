/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import um_lms_javafx.ui.utils.LogicUI.PaneSwitch;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class AdminLayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PaneSwitch switcher = new PaneSwitch();
    
    @FXML
    Pane dynamicPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/admin/dashboard/dashboard.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleDashboardButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/admin/dashboard/dashboard.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleTotalBooksButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/shared_panes/search/search.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleManageBooksButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/admin/managebooks/manageBooks.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleIssuedBooksButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/admin/issuedbooks/issuedBooks.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleStudentsButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/admin/studentsmanager/studentsmanager.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    
}
