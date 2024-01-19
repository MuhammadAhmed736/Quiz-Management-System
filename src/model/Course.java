package model;

import java.util.List;
import java.util.ArrayList;

public class Course {
    
    private String courseID;
    private String courseTitle;
    private String instructor;
    private List<Quiz> quizzes;
    public Course(String courseID,String title,String instructor)
    {
        quizzes = new ArrayList<Quiz>();
        this.courseID = courseID;
        this.courseTitle = title;
        this.instructor = instructor;
        
    }
    public String getCourseId()
    {
        return this.courseID;
    }
    public String getCourseTitle()
    {
        return this.courseTitle;
    }
    public String getInstructor()
    {
        return this.instructor;
    }
    public void addQuiz(Quiz q)
    {
        this.quizzes.add(q);
    }
    public List<Quiz> getQuizzes()
    {
        return this.quizzes;
    }
    
}
