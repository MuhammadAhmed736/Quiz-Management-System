package gui;

import dao.CourseDAO;
import dao.StudentDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;

import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import model.Course;
import model.Student;
import util.InputValidator;
import util.RoundedPanel;
import util.SessionManager;

public class StudentPanel extends JPanel {
    
    SessionManager loggedInUser ; 
    Student currentStudent;
    private MainFrame mainFrame;
    
    
    private Image backgroundImage;
    InputValidator regex;
    private JButton myProfileBtn;
    private JButton quizBtn;
    private JButton coursesBtn;
    private JButton QuizListBtn;
    private JButton startQuizbtn;
    private JButton logoutBtn;
   
    int tabley = 0;
    private Boolean coursesDisplayed;
    private RoundedPanel profilePanel;
    private JPanel addedPanel;
   
     JLabel enrolledCourses;
    List<Course> coursesEnrolled ;
    JLabel username;
    JLabel email;
    JLabel regno;
    JLabel bglabel;
    public StudentPanel(MainFrame mainFrame) {
        
       
        this.mainFrame = mainFrame;
       mainFrame.getContentPane().removeAll();
       backgroundImage=null;
        repaint();
        mainFrame.repaint();
        
        regex = new InputValidator();
        loggedInUser =SessionManager.getInstance();
        currentStudent = loggedInUser.getLoggedInStudent();
      //  backgroundImage = new ImageIcon("C:\\Users\\askha\\Videos\\the 4400\\student panel.jpg").getImage();
        bglabel = new JLabel(" ok");
        username = new JLabel(currentStudent.getFirstName()+" " + currentStudent.getLastName());
        email = new JLabel(currentStudent.getEmailAddress());
        regno = new JLabel("21-ARID-735");
        myProfileBtn = createStyledButton("MY PROFILE");
      //  quizBtn = createStyledButton("QUIZZES");
        logoutBtn = createStyledButton("Sign Out");
        coursesBtn = createStyledButton("COURSES");
        QuizListBtn = createStyledButton("Quiz List");
        
        startQuizbtn = new JButton("START QUIZ");
         coursesDisplayed = false;
         enrolledCourses = new JLabel("Enrolled Courses : ");
     
        setButtonBounds();
      //  int tabley=0;
        mainFrame.add(startQuizbtn);
   
        setLabelBounds();
             mainFrame.add(bglabel);
        addClickListeners();
       setLayout(null);
       displayCourses();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        Font largerFont = button.getFont().deriveFont(14.2f);
        Color bgColor = new Color(0, 0, 0, 0);
        button.setFont(largerFont);
        button.setBackground(bgColor);
        button.setOpaque(false);
        button.setForeground(Color.CYAN);
         Border customBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

            // Set the custom border for the button
            button.setBorder(customBorder);
        mainFrame.add(button);
        
        return button;
    }

    private void setButtonBounds() {
        myProfileBtn.setBounds(3, 50, 237, 40);
        QuizListBtn.setBounds(3, 90, 237, 40);
        coursesBtn.setBounds(3, 130, 237, 40);
        logoutBtn.setBounds(3, 170, 237, 40);
        startQuizbtn.setBounds(300,10,120,37);
        startQuizbtn.setBackground(Color.WHITE);
    }
    public void setLabelBounds()
    {
        bglabel.setOpaque(true);
        bglabel.setBounds(241, 00, 700, 60);
        username.setBounds(700,10,150,25);
        email.setBounds(705,25,150,25);
        regno.setBounds(700,49,150,25);
        Font customFont = email.getFont().deriveFont( 11f); 
        bglabel.setBackground(Color.DARK_GRAY);
        email.setFont(customFont);
        username.setForeground(Color.WHITE);
     //   Color customColor = Color.decode("#45494C"); 
        Color customColor = Color.decode("#45494C");
        email.setForeground(Color.WHITE);
        regno.setForeground(Color.WHITE);
       // mainFrame.add(bglabel);
        mainFrame.add(username);
        mainFrame.add(email);
       // add(regno);
    }

