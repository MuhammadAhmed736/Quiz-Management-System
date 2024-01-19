package gui;

import dao.AdminDAO;
import dao.CourseDAO;
import dao.QuizDAO;
import dao.StudentDAO;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Admin;
import model.Course;
import model.Quiz;
import model.Student;
import util.InputValidator;

import util.RoundedPanel;
import util.SessionManager;

public class AdminPanel extends JPanel {

    private MainFrame mainFrame;
    private JPanel adminPanel;
    private JPanel menuPanel;
    private JPanel topbar;
    private RoundedPanel contentPanel;
    
    private JButton manageCourses;
    private JButton manageQuizzes;
    private JButton manageQuestions;
    private JButton logoutButton;
    private JButton manageAdmins;
    private JButton updateProfile;
    private JLabel adminpanel;
    private JLabel selectedMenuLabel;
    List<Quiz>quizzesList;
    String selectedcourse="";
    InputValidator regex;
    SessionManager loggedInUser ; 
        Admin currentAdmin;
        
    int width = 0, height = 0;
    int menuButtonHeight;
    int menuButtonWidth; 
    
 
        List<Course> courseList;

    public AdminPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        adminPanel = new JPanel();
        menuPanel = new JPanel();
        topbar = new JPanel();
        contentPanel = new RoundedPanel(25,25);
        
        loggedInUser =SessionManager.getInstance();
        currentAdmin = loggedInUser.getLoggedInAdmin();
        
        selectedMenuLabel = new JLabel("");
        updateProfile = new JButton("Update Profile");
        manageCourses = new JButton("Manage Courses");
        manageQuizzes = new JButton("Manage Quizzes");
        manageQuestions = new JButton("Add Question");
        logoutButton = new JButton("Log Out");
        manageAdmins = new JButton("Manage Admins");
        adminpanel = new JLabel("----ADMIN PANEL----");
        
