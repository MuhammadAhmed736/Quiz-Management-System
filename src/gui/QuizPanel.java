package gui;

import dao.QuizDAO;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import model.Course;
import model.Question;
import model.Quiz;


public class QuizPanel extends JPanel implements ActionListener {
    MainFrame mainFrame;
   private JPanel quizPanel;
    Quiz selectedQuiz;
    Course selectedCourse;
    JTextArea questionArea;
    JProgressBar progressBar;
    ButtonGroup choices;
    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;
    JButton nextButton;
    JButton submitButton ;

    int questionNo=0;
    private int score = 0;
    private static int currentQuestionIndex = 0;
    private String[] userAnswers;
     private static  int lastQuestionIndex;

    
    List<Question> questions;
    
    public QuizPanel(MainFrame mainframe,Quiz quiz, Course selectedCourse)
    {  
       
        this.mainFrame = mainframe;
        this.selectedQuiz = quiz;
        this.selectedCourse = selectedCourse;
        
       
        //Retreive list of Questions from database
        QuizDAO quizdao = new QuizDAO();
        questions = quizdao.getQuestionsForQuiz(selectedQuiz);
        lastQuestionIndex = questions.size()-1;
        quizPanel = new JPanel();
        questionArea = new JTextArea() ;
        choices = new ButtonGroup();
        option1 = new JRadioButton("");
        option2 = new JRadioButton("");
        option3 = new JRadioButton("");
        option4 = new JRadioButton("");
        
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit Quiz");
        
        questionArea.setBounds(20,20,500,100);
        option1.setBounds(30,130,300,30);
        option2.setBounds(30,165,300,30);
        option3.setBounds(30,200,300,30);
        option4.setBounds(30,235,300,30);
        
        submitButton.setBounds(390,340,130,40);
        nextButton.setBounds(420,280,100,40);
        styleComponents();
        choices.add(option1); choices.add(option2); 
        choices.add(option3); choices.add(option4);
        
        quizPanel.add(questionArea);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);
        quizPanel.add(option4);
        
        quizPanel.add(nextButton);
        quizPanel.add(submitButton);
        
        submitButton.addActionListener(this);
        nextButton.addActionListener(this);
        submitButton.setEnabled(false); // Disable submit button initially
        nextButton.setEnabled(false);
        quizPanel.setBackground(Color.WHITE);
        quizPanel.setLayout(null);
        quizPanel.setVisible(true);
        quizPanel.setBounds(265,100,600,450);
        mainFrame.add(quizPanel);
        
        questionNo = 1;
        userAnswers = new String[questions.size()];
        ActionListener radioListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentQuestionIndex == questions.size() - 1) {
                submitButton.setEnabled(true);
                nextButton.setEnabled(false);
            }
            else if(currentQuestionIndex < questions.size() - 1)
                {
                    nextButton.setEnabled(true);
                }
        }
    };

    option1.addActionListener(radioListener);
    option2.addActionListener(radioListener);
    option3.addActionListener(radioListener);
    option4.addActionListener(radioListener);
        updateQuestion();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        if (source == submitButton) {
            
            for (int i = 0; i < userAnswers.length; i++) {
               
                if (userAnswers[i] != null && userAnswers[i].equals(questions.get(i).getAnswer())) {
                    score++;
                }
            }

            JOptionPane.showMessageDialog(mainFrame, "Your Score: " + score + " out of " + questions.size());

           
            mainFrame.remove(quizPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
            StudentPanel s = new StudentPanel(mainFrame);
        } else if (source == nextButton) {
            userAnswers[currentQuestionIndex] = getSelectedAnswer();
            currentQuestionIndex++;
            
            updateQuestion();
        } 
    
    }
    public void updateQuestion()
    {
        
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
  
            questionArea.setText("Q  "+questionNo + "/"+questions.size()+":  "+currentQuestion.getQuestionText());

            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());
            choices.clearSelection();
           
            nextButton.setEnabled(false);

            if (currentQuestionIndex == questions.size() - 1) {
                
                nextButton.setEnabled(false);
            }

        } else {
            
            nextButton.setEnabled(false);
      
        }
    questionNo++;
        
    }
 private String getSelectedAnswer() {
        if (option1.isSelected()) return option1.getText();
        if (option2.isSelected()) return option2.getText();
        if (option3.isSelected()) return option3.getText();
        if (option4.isSelected()) return option4.getText();
        return null; 
    }

    public void styleComponents()
    {
        
        Font segoeUIFont = new Font("Segoe UI", Font.CENTER_BASELINE, 15);
        
        styleRadioButtons(option1);
        styleRadioButtons(option2);
        styleRadioButtons(option3);
        styleRadioButtons(option4);
        
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 20, 10, 20);
        questionArea.setBorder(paddingBorder);
        questionArea.setFont(segoeUIFont);
        questionArea.setForeground(Color.DARK_GRAY);
        
        nextButton.setBackground(Color.DARK_GRAY);
        nextButton.setForeground(Color.WHITE);
        
        Font buttonFont = submitButton.getFont().deriveFont(14.2f);
        submitButton.setFont(buttonFont);
       // submitButton.setEnabled(false);
        Color c = Color.decode("#00AE0E");
       submitButton.setBackground(c);
        submitButton.setForeground(Color.WHITE);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        
        
    }
    public void styleRadioButtons(JRadioButton option)
    {
        Font optionsFont = new Font("Segoe UI", Font.ROMAN_BASELINE,14);
        option.setFont(optionsFont);
        option.setBackground(Color.WHITE);
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 40, 10, 20);
        option.setBorder(paddingBorder);
      //  questionArea.setBorder(paddingBorder);
        
    }
}
