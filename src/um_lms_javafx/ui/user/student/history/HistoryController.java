/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.student.history;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.model.borrower.BorrowHistory;
import um_lms_javafx.server.model.user.StudentSession;

public class HistoryController implements Initializable {

    @FXML
    private TableView<BorrowHistory> historyTableView;
    @FXML
    private TableColumn<BorrowHistory, Integer> borrowIDColumn;
    @FXML
    private TableColumn<BorrowHistory, String> statusColumn;
    @FXML
    private TableColumn<BorrowHistory, Integer> studentIDColumn;
    @FXML
    private TableColumn<BorrowHistory, Integer> bookIDColumn;
    @FXML
    private TableColumn<BorrowHistory, LocalDateTime> issuedDateColumn;
    @FXML
    private TableColumn<BorrowHistory, LocalDate> expiryDateColumn;
    @FXML
    private TableColumn<BorrowHistory, LocalDateTime> returnedDateColumn;
    
    DBBookDAO bookDao = new DBBookDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        borrowIDColumn.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        issuedDateColumn.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        returnedDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));

        try {
            int studentId = StudentSession.getStudentId();
            ObservableList<BorrowHistory> historyList = bookDao.getBorrowHistory(studentId);
            historyTableView.setItems(historyList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
