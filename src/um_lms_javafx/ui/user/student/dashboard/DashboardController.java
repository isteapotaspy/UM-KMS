package um_lms_javafx.ui.user.student.dashboard;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ravin
 */
public class DashboardController implements Initializable {

    @FXML
    private Label announcementText;

    @FXML
    private Label timeText;

    @FXML
    private Label libraryStatusText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set announcements
        announcementText.setText("[1] Break rooms in the library are currently not available due to maintenance.\n"
                + "[2] Further, borrowing is temporarily on hold due to updates in our library management system.");
        announcementText.setWrapText(true);

        // Set time and status
        updateTime();
    }

    private void updateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);

        timeText.setText(formattedTime);

        if (currentTime.isAfter(LocalTime.of(7, 0)) && currentTime.isBefore(LocalTime.of(21, 0))) {
            libraryStatusText.setText("Library is now currently open.");
        } else {
            libraryStatusText.setText("Library is now closed.");
        }
    }
}
