/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.issuedbooks;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.server.model.borrower.Borrower;
import um_lms_javafx.server.model.user.Student;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class IssuedBooksController implements Initializable {
    

    @FXML private TableView<Borrower> issuedBooksTableView;
    @FXML private TableColumn<Borrower, String> idColumn;
    // GET BORROWER NAME!!!! huhuhuhu this is so misnamed, will fix later
    @FXML private TableColumn<Borrower, String> titleColumn;
    @FXML private TableColumn<Borrower, String> borrowerNameColumn;
    @FXML private TableColumn<Borrower, LocalDateTime> dateIssuedColumn;
    @FXML private TableColumn<Borrower, String> returnDeadlineColumn;
    
    final private ObservableList<Borrower> borrowerList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind columns to Borrowers View
        idColumn.setCellValueFactory(new PropertyValueFactory<>("borrowerID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        borrowerNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        dateIssuedColumn.setCellValueFactory(new PropertyValueFactory<>("dateIssued"));
        returnDeadlineColumn.setCellValueFactory(new PropertyValueFactory<>("returnDeadline"));
        
        issuedBooksTableView.setItems(borrowerList);
        
      //  Student quandale = new Student("Quandale", "A.", "Dingle", "skibidi@gmail.com", "096969696969", "Skibidi");
      //  Book quanMillzBook = new Book(
      //      "I Caught My Baby Daddy Cheating With My Grandma At Waffle House And Now I'm Raising Our Triplets Alone",
      //      "QuanMillz",
      //      false, 12,
       //     Date.of(2021, 9, 14, 0, 0), 
      //      "Urban Fiction",
      //      "9780000000001",
      //      "Deluxe Street Edition",
      //      "472",            
      //      "Basement",
      //      "Drama-Section-A1"
      //  );

      //  
      //  addBorrower(1, quandale, quanMillzBook, LocalDateTime.of(2021, 9, 14, 0, 0));
      //  addBorrower(2, quandale, quanMillzBook, LocalDateTime.of(2021, 9, 14, 0, 0));
      //  addBorrower(3, quandale, quanMillzBook, LocalDateTime.of(2021, 9, 14, 0, 0));
      //  addBorrower(4, quandale, quanMillzBook, LocalDateTime.of(2021, 9, 14, 0, 0));
      //  addBorrower(5, quandale, quanMillzBook, LocalDateTime.of(2021, 9, 14, 0, 0));
    }    
    
    public void addBorrower(Student student, Book book) {
        borrowerList.add(new Borrower(student, book, "Borrowed"));
    }
    
}
