package tung.example;

import java.sql.*;

class SafeSQLExample {
    public static void main(String[] args) {
        String userInput = "' OR '1'='1"; // Đầu vào giả lập

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass")) {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, userInput);
                System.out.println("Executing query with parameter: " + userInput);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    System.out.println("Found user: " + rs.getString("username"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
