package util;

import model.Admin;
import model.Student;

public class SessionManager {

    // Private static instance variable
    private static SessionManager instance;

    // Private constructor to prevent instantiation from outside
    private SessionManager() {
        // Initialization code here
    }

    // Public method to get the instance
    public static SessionManager getInstance() {
        // Lazy initialization: create the instance if it doesn't exist
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Variables to store logged-in user information
    private Admin loggedInAdmin;
    private Student loggedInStudent;

    // Method to set the logged-in Admin
    public void setLoggedInAdmin(Admin admin) {
        this.loggedInAdmin = admin;
        this.loggedInStudent = null; // Reset student if an admin logs in
    }

    public Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public void setLoggedInStudent(Student student) {
        this.loggedInStudent = student;
        this.loggedInAdmin = null; // Reset admin if a student logs in
    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    // Method to check if any user is logged in
    public boolean isLoggedIn() {
        return loggedInAdmin != null || loggedInStudent != null;
    }

    public void logout() {
        loggedInAdmin = null;
        loggedInStudent = null;
    }


}
