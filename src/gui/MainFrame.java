package gui;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import util.InputValidator;

public class MainFrame extends JFrame{
    
    
    private SignUpPanel loginPanel;
  
    private StudentPanel studentPanel;

    public MainFrame() {
        // Set up the main frame properties
        setTitle("Quiz Management System");
       
        JPanel contentPane = new JPanel();
      
        setContentPane(contentPane);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        int swidth = (int) (screenSize.getWidth() * 0.7);
        int sheight = (int) (screenSize.getHeight() * 0.8);
         contentPane.setPreferredSize(new Dimension(swidth, sheight));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        setVisible(true);   
        showSignUpPanel();
    }

    public void showSignUpPanel() {
       
        getContentPane().removeAll();
        setContentPane(new SignUpPanel(this));

        revalidate();
         repaint();
  
    }
    public void showLoginPanel() {
       

        getContentPane().removeAll();
      setContentPane(new LoginPanel(this));

        revalidate();
         repaint();
  
    }


    public void showStudentPanel() {
       
        getContentPane().removeAll();
        setContentPane(new StudentPanel(this));

        revalidate();
        repaint();
   
    }
    public void showAdminPanel()
    {
        getContentPane().removeAll();
        
        setContentPane(new AdminPanel(this));

        revalidate();
         repaint();
       
    }

       
}