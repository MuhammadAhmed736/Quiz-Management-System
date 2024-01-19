package model;

public class Question {
    private int questionNo;
    private int questionid;
    private String questiontext;
    private String correctAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int timeDuration;
    
    public Question(int qno,int qid,String question,String answer,String opt1,String opt2,String opt3,String opt4,int timeDuration)
    {
        this.questionNo = qno;
        this.questionid = qid;
        this.questiontext = question;
        this.correctAnswer = answer;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
        this.timeDuration = timeDuration;
    }
    public int getQuestionNo()
    {
        return this.questionNo;
    }
    public int getQuestionId()
    {
        return this.questionid;
    }
    public String getQuestionText()
    {
        return this.questiontext;
    }
    public String getAnswer()
    {
        return this.correctAnswer;
    }
    public String getOption1()
    {
        return this.option1;
    }
    public String getOption2()
    {
        return this.option2;
    }
    public String getOption3()
    {
        return this.option3;
    }
    public String getOption4()
    {
        return this.option4;
    }
    public int gettimeDuration()
    {
        return this.timeDuration;
    }
}
