/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.studentsmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import um_lms_javafx.server.model.user.FakeStudent;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class StudentsmanagerController implements Initializable {

    @FXML private TableView<FakeStudent> manageStudentsTableView;
    @FXML private TableColumn<FakeStudent, Integer> idColumn;
    @FXML private TableColumn<FakeStudent, String> nameColumn;
    @FXML private TableColumn<FakeStudent, String> emailColumn;
    @FXML private TableColumn<FakeStudent, String> dateJoinedColumn;
    @FXML private TableColumn<FakeStudent, Integer> totalBooksIssuedColumn;
    @FXML private TableColumn<FakeStudent, Integer> totalFinesColumn;
    
    private ObservableList<FakeStudent> studentList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
       dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined")); 
       totalBooksIssuedColumn.setCellValueFactory(new PropertyValueFactory<>("totalBooksIssued"));
       totalFinesColumn.setCellValueFactory(new PropertyValueFactory<>("totalFines"));
       
       manageStudentsTableView.setItems(studentList);
       
       addStudent(1, "Harry POtter", "harry@umidnanao.edu.ph", "March 13, 2025", 26, 0);
       addStudent(2, "Harry POtter", "harry@umidnanao.edu.ph", "March 13, 2025", 26, 0);
       addStudent(3, "Harry POtter", "harry@umidnanao.edu.ph", "March 13, 2025", 26, 0);
       addStudent(4, "Harry POtter", "harry@umidnanao.edu.ph", "March 13, 2025", 26, 0);
       
       
       //IF DOUBLE CLICKED
        manageStudentsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !manageStudentsTableView.getSelectionModel().isEmpty()) {
                FakeStudent selectedStudent = manageStudentsTableView.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/selectedstudent/selectedstudent.fxml"));
                        Pane dialogRoot = loader.load();

                        Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.APPLICATION_MODAL);
                        dialogStage.setResizable(false);
                        dialogStage.setScene(new Scene(dialogRoot));
                        dialogStage.showAndWait(); // FORCE THE PARENT TO WAIT FOR MORE INPUT
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
       
    }    
    
    public void addStudent(int studentID, String name, String email, String dateJoined, int totalBooksIssued, int totalFines) {
        studentList.add(new FakeStudent(studentID, name, email, dateJoined, totalBooksIssued, totalFines));
    }
}
