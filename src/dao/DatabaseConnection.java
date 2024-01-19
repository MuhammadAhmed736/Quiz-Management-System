package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/QuizManagementSystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ahmed735";

    private static Connection connection;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    // Other methods to close connections, execute queries, etc.
}
