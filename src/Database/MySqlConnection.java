package Database;

import java.sql.*;

public class MySqlConnection {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9848688463";
    private static final String DATABASE = "ledDatabase";

    public static void main(String[] args) {
        MySqlConnection details = new MySqlConnection();
        details.makeConnection();
        // details.insertCredentials("test@example.com", "testuser", "testpass");
    }

    public void makeConnection() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);
            stmt.executeUpdate("USE " + DATABASE);
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (\r\n" + //
                            "    id INT AUTO_INCREMENT PRIMARY KEY,\r\n" + //
                            "    email VARCHAR(255),\r\n" + //
                            "    username VARCHAR(255),\r\n" + //
                            "    password VARCHAR(255),\r\n" + //
                            "    photo BLOB\r\n" + //
                            ");\r\n" + //
                            "");
            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection openConnection() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_URL + DATABASE, USERNAME, PASSWORD);
            System.out.println("Connection established successfully.");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeUpdate(Connection conn, String query) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet runQuery(Connection conn, String query) {
        try (Statement stmt = conn.createStatement()) {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    public boolean insertCredentials(String email, String user, String password) {
        boolean taken = false;
        String sql = "SELECT * FROM users WHERE email = ?";
        System.out.println("Attempting to insert credentials...");

        try (Connection conn = openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.err.println("Connection is null. Unable to insert credentials.");
                return taken;
            }

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    taken = true;
                    System.out.println("Email already taken.");
                } else {
                    String insertSql = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
                    try (PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {
                        insertPstmt.setString(1, email);
                        insertPstmt.setString(2, user);
                        insertPstmt.setString(3, password);
                        int rowsAffected = insertPstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Inserted new user: " + user);
                        } else {
                            System.err.println("Failed to insert user: " + user);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taken;
    }

    @SuppressWarnings("unused")
    public int checkCredentials(String email, String password) {
        boolean emailValidity = false;
        boolean passValidity = false;

        String emailSql = "SELECT * FROM users WHERE email = ?";
        String passSql = "SELECT * FROM users WHERE password = ?";

        try (Connection conn = openConnection();
                PreparedStatement emailPstmt = conn.prepareStatement(emailSql);
                PreparedStatement passPstmt = conn.prepareStatement(passSql)) {

            if (conn == null) {
                System.err.println("Connection is null. Unable to check credentials.");
                return 0;
            }

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
