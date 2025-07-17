/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.popups.addbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.model.book.Book;

/**
 * FXML Controller class
 *
 * @author jeanv
 */

public class AddbookController implements Initializable {

    @FXML
    private ImageView bookCoverView;
    @FXML
    private Button addBookButton;
    @FXML
    private TextField libraryBookIDField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private DatePicker publishedDatePicker;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField editionField;
    @FXML
    private TextField pagesField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField copiesField;
    @FXML
    private TextField floorField;
    @FXML
    private TextField shelfField;

    private Book book = new Book();
    private DBBookDAO modifyBook = new DBBookDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleAddBookClick(ActionEvent event) {
        try {
            book.setId(Integer.parseInt(libraryBookIDField.getText()));
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            
            if (publishedDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Published Date is required.");
            return;
            }
            book.setPublishedDate(java.sql.Date.valueOf(publishedDatePicker.getValue()));
            book.setIsbn(isbnField.getText());
            book.setGenre(genreField.getText());
            book.setEdition(Integer.parseInt(editionField.getText()));
            book.setPages(Integer.parseInt(pagesField.getText()));
            book.setDescription(descriptionField.getText());
            // assuming status is text like "Available" or "Not Available"
            book.setStatus(statusField.getText().equalsIgnoreCase("Available"));
            book.setCopies(Integer.parseInt(copiesField.getText()));
            book.setFloor(floorField.getText());
            book.setShelf(shelfField.getText());

            boolean success = modifyBook.insertBook(book);
            
            if (success) {
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Book Added", "Book was added successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add the book.");
            }
            
        } catch (Exception e) {
             e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed: " + e.getMessage());
            }
        }

    public void handleBookCoverClick(MouseEvent event) {

        if (event.getClickCount() == 2) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Book Cover");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*png", "*jpg", "*jpeg"));
            File selectedFile = fileChooser.showOpenDialog(bookCoverView.getScene().getWindow());

            if (selectedFile != null) {
                try {
                    byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                    book.setBookCover(imageBytes);
                    Image image = new Image(new ByteArrayInputStream(imageBytes));
                    bookCoverView.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (book.getBookCover() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Book cover is required.");
                return;
            }
        }
    }

    public void clearForm() { //CLEARS THE FIELDS AFTER ADDING
        
        bookCoverView.setImage(null);
        libraryBookIDField.clear();
        titleField.clear();
        authorField.clear();
        publishedDatePicker.setValue(null);
        isbnField.clear();
        genreField.clear();
        editionField.clear();
        pagesField.clear();
        descriptionField.clear();
        statusField.clear();
        copiesField.clear();
        floorField.clear();
        shelfField.clear();
        book = new Book();
    }
    
    //POP UP SHOWING IF SUCCESSFULLLLLL OR NOT
    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
