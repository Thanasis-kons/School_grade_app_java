package school;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentRegistration {
private static final String URL = "jdbc:mysql://localhost:3306/school_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "";



public static void insertStudent(String fname, String lname, String bday, String gender, String address) {
    try {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (first_name, last_name, date_of_birth , gender, home_address) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setString(3, bday);
        stmt.setString(4, gender);
        stmt.setString(5, address);
        stmt.executeUpdate();
        
        System.out.println("επιτυχής εγγραφή!");
        conn.close();
              
       
        
        
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}

