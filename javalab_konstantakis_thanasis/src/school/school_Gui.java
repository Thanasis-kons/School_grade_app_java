package school;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

public class school_Gui {
    private JTable table_show;
    private JScrollPane scrollPane;
    private JFrame frame;
    private JTextField txt_Fname;
    private JTextField txt_Lname;
    private JTextField txt_Bday;
    private JComboBox<String> cbox_Gender;
    private JTextField txt_Address;
    private JComboBox<String> cbox_grades;
    private JComboBox<String> cboxSearchStudent;
    private JComboBox<String> cboxSearchStudentGrades;
    private static final String URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    school_Gui window = new school_Gui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public school_Gui() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 724, 299);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 621, 243);
        frame.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Registration", null, panel, null);
        panel.setLayout(null);

        txt_Fname = new JTextField();
        txt_Fname.setBounds(271, 30, 96, 19);
        panel.add(txt_Fname);
        txt_Fname.setColumns(10);

        txt_Lname = new JTextField();
        txt_Lname.setBounds(271, 59, 96, 19);
        panel.add(txt_Lname);
        txt_Lname.setColumns(10);

        txt_Bday = new JTextField();
        txt_Bday.setBounds(271, 88, 96, 19);
        panel.add(txt_Bday);
        txt_Bday.setColumns(10);

        cbox_Gender = new JComboBox<String>();
        cbox_Gender.setBounds(271, 117, 96, 21);
        cbox_Gender.addItem("Male");
        cbox_Gender.addItem("Female");
        cbox_Gender.addItem("Other");
        panel.add(cbox_Gender);

        txt_Address = new JTextField();
        txt_Address.setBounds(271, 146, 96, 19);
        panel.add(txt_Address);
        txt_Address.setColumns(10);

        JButton btn_insStudent = new JButton("Register");
        btn_insStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fname = txt_Fname.getText();
                String lname = txt_Lname.getText();
                String bday = txt_Bday.getText();
                String gender = (String) cbox_Gender.getSelectedItem();
                String address = txt_Address.getText();

                try {
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                    String query = "INSERT INTO students (first_name, last_name, date_of_birth, gender, home_address) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, fname);
                    stmt.setString(2, lname);
                    stmt.setString(3, bday);
                    stmt.setString(4, gender);
                    stmt.setString(5, address);

                    stmt.executeUpdate();
                    conn.close();

                    // Display success message
                    JOptionPane.showMessageDialog(null, "Student registration successful.");

                    // Clear the text fields
                    txt_Fname.setText("");
                    txt_Lname.setText("");
                    txt_Bday.setText("");
                    cbox_Gender.setSelectedIndex(0);
                    txt_Address.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error during student registration.");
                    ex.printStackTrace();
                }
            }
        });
        btn_insStudent.setBounds(159, 177, 114, 21);
        panel.add(btn_insStudent);

        JLabel lblNewLabel = new JLabel("First Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(87, 33, 65, 16);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Last Name");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(87, 60, 65, 16);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Date of Birth");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(87, 89, 65, 16);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Gender");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(87, 123, 65, 13);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Address");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(87, 149, 65, 16);
        panel.add(lblNewLabel_4);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Grade entry", null, panel_1, null);
        panel_1.setLayout(null);

        JComboBox<String> cbox_student = new JComboBox<String>();
        cbox_student.setBounds(60, 67, 181, 31);
        panel_1.add(cbox_student);

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT id, first_name, last_name FROM students";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("id");
                String studentFname = rs.getString("first_name");
                String studentLname = rs.getString("last_name");
                String studentFullname = studentFname + " " + studentLname;

                cbox_student.addItem(studentId + " " + studentFullname);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JComboBox<String> cbox_courses = new JComboBox<String>();
        cbox_courses.setBounds(414, 67, 151, 31);
        panel_1.add(cbox_courses);

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT id, title FROM courses";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseId = rs.getString("id");
                String courseTitle = rs.getString("title");
                String courseInfo = courseId + " " + courseTitle;

                cbox_courses.addItem(courseInfo);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel lblNewLabel_5 = new JLabel("Select Student");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(85, 30, 104, 22);
        panel_1.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Select Course");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setBounds(428, 33, 120, 17);
        panel_1.add(lblNewLabel_6);

        cbox_grades = new JComboBox<String>();
        cbox_grades.setBounds(270, 143, 96, 19);
        panel_1.add(cbox_grades);
        cbox_grades.addItem("1");
        cbox_grades.addItem("2");
        cbox_grades.addItem("3");
        cbox_grades.addItem("4");
        cbox_grades.addItem("5");
        cbox_grades.addItem("6");
        cbox_grades.addItem("7");
        cbox_grades.addItem("8");
        cbox_grades.addItem("9");
        cbox_grades.addItem("10");

        JButton btn_grade_insertion = new JButton("Grade Entry");
        btn_grade_insertion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    // Get selected student ID, course ID, and grade
                    String selectedStudent = (String) cbox_student.getSelectedItem();
                    int studentId = Integer.parseInt(selectedStudent.split(" ")[0]);
                    String selectedCourse = (String) cbox_courses.getSelectedItem();
                    int courseId = Integer.parseInt(selectedCourse.split(" ")[0]);
                    int grade = Integer.parseInt((String) cbox_grades.getSelectedItem());

                    // Create Grades object and save to database
                    Grades newGrade = new Grades(studentId, courseId, grade);
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    newGrade.saveGrade(conn);
                    conn.close();

                    // Display success message
                    JOptionPane.showMessageDialog(null, "Grade entry successful.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        });

        btn_grade_insertion.setBounds(239, 169, 151, 21);
        panel_1.add(btn_grade_insertion);

        JLabel lblNewLabel_7 = new JLabel("Grade");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setBounds(264, 111, 94, 22);
        panel_1.add(lblNewLabel_7);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Search", null, panel_2, null);
        panel_2.setLayout(null);

        JLabel lblSearchStudent = new JLabel("Search Student");
        lblSearchStudent.setHorizontalAlignment(SwingConstants.CENTER);
        lblSearchStudent.setBounds(24, 10, 125, 13);
        panel_2.add(lblSearchStudent);

        cboxSearchStudentGrades = new JComboBox<String>();
        cboxSearchStudentGrades.setBounds(177, 7, 218, 19);
        panel_2.add(cboxSearchStudentGrades);

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT id, first_name, last_name FROM students";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("id");
                String studentFname = rs.getString("first_name");
                String studentLname = rs.getString("last_name");
                String studentInfo = studentId + " " + studentFname + " " + studentLname;

                cboxSearchStudentGrades.addItem(studentInfo);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(255, 255, 255));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchStudentId = (String) cboxSearchStudentGrades.getSelectedItem();
                searchStudentId = searchStudentId.split(" ")[0];

                try {
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    Student student = new Student();
                    ResultSet resultSet = student.getCoursesAndGrades(conn, Integer.parseInt(searchStudentId));

                    // Create a DefaultTableModel and set it as the table model for table_show
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Course");
                    model.addColumn("Grade");

                    // Populate the table with the data from the ResultSet
                    while (resultSet.next()) {
                        String courseName = resultSet.getString("title");
                        int grade = resultSet.getInt("grade");
                        model.addRow(new Object[]{courseName, grade});
                    }
                    if (scrollPane != null) {
                        panel_2.remove(scrollPane);
                    }
                    table_show = new JTable();
                    scrollPane = new JScrollPane(table_show);
                    scrollPane.setBounds(10, 50, 600, 150);
                    panel_2.add(scrollPane);

                    table_show.setModel(model);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSearch.setBounds(414, 2, 106, 28);
        panel_2.add(btnSearch);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Deletion", null, panel_3, null);
        panel_3.setLayout(null);

        JLabel lblStudentId = new JLabel("Student Number:");
        lblStudentId.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudentId.setBounds(193, 40, 130, 16);
        panel_3.add(lblStudentId);

        JTextField txtStudentId = new JTextField();
        txtStudentId.setBounds(333, 38, 96, 19);
        panel_3.add(txtStudentId);
        txtStudentId.setColumns(10);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentId = Integer.parseInt(txtStudentId.getText());
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                    // Delete student's grades
                    String deleteGradesQuery = "DELETE FROM grades WHERE student_id = ?";
                    PreparedStatement deleteGradesStmt = conn.prepareStatement(deleteGradesQuery);
                    deleteGradesStmt.setInt(1, studentId);
                    deleteGradesStmt.executeUpdate();

                    // Delete student
                    String deleteStudentQuery = "DELETE FROM students WHERE id = ?";
                    PreparedStatement deleteStudentStmt = conn.prepareStatement(deleteStudentQuery);
                    deleteStudentStmt.setInt(1, studentId);
                    deleteStudentStmt.executeUpdate();

                    conn.close();

                    // Display success message
                    JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting student.");
                    ex.printStackTrace();
                }
            }
        });
        btnDelete.setBounds(283, 85, 96, 21);
        panel_3.add(btnDelete);
    }
}
