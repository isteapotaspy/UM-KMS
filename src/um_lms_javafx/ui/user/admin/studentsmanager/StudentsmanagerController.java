/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.studentsmanager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import um_lms_javafx.server.DAO.DBStudentDAO;
import um_lms_javafx.server.model.user.Student;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class StudentsmanagerController implements Initializable {

    @FXML private TableView<Student> manageStudentsTableView;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, LocalDateTime> dateJoinedColumn;
    @FXML private TableColumn<Student, Integer> totalBooksIssuedColumn;
    @FXML private TableColumn<Student, Integer> totalFinesColumn;
    
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private DBStudentDAO studentDao = new DBStudentDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
       emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
       dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined")); 
       totalBooksIssuedColumn.setCellValueFactory(new PropertyValueFactory<>("booksIssued"));
       totalFinesColumn.setCellValueFactory(new PropertyValueFactory<>("totalFines"));
       
       manageStudentsTableView.setItems(studentDao.loadStudents());
       
        
       
       
       //IF DOUBLE CLICKED
        manageStudentsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !manageStudentsTableView.getSelectionModel().isEmpty()) {
                Student selectedStudent = manageStudentsTableView.getSelectionModel().getSelectedItem();
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
}
