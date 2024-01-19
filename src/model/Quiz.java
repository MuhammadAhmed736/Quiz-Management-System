package model;

import java.util.List;

public class Quiz {
    
    int quizId;
    public String quizTitle;
    
    int timeDuration;
    int numberOfQuestions;
    List<Question> questions;
    
    public Quiz(int quizId,String title,int questions,int time)
    {
        this.quizTitle = title;
        this.quizId = quizId;
        this.timeDuration = time;
        this.numberOfQuestions = questions;
    }
    public Quiz()
    {

    }
    public String getTitle()
    {
        return this.quizTitle;
    }

    public Quiz getQuiz()
    {
        return this;
    }
    public int getQuizId()
    {
        return this.quizId;
    }
    public int getTimeDuration()
    {
        return this.timeDuration;
    }
    public int getNumberofQuestions()
    {
        return this.numberOfQuestions;
    }
    public List<Question> getQuestions()
    {
        return this.questions;
    }
    
}