        quizzesList = new ArrayList<Quiz>();
        regex = new InputValidator();
        setBounds();
        styleComponents();
        addActionListeners();
        addComponents();
        updateProfile();
        
    }

    private void addComponents() {
        menuPanel.add(updateProfile);
        menuPanel.add(manageCourses);
        menuPanel.add(manageQuizzes);
        menuPanel.add(manageQuestions);
        menuPanel.add(logoutButton);
       // menuPanel.add(manageAdmins);
       // menuPanel.add(adminpanel);
        
        menuPanel.setLayout(null);
        topbar.setLayout(null);
        contentPanel.setLayout(null);
        topbar.add(adminpanel);
       // adminPanel.add(menuPanel);
        adminPanel.add(topbar);
       // adminPanel.add(contentPanel);
        mainFrame.add(menuPanel);
        mainFrame.add(selectedMenuLabel);
        mainFrame.add(contentPanel);
       
        adminPanel.setLayout(null);
        setLayout(null);
    }

    private void setBounds() {
        width = mainFrame.getWidth();
        height = mainFrame.getHeight();
        menuButtonHeight = 45;
        menuButtonWidth = (width / 5)+((width/5 +50)/4 ); 

        menuPanel.setBounds(0, 0, menuButtonWidth, height);
        adminPanel.setBounds(0, 0, width, height);
        topbar.setBounds(0,0,width,45);
      //  adminPanel.setBackground(Color.WHITE);
      menuPanel.setOpaque(false);
      selectedMenuLabel.setBounds(480,30,200,menuButtonHeight);
        contentPanel.setBounds(menuButtonWidth+15,90,width-menuButtonWidth-45,280);

        int x = 0;
        int y = 50;
        
        adminpanel.setBounds((width/2),6,120,30);
        updateProfile.setBounds(x, y, menuButtonWidth, menuButtonHeight);
        manageCourses.setBounds(x, y + menuButtonHeight, menuButtonWidth, menuButtonHeight);
        manageQuizzes.setBounds(x, y + 2 * menuButtonHeight, menuButtonWidth, menuButtonHeight);
        manageQuestions.setBounds(x, y + 3 * menuButtonHeight, menuButtonWidth, menuButtonHeight);
        logoutButton.setBounds(x, y + 4 * menuButtonHeight, menuButtonWidth, menuButtonHeight);
        manageAdmins.setBounds(x, y + 5 * menuButtonHeight, menuButtonWidth, menuButtonHeight);
    }
    
    private void styleComponents() 
    {
       
      
         Font largerFont = new Font(selectedMenuLabel.getFont().toString(), Font.CENTER_BASELINE, 17);
           selectedMenuLabel.setFont(largerFont);
      //  menuPanel.setBackground(Color.WHITE);
        contentPanel.setBackground(Color.WHITE);
      //  topbar.setBackground(Color.BLACK);
        //adminpanel.setOpaque(false);
       // topbar.setBackground(Color.CYAN);
    }
    public void addActionListeners()
    {
      
        updateProfile.addActionListener(e -> updateProfile());
        manageCourses.addActionListener(e->manageCourses());
        manageQuizzes.addActionListener(e -> manageQuizzes());
        manageQuestions.addActionListener(e -> manageQuestions());
        logoutButton.addActionListener(e -> logout());

        
    }
    public void updateProfile()
    {
        contentPanel.removeAll();
        contentPanel.repaint();
        
        
        selectedMenuLabel.setText("UPDATE  PROFILE");
        JLabel emailLabel = new JLabel("Email : ");
        JLabel passLabel = new JLabel("Passsword : ");
        JLabel adminIdLabel = new JLabel("Admin Id : ");
        JLabel firstName = new JLabel("First Name : ");
        JLabel lastName = new JLabel("Second Name : ");
        JButton saveButton = new JButton("Save Profile");
        
        JTextField email_tv = new JTextField();
        JTextField pass_tv = new JTextField();
        JTextField adminId_tv = new JTextField();
        JTextField firstname_tv = new JTextField();
        JTextField lastname_tv = new JTextField();
        
        emailLabel.setBounds(50,50,120,25);
        email_tv.setBounds(110,50,160,25);
        
        passLabel.setBounds(320,50,120,25);
        pass_tv.setBounds(410,50,160,25);
        
        firstName.setBounds(35,100,120,25);
        firstname_tv.setBounds(110,100,160,25);
        
        lastName.setBounds(310,100,120,25);
        lastname_tv.setBounds(410,100,160,25);
        
        adminIdLabel.setBounds(40,150,120,25);
        adminId_tv.setBounds(110,150,160,25);
      
       email_tv.setText(currentAdmin.getEmailAddress());
        pass_tv.setText(currentAdmin.getPassword());
        adminId_tv.setText(currentAdmin.getAdminId());
        adminId_tv.setEditable(false);
        firstname_tv.setText(currentAdmin.getFirstName());
        lastname_tv.setText(currentAdmin.getLastName());
        saveButton.setBounds(240,210,150,35);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        
         saveButton.addActionListener(new ActionListener() {
             
            
            public void actionPerformed(ActionEvent e) {
              
                 String emailaddress = email_tv.getText().trim();
                String password = pass_tv.getText().trim();
                String adminid = adminId_tv.getText().trim();
                String firstname = firstname_tv.getText().trim();
                String lastname = lastname_tv.getText().trim();
              
               List<String> errors = regex.validateInputs("admin",emailaddress, password, adminid, firstname, lastname);

                 if (!errors.isEmpty()) 
                {
                    String errorMessage = String.join("\n", errors);
                    JOptionPane.showMessageDialog(mainFrame, errorMessage, "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                         Admin a = new Admin(firstname,lastname,emailaddress,password,adminid);
                        AdminDAO admindao = new AdminDAO();
                        if(admindao.updateAdmin(a))
                        {
                        
                        loggedInUser.setLoggedInAdmin(a);
                        currentAdmin = a;
                        }
                      
                    }
            }
         });
        
       
        contentPanel.add(emailLabel);
        contentPanel.add(email_tv);
        contentPanel.add(passLabel);
        contentPanel.add(pass_tv);
        contentPanel.add(adminIdLabel);
        contentPanel.add(adminId_tv);
        contentPanel.add(firstName);
        contentPanel.add(firstname_tv);
        contentPanel.add(lastName);
        contentPanel.add(lastname_tv);
        contentPanel.add(saveButton);
        revalidate();
        repaint();
        
        
    }
    public void manageCourses()
    {
        contentPanel.removeAll();
        contentPanel.repaint();
        
        contentPanel.setBounds(menuButtonWidth+15,90,width-menuButtonWidth-45,450);
        selectedMenuLabel.setText("MANAGE COURSES");
        int xstart=20,ystart=60;
        int labelwidth=150; int labelheight=40;
        
        JLabel addCourseLabel = new JLabel("Add New Course : ");
        JLabel courseid_label = new JLabel("Course Id : ");
        JLabel courseTitle_label = new JLabel("Title : ");
        JLabel courseInstructor_label = new JLabel("Instructor : ");
        JButton addCoursebutton = new JButton("Add Course");
        JTextField courseId_tf= new JTextField();
        JTextField courseTitle_tf= new JTextField();
        JTextField courseInstructor_tf= new JTextField();
        
        JLabel coursesLabel = new JLabel("Courses List");

        JLabel courseIdLabel = new JLabel("Courses Id");
        JLabel courseTitleLabel = new JLabel("Course Title");
        JLabel courseInstructorLabel = new JLabel("Course Instructor");
        JLabel deleteCoursesLabel = new JLabel("Delete Courses");
        
            setlabelStyling(courseIdLabel);
            setlabelStyling(courseTitleLabel);
            setlabelStyling(courseInstructorLabel);
            setlabelStyling(deleteCoursesLabel);
            
            xstart=30;
            
            addCourseLabel.setBounds(xstart,ystart-50,labelwidth,labelheight-10);
            courseTitle_label.setBounds(xstart+185,ystart-12,labelwidth-80,25);
            courseTitle_tf.setBounds(xstart+230,ystart-12,labelwidth,25);
            courseInstructor_label.setBounds(xstart+395,ystart-12,labelwidth,25);
            courseInstructor_tf.setBounds(xstart+465,ystart-12,labelwidth-20,25);
            courseid_label.setBounds(xstart,ystart-15,labelwidth-70,25);
            courseId_tf.setBounds(xstart+(labelwidth-80),ystart-12,labelwidth-50,25);

            addCoursebutton.setBounds(xstart+200,ystart+40,160,35);
            addCoursebutton.setBackground(Color.DARK_GRAY);
            addCoursebutton.setForeground(Color.WHITE);
            addCoursebutton.addActionListener(new ActionListener() 
            {
             
                public void actionPerformed(ActionEvent e) 
                {
              
                    String courseid = courseId_tf.getText().trim();
                    String coursetitle = courseTitle_tf.getText().trim();
                    String instructor = courseInstructor_tf.getText().trim();
                    
                    List<String> errors = regex.validateCourseInputs(courseid,coursetitle, instructor );

                    if (!errors.isEmpty()) 
                    {
                        String errorMessage = String.join("\n", errors);
                        JOptionPane.showMessageDialog(mainFrame, errorMessage, "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    else 
                    {
                        Course coursetobeAdded = new Course(courseid,coursetitle,instructor);
                        if(addCourse(coursetobeAdded))
                        { 
                            courseList.add(coursetobeAdded);
                            JOptionPane.showMessageDialog(mainFrame, "Course Added Successfully!");
                            manageCourses();
                        }
                        else 
                        {
                             JOptionPane.showMessageDialog(null, "Unable to add Course.Course already exists",
                         "Course Not Added", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        
            });
            


            ystart += 50;
            coursesLabel.setBounds(xstart,ystart,labelwidth,labelheight);ystart+=40;
            courseIdLabel.setBounds(xstart,ystart,labelwidth-50,labelheight);  xstart+=(labelwidth-50);
            courseTitleLabel.setBounds(xstart,ystart,labelwidth+30,labelheight); xstart+=(labelwidth)+30;
            courseInstructorLabel.setBounds(xstart,ystart,labelwidth,labelheight); xstart+=labelwidth;
            deleteCoursesLabel.setBounds(xstart,ystart,labelwidth,labelheight);
   
       getCoursesList();     
       ystart+=40;
       xstart = 30;
       
        for (Course c : courseList)
        {
            labelheight = 35;
            JLabel courseid = new JLabel(c.getCourseId());
            JLabel coursetitle = new JLabel(c.getCourseTitle());
            JLabel courseinstructor = new JLabel(c.getInstructor());
            JButton deleteButton = new JButton("Delete Course");
            setlabelStyling(courseid);
            setlabelStyling(coursetitle);
            setlabelStyling(courseinstructor);
            
            courseid.setBounds(xstart,ystart,labelwidth-50,labelheight);  xstart+=(labelwidth-50);
            coursetitle.setBounds(xstart,ystart,labelwidth+30,labelheight); xstart+=(labelwidth)+30;
            courseinstructor.setBounds(xstart,ystart,labelwidth,labelheight); xstart+=labelwidth;
            deleteButton.setBounds(xstart,ystart,labelwidth,labelheight);
            deleteButton.addActionListener(e -> deleteCourse(c));
            
            contentPanel.add(courseid);
            contentPanel.add(coursetitle);
            contentPanel.add(courseinstructor);
            contentPanel.add(deleteButton);
            
            contentPanel.repaint();
            ystart+=35;
            xstart = 30;
        }
            addCoursebutton.addActionListener(e -> 
            {
                
            String course_Id = courseId_tf.getText();
            String course_Title = courseTitle_tf.getText();
            String course_Instructor = courseInstructor_tf.getText();
            Course course = new Course(course_Id,course_Title,course_Instructor);
            courseList.add(course);
            });
            contentPanel.add(addCourseLabel);
            contentPanel.add(courseid_label);
            contentPanel.add(courseTitle_label);
            contentPanel.add(courseInstructor_label);
            contentPanel.add(courseId_tf);
            contentPanel.add(courseTitle_tf);
            contentPanel.add(courseInstructor_tf);
            contentPanel.add(addCoursebutton);
            contentPanel.add(coursesLabel);
            contentPanel.add(courseIdLabel);
            contentPanel.add(courseTitleLabel);
            contentPanel.add(courseInstructorLabel);
            contentPanel.add(deleteCoursesLabel);
            

        
    }
    public void manageQuizzes()
    {
        
        
        contentPanel.removeAll();
        contentPanel.repaint();
        
        selectedMenuLabel.setBounds(480,20,200,45);
        contentPanel.setBounds(menuButtonWidth+15,65,width-menuButtonWidth-45,800);
        selectedMenuLabel.setText("Manage Quizzes");
        JComboBox<String> coursesList;
        List<Course> availableCourses;
        String[] courseTitles;

        CourseDAO coursedao = new CourseDAO();
        availableCourses =coursedao.getCourses();
        courseTitles = new String[availableCourses.size()];
        int i = 0;  
        for (Course c : availableCourses) {
            courseTitles[i] = c.getCourseTitle();
            i++;  
          }
        coursesList = new JComboBox<> (courseTitles);
        
        
        
        int x1=15,x2=160,x3=305,x4=450;
        int y1 = 85,y2=115;
        int width = 130;
        JLabel selectCourseLabel = new JLabel("Select Course");
        JLabel addQuizLabel = new JLabel("Add New Quiz : ");
        JLabel quizid_label = new JLabel("Quiz Id : ");
        JLabel quizTitle_label = new JLabel("Title : ");
        JLabel numberofquestions_label = new JLabel("Questions : ");
        JLabel CourseidLabel = new JLabel("Course Id");
       
        JButton addQuizButton = new JButton("Add Quiz");
        
        JTextField Quizid_textField = new JTextField();
      
        JTextField Quiztitle_textField = new JTextField();
        JTextField Courseid_textField = new JTextField();
        Courseid_textField.setEditable(false);
        JTextField Questions_textField = new JTextField();
        
      coursesList.setSelectedItem(courseTitles[0]);
       QuizDAO quizdao = new QuizDAO();
        
        
        
        coursesList.addActionListener(new ActionListener()
        {
         
           public void actionPerformed(ActionEvent e)
           {
                selectedcourse = (String) coursesList.getSelectedItem();
               Courseid_textField.setText( coursedao.getCourseByCourseTitle(selectedcourse).getCourseId());
           }          
        });
       addQuizButton.addActionListener(new ActionListener(){
       
           @Override
           public void actionPerformed(ActionEvent e)
           {
                CourseDAO coursedao = new CourseDAO();
               int quizId =  Integer.parseInt(Quizid_textField.getText());
               String quizTitle = Quiztitle_textField.getText();
               String courseId = Courseid_textField.getText();
               int numberOfquestions = Integer.parseInt(Questions_textField.getText());   
               
               Quiz q = new Quiz(quizId,quizTitle,numberOfquestions,5);
            //   String selectedCourseTitle = (String) coursesList.getSelectedItem();
             //  String Courseid = coursedao.getCourseByCourseTitle(selectedCourse).getCourseId();
               QuizDAO quizdao = new QuizDAO();
                 quizdao.addQuiz(q, courseId);
                  quizzesList =quizdao.getQuizzes(selectedcourse);
                  
                  
           }

       });
        coursesList.setBounds(200,25,150,30);
        selectCourseLabel.setBounds(70,25,100,30);
        addQuizLabel.setBounds(x1,55,width,30);
        
        quizid_label.setBounds(x1,y1,width,30);
        Quizid_textField.setBounds(x1,y2,width,30);
        quizTitle_label.setBounds(x3,y1,width,30);
        Quiztitle_textField.setBounds(x3,y2,width,30);
        numberofquestions_label.setBounds(x4,y1,width,30);
        Questions_textField.setBounds(x4,y2,width,30);
        CourseidLabel.setBounds(x2,y1,width,30);
        Courseid_textField.setBounds(x2,y2,width,30);
        
        addQuizButton.setBounds(220,y2+40,120,35);
        //addQuizLabel.setBounds(15,25,100,30);
        
        contentPanel.add(selectCourseLabel);
        contentPanel.add(coursesList);
     //   contentPanel.add(addQuizLabel);
        contentPanel.add(quizid_label);
        contentPanel.add(Quizid_textField);
        contentPanel.add(quizTitle_label);
        contentPanel.add( Quiztitle_textField);
        contentPanel.add(numberofquestions_label);
        contentPanel.add(Questions_textField);
        contentPanel.add(CourseidLabel);
        contentPanel.add(Courseid_textField);
        contentPanel.add(addQuizButton);
        contentPanel.repaint();
        
    }
    public void manageQuestions()
    {
       
        contentPanel.removeAll();
        contentPanel.repaint();
         selectedMenuLabel.setText("Manage Questions");
    }
    public void logout()
    {
        
        contentPanel.removeAll();
        contentPanel.repaint();
        selectedMenuLabel.setText("Log Out");
    }

    public void getCoursesList()
    {
        CourseDAO coursedao = new CourseDAO();
        courseList= coursedao.getCourses();
    }
    public void setlabelStyling(JLabel label)
    {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }
    public void deleteCourse(Course c)
    {
        courseList.remove(c);
        CourseDAO coursedao = new CourseDAO();
        coursedao.deleteCourse(c);
        manageCourses();
       // repaint();
    }
    public boolean addCourse(Course c)
    {
        CourseDAO coursedao = new CourseDAO();
        return (coursedao.addCourse(c));
        
    }
}

