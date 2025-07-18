package um_lms_javafx.ui.user.admin.dashboard;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.DAO.DBUserDAO;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.user.Student;

public class DashboardController implements Initializable {

    // Dashboard Labels
    @FXML
    private Label issuedBooksLabel;

    @FXML
    private Label totalBooksLabel;

    @FXML
    private Label totalStudentsLabel;

    @FXML
    private Label booksReturnedLabel;

    @FXML
    private Label timeText;

    @FXML
    private Label libraryStatusText;

    // Tables for New Users
    @FXML
    private TableView<Student> newUsersTable;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Integer> idColumn;

    @FXML
    private TableColumn<Student, Integer> booksIssuedColumn;

    // Tables for New Books
    @FXML
    private TableView<Book> newBooksTable;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, Integer> bookIDColumn;

    @FXML
    private TableColumn<Book, Integer> copiesColumn;

    // Line Chart for Weekly Analytics
    @FXML
    private LineChart<String, Number> analyticsLineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    // DAOs
    private final DBUserDAO studentDao = new DBUserDAO();
    private final DBBookDAO bookDao = new DBBookDAO();

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

        updateTime();
    }

    private void dashboardAnalyticsLabels() throws SQLException {
        issuedBooksLabel.setText(String.valueOf(bookDao.countIssuedBooks()));
        totalBooksLabel.setText(String.valueOf(bookDao.countTotalBooks()));
        totalStudentsLabel.setText(String.valueOf(studentDao.countTotalStudents()));
        booksReturnedLabel.setText(String.valueOf(bookDao.countReturnedBooks()));
    }

    private void lineChart() throws SQLException {
        // Books Issued This Week
        XYChart.Series<String, Number> booksIssued = new XYChart.Series<>();
        booksIssued.setName("Books Issued");
        booksIssued.getData().addAll(bookDao.getBooksIssuedThisWeek());

        // New Books This Week
        XYChart.Series<String, Number> newBooks = new XYChart.Series<>();
        newBooks.setName("New Books");
        newBooks.getData().addAll(bookDao.getNewBooksThisWeek());

        // New Students This Week
        XYChart.Series<String, Number> newStudents = new XYChart.Series<>();
        newStudents.setName("New Students");
        newStudents.getData().addAll(studentDao.getNewStudentsThisWeek());

        // Books Returned This Week
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

    private void updateTime() {
        LocalTime currentTime = LocalTime.now();

        // Format time to 12-hour with AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);

        timeText.setText(formattedTime);

        // Optional: Display library status
        if (currentTime.isAfter(LocalTime.of(7, 0)) && currentTime.isBefore(LocalTime.of(21, 0))) {
            libraryStatusText.setText("Library is currently open.");
        } else {
            libraryStatusText.setText("Library is now closed");
        }
    }
}
