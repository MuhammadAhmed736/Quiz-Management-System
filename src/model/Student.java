package model;

import java.util.ArrayList;
import java.util.List;


public class Student {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String rollno;
    private List<Course> enrolledCourses;
    
    public Student(String fname,String lastname,String email,String pass,String rollno)
    {
        firstName = fname;
        lastName = lastname;
        emailAddress = email;
        password = pass;
        this.rollno = rollno;
        enrolledCourses = new ArrayList<Course>();
    }
    public List<Course> getenrolledCourses()
    {
        return this.enrolledCourses;
    }
    public void enrollCourse()
    {
      
       
        
    }
    public String getStudentId()
    {
        return this.rollno;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    public String getPassword()
    {
        return this.password;
    }
    
}
