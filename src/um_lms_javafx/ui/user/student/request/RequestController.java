/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.student.request;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.model.borrower.BorrowHistory;
import um_lms_javafx.server.model.user.StudentSession;

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

    @FXML
    private TableView<BorrowHistory> currentBooksIssued;
    @FXML
    private TableColumn<BorrowHistory, Integer> borrowIDColumn;
    @FXML
    private TableColumn<BorrowHistory, String> statusColumn;
    @FXML
    private TableColumn<BorrowHistory, Integer> bookIDColumn;
    @FXML
    private TableColumn<BorrowHistory, LocalDateTime> issuedDateColumn;
    @FXML
    private TableColumn<BorrowHistory, LocalDate> expiryDateColumn;

    DBBookDAO bookDao = new DBBookDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        borrowIDColumn.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        issuedDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateIssued"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        try {
            int studentId = StudentSession.getStudentId();
            ObservableList<BorrowHistory> historyList = bookDao.getCurrentIssuedBooks(studentId);
            currentBooksIssued.setItems(historyList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleReturnBookClick() {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
