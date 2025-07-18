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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import um_lms_javafx.server.DAO.DBConnection;



public class UM_LMS_JavaFX extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        
        Font.loadFont(getClass().getResourceAsStream("/um_lms_javafx/assets/fonts/Nunito/static/Nunito-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/um_lms_javafx/assets/fonts/Nunito/static/Nunito-Bold.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/um_lms_javafx/assets/fonts/Montserrat/static/Montserrat-Regular.ttf"), 12);
        
        Parent root = FXMLLoader.load(getClass().getResource("/um_lms_javafx/ui/LoginLayout.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResource("/um_lms_javafx/assets/sigmawolf_window.png").toString()));
        stage.setTitle("SigmaWolf Library Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    // NOTE TO SELF: Do NOT declare a random ass event without a handler because it will BUG the shit out
    public static void main(String[] args) {
        DBConnection.testConnection();
        launch(args);
    }
    
}
