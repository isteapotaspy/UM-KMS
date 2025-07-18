/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.managebooks;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import um_lms_javafx.server.DAO.DBBookDAO;
import um_lms_javafx.server.model.book.Book;
import um_lms_javafx.ui.user.popups.editbook.EditbookController;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class ManageBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button addNewBook;

    @FXML
    private TableView<Book> manageBooksTableView;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Date> publishedDateColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, Integer> copiesColumn;
    @FXML
    private TableColumn<Book, Void> actionsColumn;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    DBBookDAO bookDao = new DBBookDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));

        //for action column
        actionsColumn.setCellFactory(column -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {

                editButton.getStyleClass().addAll("button-added", "edit-button");
                deleteButton.getStyleClass().addAll("button-added", "delete-button");

                //edit button
                editButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/editbook/editbook.fxml"));
                        Pane dialogRoot = loader.load();
                        EditbookController controller = loader.getController();
                        controller.setBook(book, bookDao);

                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Modify Book");
                        dialogStage.initModality(Modality.APPLICATION_MODAL);
                        dialogStage.setResizable(false);
                        dialogStage.setScene(new Scene(dialogRoot));
                        dialogStage.showAndWait();

                        getTableView().refresh(); // refresh after closing dialog
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Failed to load editbook.fxml: " + e.getMessage());
                    }

                });

                //delete button
                deleteButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    boolean deleted = bookDao.deleteBook(book.getId());
                    if (deleted) {
                        getTableView().getItems().remove(book);
                        System.out.println("Deleted Book: " + book.getTitle());
                    } else {
                        System.out.println("Failed to delete: " + book.getTitle());
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, editButton, deleteButton);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        try {
            bookList = bookDao.getAllBooks();
            manageBooksTableView.setItems(bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleAddNewBookButtonClick() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um_lms_javafx/ui/user/popups/addbook/addbook.fxml"));
            Pane dialogRoot = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Book");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(dialogRoot));
            dialogStage.showAndWait();

            bookList = bookDao.getAllBooks();
            manageBooksTableView.setItems(bookList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
