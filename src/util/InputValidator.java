package util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    
    private final Pattern emailPattern;
    private final Pattern passwordPattern;
    private final Pattern namePattern;
    private final Pattern regNoPattern;
    private final Pattern courseIdPattern;
    private final Pattern alphabetPattern;
    private final Pattern adminIdPattern;
    private final Pattern digitPattern;
    private final Pattern alphanumericPattern;
    
    public InputValidator() {
        
        emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]{1,25}@[a-zA-Z0-9.-]{1,10}\\.com$");
        passwordPattern = Pattern.compile("^[^\\s]{6,15}$");
        namePattern = Pattern.compile("^[a-zA-Z]+$");
        regNoPattern = Pattern.compile("^(1[0-9]|20|21|22|23|24)-ARID-(\\d{3,4})$");
        courseIdPattern = Pattern.compile("^[A-Za-z]{1,5}-[0-9]{1,5}$");
        alphabetPattern = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
        adminIdPattern = Pattern.compile("^AD-\\d{3,4}$");
        digitPattern = Pattern.compile("^\\d+$");
        alphanumericPattern = Pattern.compile("^[a-zA-Z0-9]+$");
    }

    public boolean matchRegNoPattern(String regNo) {
        System.out.println("ffdfdg"+regNo.length());
        return regNoPattern.matcher(regNo).matches();
    }

    public boolean matchEmailPattern(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean matchPasswordPattern(String password) {
        return passwordPattern.matcher(password).matches();
    }

    public boolean matchNamePattern(String name) {
        return namePattern.matcher(name).matches();
    }

    public boolean matchCourseIdPattern(String courseId) {
        return courseIdPattern.matcher(courseId).matches();
    }

    public boolean matchAlphabetPattern(String alphabet) {
        return alphabetPattern.matcher(alphabet).matches();
    }

    public boolean matchAdminIdPattern(String adminId) {
        return adminIdPattern.matcher(adminId).matches();
    }

    public boolean matchDigitPattern(String digit) {
        return digitPattern.matcher(digit).matches();
    }

    public boolean matchAlphanumericPattern(String alphanumeric) {
        return alphanumericPattern.matcher(alphanumeric).matches();
    }
    public List<String> validateInputs(String selectedrole ,String email, String password, String userid, String firstname, String lastname) {
        List<String> errors = new ArrayList<>();

        if (!matchEmailPattern(email)) {
            errors.add("Invalid email address.");
        }

        if (!matchPasswordPattern(password)) {
            errors.add("Invalid password.");
        }
        if(selectedrole.equals("student"))
        {
             if (!matchRegNoPattern(userid)) {
            errors.add("Invalid registration number.");
            }
             
        }
        else if (selectedrole.equals("admin"))
        {
            if (!matchAdminIdPattern(userid)) {
            errors.add("Invalid Admin Id.");
            }
        }
       

        if (!matchNamePattern(firstname)) {
            errors.add("Invalid first name.");
        }

        if (!matchNamePattern(lastname)) {
            errors.add("Invalid last name.");
        }

        return errors;
    }
    public List<String> validateInputs(String selectedrole ,String email, String password) {
        List<String> errors = new ArrayList<>();

        if (!matchEmailPattern(email)) {
            errors.add("Invalid email address.");
        }

        if (!matchPasswordPattern(password)) {
            errors.add("Invalid password.");
        }


        return errors;
    }
     public List<String> validateCourseInputs(String courseid ,String coursetitle, String instructor) {
        List<String> errors = new ArrayList<>();

        if (!matchCourseIdPattern(courseid)) {
            errors.add("Invalid Course Id");
        }

        if (!matchAlphabetPattern(coursetitle)) {
            errors.add("Invalid Course Title.");
        }
        if (!matchAlphabetPattern(instructor)) {
            errors.add("Invalid Instructor Name");
        }


        return errors;
    }
}