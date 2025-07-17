/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package um_lms_javafx.ui.user.student;

import um_lms_javafx.ui.utils.LogicUI.PaneSwitch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class StudentLayoutController implements Initializable {
    // check out the code in PaneSwitch.java in students folder
    // THIS IS ONCE INITIALIZED and handles ALL OF THE PANE SWITCHING ON THIS ENTIRE FRAME
    PaneSwitch switcher = new PaneSwitch();
    
    @FXML
    Pane dynamicPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // you have to put the PATH of the frame to bind and load it into the project
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/student/dashboard/dashboard.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }    
    
    public void handleDashboardButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/student/dashboard/dashboard.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    
    // THE THINGS BELOW SHOULD HANDLE THE UI CHANGE (like design and pane switching)
    public void handleSearchButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/shared_panes/search/search.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    
    public void handleRequestButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/student/request/request.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    
    public void handleHistoryButtonClick() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/student/history/history.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleMessagesButtonClick() {
    }
    
    public void handleProfileButtonClicked() {
        Pane newPage = switcher.getPage("/um_lms_javafx/ui/user/shared_panes/profile/profile.fxml");
        dynamicPane.getChildren().setAll(newPage);
    }
    public void handleSettingsButtonClicked() {}
    public void handleLogoutButtonClicked() {}
    
}
