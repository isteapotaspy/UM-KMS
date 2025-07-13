/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package um_lms_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class UM_LMS_JavaFX extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/LoginLayout.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResource("/um_lms_javafx/assets/um_logo_window.png").toString()));
        stage.setTitle("University of Mindanao Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    // NOTE TO SELF: Do NOT declare a random ass event without a handler because it will BUG the shit out
    public static void main(String[] args) {
        launch(args);
    }
    
}
