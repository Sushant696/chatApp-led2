package DAO.User;

import Database.MySqlConnection;
import Model.User;
import java.sql.Connection;
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
            String query = "UPDATE users SET username=?, password=? WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getEmail());
                // user.setEmail(rs.getString("email"));

                pstmt.executeUpdate();
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
                    // user.setId(rs.getInt("id"));
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
                    // user.setId(rs.getInt("id"));
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

}
