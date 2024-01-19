package quizmanagementsystem;

import dao.DatabaseConnection;
import dao.QuizDAO;
import gui.MainFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class QuizManagementSystem {

    static MainFrame mainFrame;
    public static void main(String[] args) {
        
      initializeDatabase();
       SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
    private static void initializeDatabase() {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Database connection established.");

            // Additional initialization if needed
            // ...
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame,"Error initializing the database connection.");
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}



            