    private void addClickListeners() {
        myProfileBtn.addActionListener(e -> displayProfile());
        startQuizbtn.addActionListener(e->displayQuizzes());
       // quizBtn.addActionListener(e -> displayQuiz());
        logoutBtn.addActionListener(e->signOut());
        coursesBtn.addActionListener(e -> displayCourses());
        QuizListBtn.addActionListener(e ->displayQuizzes());
       
    }
    public void displayQuiz()
    {
     
        
        revalidate();
        mainFrame.repaint();
        new QuizListPanel(mainFrame);
        mainFrame.repaint();
    }
         public void displayQuizzes()
     {
         removePanel();
          revalidate();
           mainFrame.repaint();
         QuizListPanel quizlistpanel = new QuizListPanel(mainFrame);
         JPanel quizpanel = quizlistpanel.getPanel();
         addedPanel = quizpanel;
         mainFrame.revalidate();
         mainFrame.repaint();
   
     }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     
     //    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
         
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      //  g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this), this);
        
        Color c = Color.decode("#0FF783");
        g.setColor(Color.DARK_GRAY);
        g.fillRect(241, 00, 700, 60);

    }

    private void displayProfile() {

        removePanel();
 
        JLabel emailLabel = new JLabel("Email : ");
        JLabel passLabel = new JLabel("Passsword : ");
        JLabel regnoLabel = new JLabel("Roll No : ");
        JLabel firstName = new JLabel("First Name : ");
        JLabel lastName = new JLabel("Second Name : ");
        JButton saveButton = new JButton("Save Profile");
        
        JTextField email_tv = new JTextField();
        JTextField pass_tv = new JTextField();
        JTextField regno_tv = new JTextField();
        JTextField firstname_tv = new JTextField();
        JTextField lastname_tv = new JTextField();
        
        email_tv.setText(currentStudent.getEmailAddress());
        pass_tv.setText(currentStudent.getPassword());
        regno_tv.setText(currentStudent.getStudentId());
        regno_tv.setEditable(false);
        firstname_tv.setText(currentStudent.getFirstName());
        lastname_tv.setText(currentStudent.getLastName());
        
        
        emailLabel.setBounds(50,20,120,25);
        email_tv.setBounds(110,20,160,25);
        
        passLabel.setBounds(320,20,120,25);
        pass_tv.setBounds(410,20,160,25);
        
        firstName.setBounds(30,70,120,25);
        firstname_tv.setBounds(110,70,160,25);
        
        lastName.setBounds(310,70,120,25);
        lastname_tv.setBounds(410,70,160,25);
        
        regnoLabel.setBounds(50,120,120,25);
        regno_tv.setBounds(110,120,160,25);
      
        saveButton.setBounds(240,160,150,35);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        
         saveButton.addActionListener(new ActionListener() {
             
            
            public void actionPerformed(ActionEvent e) {
              
                 String emailaddress = email_tv.getText().trim();
                String password = pass_tv.getText().trim();
                String regNumber = regno_tv.getText().trim();
                String firstname = firstname_tv.getText().trim();
                String lastname = lastname_tv.getText().trim();
              
               List<String> errors = regex.validateInputs("student",emailaddress, password, regNumber, firstname, lastname);

                 if (!errors.isEmpty()) 
                {
                    String errorMessage = String.join("\n", errors);
                    JOptionPane.showMessageDialog(mainFrame, errorMessage, "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                         Student s = new Student(firstname,lastname,emailaddress,password,regNumber);
                        StudentDAO stddao = new StudentDAO();
                        if(stddao.updateStudent(s))
                        {
                        
                        loggedInUser.setLoggedInStudent(s);
                        currentStudent = s;
                        }
                      
                    }
            }
         });
        
       profilePanel = new RoundedPanel(25,25);
        profilePanel.setBackground(Color.WHITE);

    // Set the bounds for the profilePanel
    profilePanel.setBounds(270, 80, 590, 217);
        profilePanel.add(emailLabel);
        profilePanel.add(email_tv);
        profilePanel.add(passLabel);
        profilePanel.add(pass_tv);
        profilePanel.add(regnoLabel);
        profilePanel.add(regno_tv);
        profilePanel.add(firstName);
        profilePanel.add(firstname_tv);
        profilePanel.add(lastName);
        profilePanel.add(lastname_tv);
        profilePanel.add(saveButton);
        mainFrame.add(profilePanel);
        addedPanel = profilePanel;
        profilePanel.setLayout(null);
       mainFrame.revalidate();
       mainFrame.repaint();
        
    }
    public void setlabelStyling(JLabel label)
    {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
     //   label.setOpaque(true);
    }
     public void displayCourses()
    {
 
        removePanel();
      
        
        JPanel coursesPanel = new JPanel();
        
        JLabel enrollCourseLabel = new JLabel("Enroll new Course : ");
        JButton enrollButton = new JButton ("Enroll");
        
           CourseDAO coursedao = new CourseDAO();
        List<Course> availableCourses = coursedao.getCourses();
        coursesEnrolled = coursedao.getEnrolledCourses(currentStudent.getStudentId());
        String[] courseTitles = availableCourses.stream()
        .map(Course::getCourseTitle) // Assuming a getCourseTitle() method in Course class
        .toArray(String[]::new);
   
         
        
             JComboBox<String>  coursesdropdown = new JComboBox<> (courseTitles);
              coursesdropdown.setBounds(160,25,160,30);
             coursesdropdown.setSelectedIndex(0);
 
            
       Font largerFont = enrollCourseLabel.getFont().deriveFont(13.5f);
       enrollCourseLabel.setBounds(30,25,180,30);
      enrollCourseLabel.setFont(largerFont);
       enrollButton.setBounds(360,22,120,35);
       enrollButton.setBackground(Color.DARK_GRAY);
       enrollButton.setForeground(Color.WHITE);
        enrolledCourses = new JLabel("Enrolled Courses : ");
        JLabel courseid = new JLabel("Course ID");
        JLabel courseTitle = new JLabel("Course Title");
        JLabel courseInstructor = new JLabel("Instructor");
        
        Color c = Color.decode("#F2F2F7");
        courseid.setOpaque(true);
        courseid.setBackground(c);
        courseTitle.setOpaque(true);
        courseTitle.setBackground(c);
        courseInstructor.setOpaque(true);
        courseInstructor.setBackground(c);
        enrolledCourses.setBounds(20,65,150,30);
        
        enrolledCourses.setFont(largerFont);
        int x = 20,width=195,height=30; 
        courseid.setBounds(x,105,width,height);
        courseTitle.setBounds(x+195,105,width,height);
        courseInstructor.setBounds(x+390,105,width,height);
        setlabelStyling(courseid);
        setlabelStyling(courseTitle);
        setlabelStyling(courseInstructor);
        
        
        enrollButton.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
                 String selectedCourseTitle = (String) coursesdropdown.getSelectedItem();
                 if(selectedCourseTitle!=null)
                 {
                     
                     if(coursedao.enrollCourse(selectedCourseTitle,currentStudent.getStudentId()))
                     {
                          coursesEnrolled = coursedao.getEnrolledCourses(currentStudent.getStudentId());

                        displayCourses();

                       
                     }
                 }
            }
        });
            
        coursesPanel.add(enrollCourseLabel);
        coursesPanel.add(enrollButton);
        
        coursesPanel.add(courseid);
        coursesPanel.add(courseTitle);
        coursesPanel.add(courseInstructor);

        
      int y = 135;
        for (Course course : coursesEnrolled) {
          
            JLabel courseIdLabel = new JLabel(course.getCourseId());
            JLabel courseTitleLabel = new JLabel(course.getCourseTitle());
            JLabel courseInstructorLabel = new JLabel(course.getInstructor());
            courseIdLabel.setBounds(x,y,width,height);
            courseTitleLabel.setBounds(x+195,y,width,height);
            setlabelStyling(courseIdLabel);
            setlabelStyling(courseTitleLabel);
            setlabelStyling(courseInstructorLabel);
            
           // courseIdLabel.setBackground(Color.WHITE);
            courseInstructorLabel.setBounds(x+390,y,width,height);
            coursesPanel.add(courseIdLabel);
            coursesPanel.add(courseTitleLabel);
            coursesPanel.add(courseInstructorLabel);
            y = y+30;
        }
        tabley=y;
         
      coursesPanel.setBounds(260,130,620,y+320);
      coursesPanel.setBackground(Color.WHITE);

        coursesPanel.add(coursesdropdown);
        coursesPanel.add(enrolledCourses);
       coursesPanel.setLayout(null);
       mainFrame.add(coursesPanel);
       
        
        addedPanel = coursesPanel;
         coursesDisplayed = true;
       mainFrame.repaint();
        
    }


   public void removePanel()
   {
       if(addedPanel!=null)
       {
           mainFrame.remove(addedPanel);
           mainFrame.repaint();
           addedPanel = null;
       }
       if(enrolledCourses!=null)
       {
           mainFrame.remove(enrolledCourses);
           enrolledCourses = null;
           mainFrame.repaint();
       }
   }
   public void signOut()
   {
 
       SessionManager session = SessionManager.getInstance();
       session.logout();
       loggedInUser=null;
       currentStudent= null;
    LoginPanel loginPanel = new LoginPanel(mainFrame);

   }
}
