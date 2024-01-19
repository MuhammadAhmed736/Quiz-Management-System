package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Admin;
import model.Student;

public class AdminDAO {

    
    private static final String INSERT_ADMIN_SQL = 
        "INSERT INTO Admin (admin_id, firstName, lastName, emailAddress, Password) VALUES (?, ?, ?, ?, ?)";

    public Admin saveAdmin(Admin admin) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {

            preparedStatement.setString(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getFirstName());
            preparedStatement.setString(3, admin.getLastName());
            preparedStatement.setString(4, admin.getEmailAddress());
            preparedStatement.setString(5, admin.getPassword());
            preparedStatement.executeUpdate();
            
            System.out.println("Admin Saved");
             return admin;
            
        } catch (SQLException e) {
          
            return null;
          
        }
    }

         public Admin validateAdminLogin(String email, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Admin WHERE emailAddress = ? AND Password = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   
                    String AdminId = resultSet.getString("admin_id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                
                 
                    return new Admin( firstName, lastName, email, password,AdminId);
                } else {
                    // No matching record found
                    return null;
                }
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle or log the exception appropriately
            return null;
            
        }
    }
         public Boolean updateAdmin(Admin a) {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE Admin SET firstName = ?, lastName = ?, emailAddress = ?, Password = ? WHERE admin_id = ?")) {

        preparedStatement.setString(1, a.getFirstName());
        preparedStatement.setString(2, a.getLastName());
        preparedStatement.setString(3, a.getEmailAddress());
        preparedStatement.setString(4, a.getPassword());
        preparedStatement.setString(5, a.getAdminId()); // Use student ID for WHERE clause

        int updatedRows = preparedStatement.executeUpdate();

        if (updatedRows > 0) {
            
            System.out.println("Admin updated successfully");
            return true;
        } else {
            System.out.println("No Admin found with the given Admin ID");
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        // Handle or log the exception appropriately
    }
         }
}
