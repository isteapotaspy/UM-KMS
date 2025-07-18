/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.shared_panes.search;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.ui.user.popups.borrowbook.BorrowbookController;
import um_lms_javafx.server.DAO.DBBookDAO;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class SearchController implements Initializable {

    @FXML
    private TableView<Book> booksTableView;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> statusColumn;
    @FXML
    private TableColumn<Book, Integer> copiesColumn;
    @FXML
    private TableColumn<Book, LocalDate> publishedDateColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> editionColumn;
    @FXML
    private TableColumn<Book, String> pagesColumn;
    @FXML
    private TableColumn<Book, String> floorColumn;
    @FXML
    private TableColumn<Book, String> shelfColumn;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind columns to Book properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // id -> getId()
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title")); // getTitle()
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        statusColumn.setCellValueFactory(cellData -> {
            boolean status = cellData.getValue().getStatus();
            return new SimpleStringProperty(status ? "Available" : "Not Available"); //for status to display Available and Not Available instead of true or false
        });
        
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        shelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelf"));

        // Set data to TableView
        refreshTable();

    }

    public void handleTableViewClick(MouseEvent event) {
        if (event.getClickCount() == 2 && !booksTableView.getSelectionModel().isEmpty()) {
            System.out.println("Double click detected!");
            Book selectedBook = booksTableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/borrowbook/borrowbook.fxml"));
                    Pane dialogRoot = loader.load();

                    BorrowbookController controller = loader.getController();
                    controller.setBookDetails(selectedBook);

                    Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.APPLICATION_MODAL);
                    dialogStage.setResizable(false);
                    dialogStage.setScene(new Scene(dialogRoot));
                    dialogStage.showAndWait(); // FORCE THE PARENT TO WAIT FOR MORE INPUT
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    public void refreshTable() {
        booksTableView.setItems(DBBookDAO.loadBooks());
    }

}
