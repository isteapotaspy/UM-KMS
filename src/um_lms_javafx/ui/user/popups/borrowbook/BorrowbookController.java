/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.popups.borrowbook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import um_lms_javafx.server.model.book.Book;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class BorrowbookController implements Initializable {

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
        libraryBookIDField.setText(String.valueOf(book.getId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        publishedDateField.setText(String.valueOf(book.getPublishedDate()));
        isbnField.setText(book.getIsbn());
        genreField.setText(book.getGenre());
        editionField.setText(book.getEdition());
        pagesField.setText(book.getPages());
        descriptionField.setText(book.getDescription());
        statusField.setText(String.valueOf(book.getStatus()));
        copiesField.setText(String.valueOf(book.getCopies()));
        floorField.setText(book.getFloor());
        shelfField.setText(book.getShelf());
    }
}
