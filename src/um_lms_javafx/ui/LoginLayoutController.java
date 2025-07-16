/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui;

import um_lms_javafx.ui.utils.LogicUI.PaneSwitch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class LoginLayoutController implements Initializable {    
    @FXML
    Pane authenticationPane;
    
    @FXML 
    MediaView loginBackgroundVideo;
    private MediaPlayer mediaPlayer;
    
    // check out the code in PaneSwitch.java in students folder
    // THIS IS ONCE INITIALIZED and handles ALL OF THE PANE SWITCHING ON THIS ENTIRE FRAME
    PaneSwitch authenticationSwitcher = new PaneSwitch();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // FOR VIDEO
        URL resource = getClass().getResource("/um_lms_javafx/assets/library_backgroundvideo.mp4");
        if (resource != null) {
            Media backgroundVideo = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(backgroundVideo);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            loginBackgroundVideo.setMediaPlayer(mediaPlayer);
        } else {
            System.out.println("Video file not found!");
        }
        
        Pane loginPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/login/login.fxml");
        authenticationPane.getChildren().setAll(loginPage);
    }    
    
    @FXML
    public void handleSwitchToSignup() {
        Pane signupPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/signup/signup.fxml");
        authenticationPane.getChildren().setAll(signupPage);
    }
    
    @FXML
    public void handleSwitchToLogin() {
        Pane loginPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/login/login.fxml");
        authenticationPane.getChildren().setAll(loginPage);
    }
}
