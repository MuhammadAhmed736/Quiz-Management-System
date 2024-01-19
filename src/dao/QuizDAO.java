package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Question;
import model.Quiz;

public class QuizDAO {
   
    
    public boolean addQuiz(Quiz q,String courseId)
    {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Insert the Quiz record into the Course table only if Quiz doesn't exist
            String insertQuery = "INSERT INTO Quiz (QuizId, QuizTitle, NumberOfQuestions, QuizDuration, CourseID) " +
                                 "SELECT ?, ?, ?, ?, ? " +
                                 "WHERE NOT EXISTS (SELECT 1 FROM Quiz WHERE QuizId = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, q.getQuizId());
                preparedStatement.setString(2,q.getTitle() );
                preparedStatement.setInt(3, q.getNumberofQuestions());
                preparedStatement.setInt(4,q.getTimeDuration() );
                preparedStatement.setString(5,courseId );
                preparedStatement.setInt(6, q.getQuizId());
                int rowsAffected =  preparedStatement.executeUpdate();   
                if (rowsAffected > 0) {
                    System.out.println(" row Affected");
                    return true;
                } else {
                    System.out.println("no row");
                   return false;
                }
            } 
            
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
            return false;
        }
        
    }
    
    public List<Quiz> getQuizzes(String selectedCourseId) {
        List<Quiz> quizzes = new ArrayList<>();

        // SQL query to select quizzes by course ID
        String sql = "SELECT * FROM Quiz WHERE CourseId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, selectedCourseId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming you have a Quiz constructor that takes relevant fields
                    Quiz quiz = new Quiz(
                            resultSet.getInt("QuizId"),
                            resultSet.getString("QuizTitle"),
                            // Add other fields as needed
                            resultSet.getInt("NumberOfQuestions"),
                            resultSet.getInt("QuizDuration")
                    );
                    quizzes.add(quiz);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
        }

        return quizzes;
    }
    
        
    public void getQuiz(Quiz quiz)
    {
        
    }
     public List<Question> getQuestionsForQuiz(Quiz quiz) {
        List<Question> questions = new ArrayList<>();

        // SQL query to retrieve questions for a given quiz
        String sql = "SELECT * FROM question WHERE QuizId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the QuizId parameter in the prepared statement
            preparedStatement.setInt(1, quiz.getQuizId());

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Iterate through the result set and create Question objects
                while (resultSet.next()) {
                    
                    //(int qno,int qid,String question,String answer,String opt1,String opt2,String opt3,String opt4)
                    Question question = new Question(
                            resultSet.getInt("QuestionNumber"),
                            resultSet.getInt("QuestionId"),
                            resultSet.getString("QuestionStatement"),
                            resultSet.getString("CorrectAnswer"),
                            resultSet.getString("Option1"),
                            resultSet.getString("Option2"),
                            resultSet.getString("Option3"),
                            resultSet.getString("Option4"),
                            resultSet.getInt("TimeDuration")

                            );
                    // Add the question to the list
                    questions.add(question);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return questions;
    }
}
