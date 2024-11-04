import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/End Term"; // Update if needed
    private static final String USER = "postgres"; // Adjust if your username is different
    private static final String PASSWORD = "4321"; // Adjust for your actual password

    public void saveStudent(Student student) {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO students (name, course, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getCourse().getName());
            statement.setString(3, student.getEmail());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("The student has been successfully registered!");
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String courseName = resultSet.getString("course");
                String email = resultSet.getString("email");
                Course course;

                if (courseName.equals("Programming Course")) {
                    course = new ProgrammingCourse();
                } else {
                    course = new DesignCourse();
                }
                Student student = new RegularStudent(name, course, email);
                students.add(student);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return students;
    }
}
