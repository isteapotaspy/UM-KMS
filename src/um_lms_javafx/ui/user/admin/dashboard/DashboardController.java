/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package um_lms_javafx.ui.user.admin.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author jeanv
 */
public class DashboardController implements Initializable {

    @FXML private LineChart<String, Number> analyticsLineChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Categories
        xAxis.setCategories(FXCollections.observableArrayList("New Book Last Day", "Book Issued", "New User", "Not Returned"));
        
        // FAKE DATE ONLYYY!!!
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Today");
        
        series.getData().add(new XYChart.Data<>("New Book Last Day", 16));
        series.getData().add(new XYChart.Data<>("Book Issued", 7));
        series.getData().add(new XYChart.Data<>("New User", 10));
        series.getData().add(new XYChart.Data<>("Not Returned", 3));
        
        analyticsLineChart.getData().add(series);
        
        //This is for hovering on any point of the line, a popup will show with the set value
        for (XYChart.Data<String, Number> data : series.getData()) {
        Tooltip.install(data.getNode(), new Tooltip(data.getYValue().toString()));
        }    
    }    
    
}
