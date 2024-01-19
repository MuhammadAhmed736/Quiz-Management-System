package model;

import java.util.ArrayList;
import java.util.List;


public class Admin {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String adminid;
    
    
    public Admin(String fname,String lastname,String email,String pass,String adminid)
    {
        firstName = fname;
        lastName = lastname;
        emailAddress = email;
        password = pass;
        this.adminid = adminid;
    
    }

    public String getAdminId()
    {
        return this.adminid;
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
