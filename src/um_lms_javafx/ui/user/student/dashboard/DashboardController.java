package um_lms_javafx.ui.user.student.dashboard;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import um_lms_javafx.server.model.user.StudentSession;

public class DashboardController implements Initializable {

    @FXML private Label announcementText;
    @FXML private Label welcomeDashboard;
    @FXML private Label timeText;
    @FXML private Label libraryStatusText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Announcements
        announcementText.setText(
            "[1] Break rooms in the library are currently not available due to maintenance.\n" +
            "[2] Further, borrowing is temporarily on hold due to updates in our library management system."
        );
        announcementText.setWrapText(true);

        // Welcome message
        welcomeDashboard.setText("Hi, " + StudentSession.getFullName() + "!");

        // Time and Library Status
        updateTime();
    }

    private void updateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        timeText.setText(currentTime.format(formatter));

        if (currentTime.isAfter(LocalTime.of(7, 0)) && currentTime.isBefore(LocalTime.of(21, 0))) {
            libraryStatusText.setText("Library is now currently open.");
        } else {
            libraryStatusText.setText("Library is now closed.");
        }
    }
}
