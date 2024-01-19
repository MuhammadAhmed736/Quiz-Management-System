package dao;

import java.util.ArrayList;
import java.util.List;
import model.Course;
import java.sql.*;

public class CourseDAO {
    public CourseDAO()
    {
        
    }
    
    public Boolean addCourse(Course course) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Insert the course record into the Course table only if CourseId doesn't exist
            String insertQuery = "INSERT INTO Course (CourseId, CourseTitle, InstructorName) " +
                                 "SELECT ?, ?, ? " +
                                 "WHERE NOT EXISTS (SELECT 1 FROM Course WHERE CourseId = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, course.getCourseId());
                preparedStatement.setString(2, course.getCourseTitle());
                preparedStatement.setString(3, course.getInstructor());
                preparedStatement.setString(4, course.getCourseId()); // for the WHERE clause
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                   return false;
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            //e.printStackTrace();
            return false;
        }
    }
    public void deleteCourse(Course course) {
       /* 
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Set auto-commit to false to manage transactions

            // 1. Delete rows from the Quiz table with ON DELETE CASCADE
            String deleteQuizzesQuery = "DELETE FROM Quiz WHERE CourseId = ?";
            try (PreparedStatement deleteQuizzesStatement = connection.prepareStatement(deleteQuizzesQuery)) {
                deleteQuizzesStatement.setString(1, course.getCourseId());
                deleteQuizzesStatement.executeUpdate();
            }

            // 2. Delete rows from the Course table
            String deleteCourseQuery = "DELETE FROM Course WHERE CourseId = ?";
            try (PreparedStatement deleteCourseStatement = connection.prepareStatement(deleteCourseQuery)) {
                deleteCourseStatement.setString(1, course.getCourseId());
                deleteCourseStatement.executeUpdate();
            }

            connection.commit(); // Commit the transaction
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
            try {
                // Rollback the transaction in case of an exception
                DatabaseConnection.getConnection().rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        }
        */
    }
    public List<Course>  getCourses()
    {
          
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT CourseID, CourseTitle, InstructorName FROM Course")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseId = resultSet.getString("CourseID");
                String courseTitle = resultSet.getString("CourseTitle");
                String instructorName = resultSet.getString("InstructorName");

                Course course = new Course(courseId, courseTitle, instructorName);
                courses.add(course);
            }

        } catch (SQLException e) {
            
            // Handle or log the exception appropriately
        }

        return courses;
    }
    public Boolean enrollCourse(String courseTitle,String studentId) {
    try (Connection connection = DatabaseConnection.getConnection()) { // Establish database connection
        // 1. Retrieve the course ID based on the title
        PreparedStatement getCourseIdStatement = connection.prepareStatement(
            "SELECT CourseID FROM Course WHERE CourseTitle = ?"
        );
        getCourseIdStatement.setString(1, courseTitle);
        ResultSet courseResult = getCourseIdStatement.executeQuery();

        if (!courseResult.next()) {
            return false;
           // throw new Exception("Course not found: " + courseTitle); // Handle course not found
        }
        String courseId = courseResult.getString("CourseID");

        // 2. Get the student ID (assuming you have a way to access it)
       // Replace with your method to retrieve student ID

        // 3. Check for existing enrollment to prevent duplicates
        PreparedStatement checkEnrollmentStatement = connection.prepareStatement(
            "SELECT COUNT(*) FROM Enrollment WHERE CourseID = ? AND student_id = ?"
        );
        checkEnrollmentStatement.setString(1, courseId);
        checkEnrollmentStatement.setString(2, studentId);
        ResultSet enrollmentCountResult = checkEnrollmentStatement.executeQuery();
        enrollmentCountResult.next();
        int enrollmentCount = enrollmentCountResult.getInt(1);

        if (enrollmentCount > 0) {
            return false;
            //throw new Exception("Student already enrolled in the course"); // Handle duplicate enrollment
        }

        // 4. Insert the enrollment record
        PreparedStatement insertEnrollmentStatement = connection.prepareStatement(
            "INSERT INTO Enrollment (CourseID, Student_id) VALUES (?, ?)"
        );
        insertEnrollmentStatement.setString(1, courseId);
        insertEnrollmentStatement.setString(2, studentId);
        insertEnrollmentStatement.executeUpdate();
        return true;
    } catch (SQLException e) {
        throw new RuntimeException("Error saving course enrollment", e); // Handle database errors
        
    }
    
}
    public List<Course> getEnrolledCourses(String studentId) {
    List<Course> enrolledCourses = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT Course.CourseID, CourseTitle, InstructorName " +
                        "FROM Course " +
                        "JOIN Enrollment ON Course.CourseID = Enrollment.CourseID " +
                        "WHERE Enrollment.Student_id = ?"
        );
        statement.setString(1, studentId);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String courseId = resultSet.getString("CourseID");
            String courseTitle = resultSet.getString("CourseTitle");
            String instructorName = resultSet.getString("InstructorName");

            Course course = new Course(courseId, courseTitle, instructorName);
            enrolledCourses.add(course);
        }

    } catch (SQLException e) {
        // Handle or log the exception appropriately
        throw new RuntimeException("Error retrieving enrolled courses", e);
    }

    return enrolledCourses;
}

   public Course getCourseByCourseTitle(String courseTitle) {
        Course course = null;

        // SQL query to select a course by course title
        String sql = "SELECT * FROM Course WHERE CourseTitle = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, courseTitle);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Assuming you have a Course constructor that takes relevant fields
                    course = new Course(
                            resultSet.getString("CourseId"),
                            resultSet.getString("CourseTitle"),
                            // Add other fields as needed
                            resultSet.getString("InstructorName")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }

        return course;
    }
}


