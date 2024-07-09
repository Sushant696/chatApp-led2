package Database;

import java.sql.*;

public class AccountDetails {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9848688463";
    private static final String DATABASE = "accountDetails";

    public static void main(String[] args) {
        AccountDetails details = new AccountDetails();
        details.makeConnection();
    }

    public void makeConnection() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);
            stmt.executeUpdate("USE " + DATABASE);
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Data (email VARCHAR(255), username VARCHAR(255), password VARCHAR(255))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertCredentials(String email, String user, String password) {
        boolean taken = false;
        String sql = "SELECT * FROM Data WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL + DATABASE, USERNAME, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    taken = true;
                } else {
                    String insertSql = "INSERT INTO Data (email, username, password) VALUES (?, ?, ?)";
                    try (PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {
                        insertPstmt.setString(1, email);
                        insertPstmt.setString(2, user);
                        insertPstmt.setString(3, password);
                        insertPstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taken;
    }

    public int checkCredentials(String email, String password) {
        boolean emailValidity = false;
        boolean passValidity = false;

        String emailSql = "SELECT * FROM Data WHERE email = ?";
        String passSql = "SELECT * FROM Data WHERE password = ?";

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL + DATABASE, USERNAME, PASSWORD);
                PreparedStatement emailPstmt = conn.prepareStatement(emailSql);
                PreparedStatement passPstmt = conn.prepareStatement(passSql)) {

            emailPstmt.setString(1, email);
            try (ResultSet validEmail = emailPstmt.executeQuery()) {
                emailValidity = validEmail.next();
            }

            passPstmt.setString(1, password);
            try (ResultSet pass = passPstmt.executeQuery()) {
                passValidity = pass.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!emailValidity) {
            return 0;
        } else if (!passValidity) {
            return -1;
        } else {
            return 1;
        }
    }
}
