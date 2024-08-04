package DAO.User;

import Database.MySqlConnection;
import Model.User;
import java.sql.Connection;
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
        Connection conn = mySqlConnection.openConnection();
        String query = "INSERT INTO users (username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')";
        mySqlConnection.executeUpdate(conn, query);
        mySqlConnection.closeConnection(conn);
    }

    @Override
    public void updateUser(User user) {
        Connection conn = mySqlConnection.openConnection();
        String query = "UPDATE users SET username='" + user.getUsername() + "', password='" + user.getPassword() + "' WHERE id=" + user.getId();
        mySqlConnection.executeUpdate(conn, query);
        mySqlConnection.closeConnection(conn);
    }

    @Override
    public void deleteUser(int userId) {
        Connection conn = mySqlConnection.openConnection();
        String query = "DELETE FROM users WHERE id=" + userId;
        mySqlConnection.executeUpdate(conn, query);
        mySqlConnection.closeConnection(conn);
    }

    @Override
    public User getUserById(int userId) {
        Connection conn = mySqlConnection.openConnection();
        String query = "SELECT * FROM users WHERE id=" + userId;
        ResultSet rs = mySqlConnection.runQuery(conn, query);
        User user = null;
        try {
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySqlConnection.closeConnection(conn);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Connection conn = mySqlConnection.openConnection();
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        ResultSet rs = mySqlConnection.runQuery(conn, query);
        User user = null;
        try {
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mySqlConnection.closeConnection(conn);
        return user;
    }

    @Override
public List<User> getAllUsers() {
    Connection conn = mySqlConnection.openConnection();
    String query = "SELECT * FROM data";
    ResultSet rs = null;
    List<User> users = new ArrayList<>();
    
    try {
        rs = mySqlConnection.runQuery(conn, query);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Ensure ResultSet and Connection are closed in the finally block
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        mySqlConnection.closeConnection(conn);
    }
    return users;
}

}
