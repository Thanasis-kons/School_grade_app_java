package school;
import school.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String homeAddress;

    public Student(int id, String firstName, String lastName, Date dateOfBirth, String gender, String homeAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.homeAddress = homeAddress;
    }

    public Student() {
		
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
                + dateOfBirth + ", gender=" + gender + ", homeAddress=" + homeAddress + "]";
    }

    public static void main(String[] args) {
        // Create and initialise a Student object
        Student student = new Student(9, "John", "Doe", Date.valueOf("1983-08-08"), "Male", "8 Smyrnis Street, Samos");
        System.out.println(student);
    }
    
    public ResultSet getCoursesAndGrades(Connection conn, int studentId) throws SQLException {
        String query = "SELECT c.title, g.grade FROM courses c INNER JOIN grades g ON c.id = g.course_id WHERE g.student_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, studentId);
        return stmt.executeQuery();
    }
  
    
}
