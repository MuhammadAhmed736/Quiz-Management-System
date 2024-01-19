package gui;

import dao.CourseDAO;
import dao.QuizDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Course;
import model.Quiz;
import model.Student;
import util.SessionManager;

public class QuizListPanel extends JPanel{
    
    public MainFrame mainFrame;
    JPanel quizListpanel;
    JComboBox<String> courses;
    String[] courseTitles;
    JLabel coursesLabel;
    JLabel quizTitle;
    JLabel quizNo;
    JLabel NumberOfQuestions;
    JLabel timeDuration;
    JLabel startQuizLabel;
    List<Course> enrolledCourses;
    List<Quiz> quizzeslist;
    CourseDAO coursedao;
    Student currentStudent;
    List<JLabel> dynamicLabels;
    List<JButton> dynamicButton;
   
    
    public QuizListPanel(MainFrame mainframe)
    {
        this.mainFrame = mainframe;
        
        quizListpanel = new JPanel();
        coursesLabel = new JLabel("Select Course : ");
        quizTitle = new JLabel("Quiz Topic");
        NumberOfQuestions = new JLabel("Questions");
        coursedao = new CourseDAO();
        timeDuration = new JLabel("Time Duration");
        startQuizLabel = new JLabel("Start Quiz");
        quizNo = new JLabel("Quiz #");
        dynamicLabels = new ArrayList<JLabel>();
        dynamicButton = new ArrayList<JButton>();
       
       SessionManager sm = SessionManager.getInstance();
       currentStudent = sm.getLoggedInStudent();
       
        enrolledCourses =coursedao.getEnrolledCourses(currentStudent.getStudentId());
        courseTitles = new String[enrolledCourses.size()];
        int i = 0;  // Index for the courseTitles array
        for (Course c : enrolledCourses) {
            courseTitles[i] = c.getCourseTitle();  // Extract course title and add it to the array
            i++;  // Increment the index for the next title
          }
   
        currentStudent = SessionManager.getInstance().getLoggedInStudent();
        
        courses = new JComboBox<> (courseTitles);
       
       
        quizzeslist = new ArrayList<Quiz>();
        //setBounds();
       quizListpanel.add(coursesLabel);
       quizListpanel.add(quizTitle);
       quizListpanel.add(NumberOfQuestions);
       quizListpanel.add(startQuizLabel);
       quizListpanel.add(courses);
       quizListpanel.add(quizNo);
       quizListpanel.add(timeDuration);
       quizListpanel.add(courses);
       quizListpanel.setLayout(null);
       quizListpanel.setVisible(true);
       quizListpanel.setBackground(Color.WHITE);
       mainFrame.add(quizListpanel);
       
       displayQuizDetails();
    }

    public void displayQuizDetails()
    {
       
        courses.setBounds(150,15,150,30);
        coursesLabel.setBounds(25,15,130,30);
       
        int x = 20,width=195,height=40;
        quizNo.setBounds(x,70,width-140,height);
        quizTitle.setBounds(x+55,70,width,height);
        NumberOfQuestions.setBounds(x+250,70,width-110,height);
        timeDuration.setBounds(x+335,70,width-100,height);
        startQuizLabel.setBounds(450,70,140,height);
        setlabelStyling(quizNo);
        setlabelStyling(quizTitle);
        setlabelStyling(NumberOfQuestions);
        setlabelStyling(timeDuration);
        setlabelStyling(startQuizLabel);
         
        
        int y2=110;
        
        courses.addActionListener(new ActionListener() {
             
            
            public void actionPerformed(ActionEvent e) {
                int y = y2;
                if(dynamicLabels!=null)
                {
                    for (JLabel label : dynamicLabels)
                    {
                        quizListpanel.remove(label);
                       
                        quizListpanel.repaint();
                    }
                }
                if (dynamicButton!=null)
                {
                    for (JButton button : dynamicButton)
                    {
                        quizListpanel.remove(button);
                      
                        quizListpanel.repaint();
                        
                    }
                    y=y2;
                   
                }
                quizListpanel.repaint();
                 String selectedCourseTitle;  selectedCourseTitle = (String) courses.getSelectedItem();
                  QuizDAO quizdao = new QuizDAO();
                  Course SelectedCourse = coursedao.getCourseByCourseTitle(selectedCourseTitle);
                  quizzeslist=quizdao.getQuizzes(SelectedCourse.getCourseId());

         if(quizzeslist!=null)
         {
             for (Quiz q : quizzeslist)
             {
               
                String s=String.valueOf(q.getQuizId());
               JLabel quizNoLabel= new JLabel(s);
                
                JLabel quizTitleLabel = new JLabel(String.valueOf(q.getTitle()));
               JLabel QuizDurationLabel = new JLabel(String.valueOf(q.getTimeDuration()));
               JLabel questionsLabel = new JLabel(String.valueOf(q.getNumberofQuestions()));

                JButton startQuiz = new JButton("Start Quiz");

                quizNoLabel.setBounds(x,y,width-140,height-4); 
                quizTitleLabel.setBounds(x+55,y,width,height-4); 
                questionsLabel.setBounds(x+250,y,width-110,height-4); 
                QuizDurationLabel.setBounds(x+335,y,width-100,height-4);
                setlabelStyling(quizNoLabel);
                setlabelStyling(quizTitleLabel);
                setlabelStyling(QuizDurationLabel);
                setlabelStyling(questionsLabel);
                dynamicButton.add(createstartQuizButton(450,y,140,height-4,q,SelectedCourse));
               dynamicLabels.add(quizNoLabel);
               dynamicLabels.add(quizTitleLabel);
               dynamicLabels.add(QuizDurationLabel);
               dynamicLabels.add(questionsLabel);
               //dynamicLabels.add(createStartQuizButton);
                quizListpanel.add(quizNoLabel);
                quizListpanel.add(quizTitleLabel);
                quizListpanel.add(QuizDurationLabel);
                quizListpanel.add(questionsLabel); 
                quizListpanel.repaint();
                 y = y+40;
              
                }
                
             }
         else 
         {
             JOptionPane.showMessageDialog(mainFrame, "No Quiz of Selected Course is Available");
         }
                 
            }
        });

         quizListpanel.setBounds(260,130,620,1000);
         mainFrame.add(quizListpanel);
    }
     public void setlabelStyling(JLabel label)
    {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    
    }
    public JButton createstartQuizButton(int x,int y,int width,int height,Quiz quiz,Course course)
     {
         JButton startButton = new JButton("Start Quiz");
         startButton.setBounds(x,y,width,height);
         
         startButton.setBackground(Color.CYAN);
         startButton.setForeground(Color.BLACK);
         quizListpanel.add(startButton);
         startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click here
               mainFrame.remove(quizListpanel);
               mainFrame.revalidate();
               mainFrame.repaint();
               QuizPanel qp = new QuizPanel(mainFrame,quiz,course);
               mainFrame.add(qp);
               mainFrame.revalidate();
              mainFrame.repaint();

            }
           
        });
         return startButton;
     }

    public JPanel getPanel()
    {
        return quizListpanel;
    }

}
