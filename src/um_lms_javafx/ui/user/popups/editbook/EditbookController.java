/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.popups.editbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.model.book.Book;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class EditbookController implements Initializable {

    @FXML private Button updateBookButton;
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
    
    private Book book;
    private DBBookDAO bookDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setBook(Book book, DBBookDAO bookDAO) {
        this.book = book;
        this.bookDAO = bookDAO;
        
        Image image = new Image(new ByteArrayInputStream(book.getBookCover()));
        bookCoverView.setImage(image);
        libraryBookIDField.setText(String.valueOf(book.getId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
       publishedDatePicker.setValue(book.getPublishedDate());
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
    
    public void handleUpdateButtonClick() {
       
            book.setId(Integer.parseInt(libraryBookIDField.getText()));
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setPublishedDate(publishedDatePicker.getValue());
            book.setIsbn(isbnField.getText());
            book.setGenre(genreField.getText());
            book.setEdition(Integer.parseInt(editionField.getText()));
            book.setPages(Integer.parseInt(pagesField.getText()));
            book.setDescription(descriptionField.getText());
            // status returns true if "Available" is typed 
            book.setStatus(statusField.getText().equalsIgnoreCase("Available"));
            book.setCopies(Integer.parseInt(copiesField.getText()));
            book.setFloor(floorField.getText());
            book.setShelf(shelfField.getText());

            boolean success = bookDAO.updateBook(book);
                if (success) {
                    ((Stage) titleField.getScene().getWindow()).close();
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
    
    //POP UP SHOWING IF SUCCESSFULLLLLL OR NOT
    public void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
        
        
}
   
