/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.student.request;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class RequestController implements Initializable {
  
    
    @FXML
    Hyperlink borrowBook;
    @FXML
    Hyperlink returnBook;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void handleBorrowBookClick(){
        
        // THIS IS THE CODE TO CREATE A NEW PANE
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/borrowbook/borrowbook.fxml"));
            // ignore this error, it will work regardless
            Pane dialogRoot = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.showAndWait(); // FORCE THE PARENT TO WAIT FOR MORE INPUT
        } catch(IOException e) { e.printStackTrace(); }
    }
    
    public void handleReturnBookClick(){
        
        // THIS IS THE CODE TO CREATE A NEW PANE
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/borrowbook/borrowbook.fxml"));
            // ignore this error, it will work regardless
            Pane dialogRoot = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.showAndWait(); // FORCE THE PARENT TO WAIT FOR MORE INPUT
        } catch(IOException e) { e.printStackTrace(); }
    }
   
    
}
