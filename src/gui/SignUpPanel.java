package gui;
import dao.AdminDAO;
import dao.StudentDAO;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.Admin;
import model.Student;
import util.InputValidator;
import util.SessionManager;

public class SignUpPanel extends JPanel {

    private MainFrame mainFrame;
    private JButton loginButton;
    
 private JLabel role;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    
    private ButtonGroup roles;
    private JRadioButton student;
    private JRadioButton admin;
    private JButton signupButton;
    private JTextField emailField;
    private JTextField passField;
    private JTextField regNo;
    private JTextField firstName;
    private JTextField lastName;
    private Image backgroundImage;
    String selectedrole = "student";
    InputValidator regex;

    public SignUpPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Load the background image
        backgroundImage = new ImageIcon("C:\\Users\\askha\\Videos\\the 4400\\laptop.jpg").getImage();
        regex = new InputValidator();
        label1 = new JLabel("Email Address");
        label2 = new JLabel("Password");
        label3 = new JLabel("First Name");
        label4 = new JLabel("Last Name");
        label5 = new JLabel("CNIC");
        label6 = new JLabel("Registration Number");
        role = new JLabel("Select your Role: ");
        roles = new ButtonGroup();
        student = new JRadioButton("Student");
        
        admin = new JRadioButton("Admin");
        emailField = new JTextField();
        passField = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();
       
        regNo = new JTextField();
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        
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
        add(label3);
        add(firstName);
        add(label4);
        add(lastName);
        add(label5);
       
        add(label6);
        add(regNo);
        add(role);
        add(student);
        
        add(admin);
        add(loginButton);
        add(signupButton);
        
        student.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedrole = "student";
                    label6.setText("Registration Number:");
                }
            }
        });

       

        admin.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedrole = "admin";
                    label6.setText("Admin ID:");
                }
            }
        });
        signupButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = passField.getText().trim();
                String regNumber = regNo.getText().trim();
                String firstname = firstName.getText().trim();
                String lastname = lastName.getText().trim();
               // Boolean inputsAreValid=validateInputs(email,password,regNumber,firstname,lastname);
               List<String> errors = regex.validateInputs(selectedrole,email, password, regNumber, firstname, lastname);

                 if (!errors.isEmpty()) 
                {
                    String errorMessage = String.join("\n", errors);
                    JOptionPane.showMessageDialog(mainFrame, errorMessage, "Input Validation Error", JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                    
                    if(selectedrole.equals("student"))
                    {
                        
                         Student s = new Student(firstname,lastname,email,password,regNumber);
                        StudentDAO stddao = new StudentDAO();
                        stddao.saveStudent(s);
                       
                         mainFrame.getContentPane().removeAll();

                     repaint();

                        new LoginPanel(mainFrame);
                    }
                    else  if(selectedrole.equals("admin"))
                    {
                         Admin admin = new Admin(firstname,lastname,email,password,regNumber);
                     
                        AdminDAO admindao = new AdminDAO();
                        admindao.saveAdmin(admin);
                       
                         mainFrame.getContentPane().removeAll();

                     revalidate();
                     repaint();
                     // backgroundImage = new ImageIcon("C:\\Users\\askha\\Videos\\the 4400\\student panel.jpg").getImage();
 
                  
                        new LoginPanel(mainFrame);
                    }
                    }
                }
            
        });

        loginButton.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLoginPanel();
                
                
            }
        });
        setLayout(null);
    }
    public void setBounds(int swidth,int sheight)
    {
      
        int hCentre = swidth/2;
        int vCentre = sheight/2;
        int x = hCentre-230;
        int yStart = 150;
        int yLabel = yStart-22;
        int width = 250;
        int height = 30;
        
        label1.setBounds(x,yLabel,width,20);
        label2.setBounds(x,yLabel+60,width,20);
        label3.setBounds(x,yLabel+120,width,20);
        label4.setBounds(x,yLabel+180,width,20);
        label6.setBounds(x,yLabel+240,width,20);
       // label6.setBounds(x,yLabel+300,width,20);
        role.setBounds(x-60,yLabel-60,120,30);
        
        role.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);
        label5.setForeground(Color.WHITE);
        label6.setForeground(Color.WHITE);
        emailField.setBounds(x,yStart,width,height);
        //emailField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        emailField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        passField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        firstName.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        lastName.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        regNo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
       
        //emailField.setBackground(Color.CYAN);
        passField.setBounds(x,yStart+60,width,height);
        firstName.setBounds(x,yStart+120,width,height);
        lastName.setBounds(x,yStart+180,width,height);
        regNo.setBounds(x,yStart+240,width,height);
        //regNo.setBounds(x,yStart+300,width,height);
        student.setBounds(x+60, yLabel-56, 75, 25);
        admin.setBounds(x+138, yLabel-56, 70, 25);
        
        
        signupButton.setBounds(x+10,yStart+300,100,30);
        loginButton.setBounds(x+140,yStart+300,100,30);
        
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
         
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
      //g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(this), backgroundImage.getHeight(this), this);
    }
  
}
