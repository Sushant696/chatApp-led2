package DAO.User;

import Database.MySqlConnection;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO {

    final MySqlConnection mySqlConnection;

    public UserDAOImplementation() {
        this.mySqlConnection = new MySqlConnection();
        System.out.println("User DAO Implementation ran");
    }

    @Override
    public void addUser(User user) {
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySqlConnection.closeConnection(conn);
        }
    }

    @Override
    public void updateUser(User user) {
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "UPDATE users SET username=? WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setInt(2, user.getId());
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected by update: " + rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySqlConnection.closeConnection(conn);
        }
    }

    @Override
    public void deleteUser(int userId) {
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "DELETE FROM users WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySqlConnection.closeConnection(conn);
        }
    }

    @Override
    public User getUserById(int userId) {
        Connection conn = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "SELECT * FROM users WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Connection conn = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "SELECT * FROM users WHERE username=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try {
            conn = mySqlConnection.openConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection conn = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "SELECT * FROM users WHERE email=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    // Populate the User object with data from the database
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setPhoto(rs.getBytes("photo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return user;
    }

    @Override
    public boolean updateUserPhoto(int userId, byte[] photo) {
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "UPDATE users SET photo = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setBytes(1, photo);
                pstmt.setInt(2, userId);
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        } finally {
            mySqlConnection.closeConnection(conn);
        }
        return false;
    }

    @Override
    public byte[] getUserPhoto(int userId) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "SELECT photo FROM users WHERE id = ?";
            System.out.println("Fetching photo for user ID: " + userId);
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    byte[] photoData = rs.getBytes("photo");
                    System.out.println(
                            "Photo data fetched: " + (photoData != null ? photoData.length + " bytes" : "null"));
                    return photoData;
                } else {
                    System.out.println("No photo found for user ID: " + userId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching photo for user ID: " + userId);
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean hasUserUploadedPhoto(int userId) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = mySqlConnection.openConnection();
            String query = "SELECT photo FROM users WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getBytes("photo") != null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            mySqlConnection.closeConnection(conn);
        }
        return false;
    }
}