/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package um_lms_javafx.server.model.user;

/**
 *
 * @author jeanv
 */
public class StudentSession {
   private static int studentId;
    private static String fullName;

    public static void setStudent(int id, String name) {
        studentId = id;
        fullName = name;
    }

    public static int getStudentId() {
        return studentId;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void clearSession() {
        studentId = 0;
        fullName = null;
    }
}
