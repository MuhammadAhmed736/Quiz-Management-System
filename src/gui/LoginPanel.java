package gui;

import dao.AdminDAO;
import dao.StudentDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.*;

import model.Admin;
import model.Student;
import util.InputValidator;
import util.SessionManager;

public class LoginPanel  extends JPanel {

    private MainFrame mainFrame;
    private JButton loginButton;
    private JButton signupButton;
    
    private JLabel role;
    private JLabel label1;
    private JLabel label2;
    private JLabel forgotpassLabel;

    Image img;
    private ButtonGroup roles;
    private JRadioButton student;
   
    private JRadioButton admin;
    private JTextField emailField;
    private JTextField passField;
    String selectedrole = "student";
    private Image backgroundImage;
    InputValidator regex;
    
    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.getContentPane().removeAll();
        backgroundImage = new ImageIcon("Images/laptop.jpg").getImage();
        mainFrame.setContentPane(this);
      // backgroundImage=null;
       revalidate();
       repaint();
        
        regex = new InputValidator();
       
        

        label1 = new JLabel("Email Address");
        label2 = new JLabel("Password");
        forgotpassLabel = new JLabel("Forgot Password");
        role = new JLabel("Select your Role: ");
        roles = new ButtonGroup();
        student = new JRadioButton("Student");
        
        admin = new JRadioButton("Admin");
        emailField = new JTextField();
        passField = new JTextField();
        signupButton = new JButton("Sign up");
        loginButton = new JButton("Login");
        student.setSelected(true);
        roles.add(student);
        
        roles.add(admin);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        int swidth = (int) (screenSize.getWidth() * 0.7);
        int sheight = (int) (screenSize.getHeight() * 0.8);
        setBounds(swidth,sheight);
        add(label1);
        add(emailField);
        add(label2);
        add(passField);
        add(role);
        add(student);
        add(forgotpassLabel);
        add(admin);
        add(loginButton);
        add(signupButton);
        
        student.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedrole = "student";
                    
                }
            }
        });

       

        admin.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedrole = "admin";
                    
                }
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
             
            
            public void actionPerformed(ActionEvent e) {
              
                String email = emailField.getText().trim();
                String password = passField.getText().trim();
                List<String> errors = regex.validateInputs(selectedrole,email, password );

                 if (!errors.isEmpty()) 
                {
                    String errorMessage = String.join("\n", errors);
                    JOptionPane.showMessageDialog(mainFrame, errorMessage, "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                    if(selectedrole.equals("student"))
                    {
                        StudentDAO stddao = new StudentDAO();
                      Student student = stddao.validateStudentLogin(email, password);
                      if(student!=null)
                    {
                        SessionManager sessionManager = SessionManager.getInstance();
                        sessionManager.setLoggedInStudent(student);
                      
                      mainFrame.getContentPane().removeAll();
                      
                      backgroundImage = null;
                     revalidate();
                     repaint();
                      backgroundImage = new ImageIcon("Images/student panel.jpg").getImage();
 
                      repaint();
                      new StudentPanel(mainFrame);
                    }
                         else {
                             JOptionPane.showMessageDialog(mainFrame, "Student Not Found");
                            }
                    }
                    else if(selectedrole.equals("admin"))
                    {
                        
                        AdminDAO admindao = new AdminDAO();
                      Admin admin = admindao.validateAdminLogin(email, password);
                      if(admin!=null)
                    {
                        SessionManager sessionManager = SessionManager.getInstance();
                        sessionManager.setLoggedInAdmin(admin);
                         mainFrame.getContentPane().removeAll();
                         
                      
                      backgroundImage = null;
                     revalidate();
                     repaint();
                      backgroundImage = new ImageIcon("Images/admin bg.jpg").getImage();
 
                      repaint();
                        new AdminPanel(mainFrame);
                        
                    }
                         else {
                             JOptionPane.showMessageDialog(mainFrame, "Admin Not Found");
                            }
                    }
                    
                }

                }

        });
            
              /// 
        
        // Add action listener for the signup button
        signupButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
               
                mainFrame.showSignUpPanel();
            }
        });
        setLayout(null);
    }
    public void setBounds(int swidth,int sheight)
    {
        System.out.println(swidth+" "+sheight);
        int hCentre = swidth/2;
        int vCentre = sheight/2;
        int x = hCentre-230;
        int yStart = vCentre-150;
        int yLabel = yStart-22;
        int width = 250;
        int height = 30;
        
        label1.setBounds(x,yLabel+100,width,20);
        label2.setBounds(x,yLabel+160,width,20);
        role.setBounds(x-60,yLabel+40,120,30);
        
        role.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        emailField.setBounds(x,yStart+100,width,height);
        emailField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        passField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        passField.setBounds(x,yStart+160,width,height);
        forgotpassLabel.setBounds(x+5,yStart+190,120,height);
        forgotpassLabel.setForeground(Color.WHITE);
        
        student.setBounds(x+60, yLabel+40, 75, 25);
        admin.setBounds(x+138, yLabel+40, 70, 25);
        
        
        signupButton.setBounds(x+10,yStart+250,100,30);
        loginButton.setBounds(x+140,yStart+250,100,30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
       
    }
    public void removeImage ()
    {
        backgroundImage = null;
        repaint();
        mainFrame.repaint();
    }
}
