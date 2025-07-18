/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.popups.borrowbook;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import um_lms_javafx.server.model.book.Book;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class BorrowbookController implements Initializable {

    @FXML ImageView bookCoverView;
    @FXML TextField libraryBookIDField;
    @FXML TextField titleField;
    @FXML TextField authorField;
    @FXML TextField publishedDateField; 
    @FXML TextField isbnField;
    @FXML TextField genreField;
    @FXML TextField editionField;
    @FXML TextField pagesField;
    @FXML TextField descriptionField;
    @FXML TextField statusField;
    @FXML TextField copiesField;
    @FXML TextField floorField;
    @FXML TextField shelfField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setBookDetails(Book book) {
        Image image = new Image(new ByteArrayInputStream(book.getBookCover()));
        bookCoverView.setImage(image);
        libraryBookIDField.setText(String.valueOf(book.getId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        LocalDate publishedDate = book.getPublishedDate();
        publishedDateField.setText(publishedDate.toString());   
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
}
