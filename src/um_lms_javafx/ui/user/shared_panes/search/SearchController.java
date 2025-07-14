/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.shared_panes.search;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import um_lms_javafx.server.model.book.FakeBook;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class SearchController implements Initializable {
    
    @FXML private TableView<FakeBook> booksTableView;
    @FXML private TableColumn<FakeBook, String> idColumn;
    @FXML private TableColumn<FakeBook, String> titleColumn;
    @FXML private TableColumn<FakeBook, String> authorColumn;
    @FXML private TableColumn<FakeBook, String> publishedDateColumn;
    @FXML private TableColumn<FakeBook, String> genreColumn;
    
    private ObservableList<FakeBook> bookList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind columns to Book properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // Set data to TableView
        booksTableView.setItems(bookList);
        
        addBook("1", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("2", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("3", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("4", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("5", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("6", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
        addBook("7", "Harry Potter", "J.K Rowling", "March 17, 1997", "Fantasy");
    }    
    
    public void addBook(String id,String title, String author, String publishedDate, String genre) {
    bookList.add(new FakeBook(id, title, author, publishedDate, genre));
}
    
}
