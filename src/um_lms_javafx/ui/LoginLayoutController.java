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
import javafx.scene.control.Hyperlink;
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
    Hyperlink switchToSignup;
    
    @FXML
    Pane authenticationPane;
    
    @FXML 
    MediaView loginBackgroundVideo;
    private MediaPlayer mediaPlayer;
    
    // check out the code in PaneSwitch.java in students folder
    // THIS IS ONCE INITIALIZED and handles ALL OF THE PANE SWITCHING ON THIS ENTIRE FRAME
    PaneSwitch authenticationSwitcher = new PaneSwitch();
    private boolean paneOnLogin = true;
        
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
        
        switchToSignup.getStyleClass().add("linkFromLogin");
        Pane loginPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/login/login.fxml");
        authenticationPane.getChildren().setAll(loginPage);
    }    
    
    @FXML
    public void handleLoginSwitch() {
        if(paneOnLogin == false){
            
            Pane signupPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/signup/signup.fxml");
            authenticationPane.getChildren().setAll(signupPage);
            
            switchToSignup.getStyleClass().remove("linkFromLogin");
            switchToSignup.getStyleClass().add("linkFromSignup");
            switchToSignup.setText("Already have an account? Login now.");
            paneOnLogin = true;
        } else if (paneOnLogin == true) {
            Pane loginPage = authenticationSwitcher.getPage("/um_lms_javafx/ui/login/login.fxml");
            authenticationPane.getChildren().setAll(loginPage);
            switchToSignup.setText("Don't have an account? Sign up now.");
            
            switchToSignup.getStyleClass().remove("linkFromSignup");
            switchToSignup.getStyleClass().add("linkFromLogin");
            paneOnLogin = false;
        }
    }
}
