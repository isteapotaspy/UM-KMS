/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import um_lms_javafx.server.model.user.Student;

/**
 *
 * @author jeanv
 */
public class DBUserDAO {

    public static ObservableList<Student> getNewUsers() throws SQLException {
        ObservableList<Student> list = FXCollections.observableArrayList();
        String sql = "SELECT first_name, middle_name, last_name, user_id, books_issued FROM library_users "
                + "WHERE date_created >= CURDATE() - INTERVAL 7 DAY AND admin_access = 0";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("lastName"),
                        rs.getInt("studentID"),
                        rs.getInt("booksIssued")
                ));
            }
        }
        return list;
    }

    //COunt Total Stduents
    public int countTotalStudents() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM library_users WHERE admin_access = 0";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;
    }

    //LINE GRAPH FOR DASHBOARD
    public List<XYChart.Data<String, Number>> getNewStudentsThisWeek() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = """
        SELECT DATE(date_created) AS day, COUNT(*) AS total
        FROM library_users
        WHERE date_created >= CURDATE() - INTERVAL 6 DAY AND admin_access = 0
        GROUP BY day
        ORDER BY day
    """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<XYChart.Data<String, Number>> data = new ArrayList<>();
        while (rs.next()) {
            data.add(new XYChart.Data<>(rs.getString("day"), rs.getInt("total")));
        }
        return data;
    }
}
