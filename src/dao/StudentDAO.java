package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import model.Student;

public class StudentDAO {
    
    private static final String INSERT_STUDENT_SQL = 
        "INSERT INTO Student (student_id, firstName, lastName, emailAddress, Password) VALUES (?, ?, ?, ?, ?)";

    public Student saveStudent(Student student) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {

            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmailAddress());
            preparedStatement.setString(5, student.getPassword());
            preparedStatement.executeUpdate();
            
            
            System.out.println("Student Saved");
            return student;

            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
            // Handle or log the exception appropriately
        }
    }

         public Student validateStudentLogin(String email, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student WHERE emailAddress = ? AND Password = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve values from the result set and create a Student object
                    String studentId = resultSet.getString("student_id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                
                    // Add more fields as needed

                    // Create and return the Student object
                    return new Student( firstName, lastName, email, password,studentId);
                } else {
                    // No matching record found
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
            return null;
        }
    }
         
        public Boolean updateStudent(Student s) {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE Student SET firstName = ?, lastName = ?, emailAddress = ?, Password = ? WHERE student_id = ?")) {

        preparedStatement.setString(1, s.getFirstName());
        preparedStatement.setString(2, s.getLastName());
        preparedStatement.setString(3, s.getEmailAddress());
        preparedStatement.setString(4, s.getPassword());
        preparedStatement.setString(5, s.getStudentId()); // Use student ID for WHERE clause

        int updatedRows = preparedStatement.executeUpdate();

        if (updatedRows > 0) {
            
            System.out.println("Student updated successfully");
            return true;
        } else {
            System.out.println("No student found with the given student ID");
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        // Handle or log the exception appropriately
    }
}

   }

