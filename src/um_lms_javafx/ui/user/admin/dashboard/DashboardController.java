/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.dashboard;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.DAO.DBUserDAO;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.user.Student;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class DashboardController implements Initializable {
    
    @FXML Label issuedBooksLabel, totalBooksLabel, totalStudentsLabel, booksReturnedLabel;
    @FXML private TableView<Student> newUsersTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, Integer> booksIssuedColumn; 
    @FXML private TableView<Book> newBooksTable;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, Integer> bookIDColumn;
    @FXML private TableColumn<Book, Integer> copiesColumn;
    @FXML private LineChart<String, Number> analyticsLineChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis; 
    
    DBUserDAO studentDao = new DBUserDAO();
    DBBookDAO bookDao = new DBBookDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            dashboardAnalyticsLabels();
            lineChart();
            loadNewUsers();
            loadNewBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }    
    
    private void dashboardAnalyticsLabels() throws SQLException {
        issuedBooksLabel.setText(String.valueOf(bookDao.countIssuedBooks()));
        totalBooksLabel.setText(String.valueOf(bookDao.countTotalBooks()));
        totalStudentsLabel.setText(String.valueOf(studentDao.countTotalStudents()));
        booksReturnedLabel.setText(String.valueOf(bookDao.countReturnedBooks()));
    }
    
    private void lineChart() throws SQLException {
        
        //(THIS WEEK BASED)
        //Books Issued
        XYChart.Series<String, Number> booksIssued = new XYChart.Series<>();
        booksIssued.setName("Books Issued");
        booksIssued.getData().addAll(bookDao.getBooksIssuedThisWeek());
        
        //Total Book
        XYChart.Series<String, Number> newBooks = new XYChart.Series<>();
        newBooks.setName("New Books");
        newBooks.getData().addAll(bookDao.getNewBooksThisWeek());
        
        //Total Student
        XYChart.Series<String, Number> newStudents = new XYChart.Series<>();
        newStudents.setName("New Students");
        newStudents.getData().addAll(studentDao.getNewStudentsThisWeek());
        
        //Returned Books
        XYChart.Series<String, Number> bookReturned = new XYChart.Series<>();
        bookReturned.setName("Books Returned");
        bookReturned.getData().addAll(bookDao.getBooksReturnedThisWeek());

        analyticsLineChart.getData().clear();
        analyticsLineChart.getData().addAll(booksIssued, newBooks, newStudents, bookReturned);
        
    }
    private void loadNewUsers() throws SQLException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        booksIssuedColumn.setCellValueFactory(new PropertyValueFactory<>("booksIssued"));

        newUsersTable.setItems(studentDao.getNewUsers());
    }
    
    private void loadNewBooks() throws SQLException {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
        
        newBooksTable.setItems(bookDao.getNewBooks());
    }
    
}
