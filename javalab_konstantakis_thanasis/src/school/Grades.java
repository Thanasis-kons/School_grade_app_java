package school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Grades {
    private int studentId ;
    private int courseId;
    private int grade;

    public Grades(int studentId, int courseId, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    
    public void saveGrade(Connection conn) throws SQLException {
        String sql = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, studentId);
        statement.setInt(2, courseId);
        statement.setInt(3, grade);
        statement.executeUpdate();
    }
}
