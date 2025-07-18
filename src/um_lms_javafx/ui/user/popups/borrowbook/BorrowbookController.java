package um_lms_javafx.ui.user.popups.borrowbook;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.DAO.DBBorrowerDAO;
import um_lms_javafx.server.DAO.DBStudentDAO;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.borrower.Borrower;
import um_lms_javafx.server.model.user.Student;

public class BorrowbookController implements Initializable {

    @FXML private ImageView bookCoverView;
    @FXML private TextField libraryBookIDField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField publishedDateField;
    @FXML private TextField isbnField;
    @FXML private TextField genreField;
    @FXML private TextField editionField;
    @FXML private TextField pagesField;
    @FXML private TextField descriptionField;
    @FXML private TextField statusField;
    @FXML private TextField copiesField;
    @FXML private TextField floorField;
    @FXML private TextField shelfField;
    @FXML private TextField studentIDField;

    private final DBStudentDAO studentDAO = new DBStudentDAO();
    private final DBBookDAO bookDAO = new DBBookDAO();
    private final DBBorrowerDAO borrowerDAO = new DBBorrowerDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable all book info fields
        TextField[] fields = {
            titleField, authorField, publishedDateField, isbnField, genreField,
            editionField, pagesField, descriptionField, statusField,
            copiesField, floorField, shelfField, libraryBookIDField
        };
        for (TextField field : fields) {
            field.setDisable(true);
        }
        
        studentIDField.setText("");
        libraryBookIDField.setText("");
    }

    @FXML
    public void processRequest() {
        String studentIDText = studentIDField.getText().trim();
        String libraryBookIDText = libraryBookIDField.getText().trim();
        
        if (studentIDText.isEmpty() || libraryBookIDText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in both Book ID and Student ID.");
            return;
        }

        if (!studentIDText.matches("^\\d{6}$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Student ID", "Student ID must be exactly 6 digits.");
            return;
        }

        if (!libraryBookIDText.matches("^\\d{1,5}$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Book ID", "Book ID must be up to 5 digits.");
            return;
        }

        try {
            int studentID = Integer.parseInt(studentIDText);
            int bookID = Integer.parseInt(libraryBookIDText);

            Student student = studentDAO.findStudentByID(studentID);
            Book book = bookDAO.findBookByID(bookID);

            if (student == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Student not found.");
                return;
            }

            if (book == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Book not found.");
                return;
            }
            
            if (studentIDField == null || libraryBookIDField == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Book not found.");
                return;
            }

            setBookDetails(book); // display book info before processing

            Borrower borrower = new Borrower(student, book, "Borrowed");
            boolean success = borrowerDAO.insertBorrower(borrower);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book reserved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to reserve book.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "IDs must be numeric.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    @FXML
    public void setBookDetails() {
        String bookIDText = libraryBookIDField.getText().trim();
        if (!bookIDText.matches("^\\d{1,5}$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Book ID", "Book ID must be up to 5 digits.");
            return;
        }

        try {
            int bookID = Integer.parseInt(bookIDText);
            Book book = bookDAO.findBookByID(bookID);

            if (book != null) {
                setBookDetails(book);
            } else {
                showAlert(Alert.AlertType.ERROR, "Not Found", "No book found with that ID.");
            }

        } catch (NumberFormatException | SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load book details.");
            e.printStackTrace();
        }
    }

    public void setBookDetails(Book book) {
        if (book.getBookCover() != null) {
            Image image = new Image(new ByteArrayInputStream(book.getBookCover()));
            bookCoverView.setImage(image);
        }

        libraryBookIDField.setText(String.valueOf(book.getId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        publishedDateField.setText(String.valueOf(book.getPublishedDate()));
        isbnField.setText(book.getIsbn());
        genreField.setText(book.getGenre());
        editionField.setText(String.valueOf(book.getEdition()));
        pagesField.setText(String.valueOf(book.getPages()));
        descriptionField.setText(book.getDescription());
        statusField.setText(book.getStatus() ? "Available" : "Not Available");
        copiesField.setText(String.valueOf(book.getCopies()));
        floorField.setText(book.getFloor());
        shelfField.setText(book.getShelf());
    }

    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
