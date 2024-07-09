package Dao;

import Database.MySqlConnection;
import Model.LoginRequest;
import Model.UserData;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    private final MySqlConnection mysql = new MySqlConnection();

    public UserData login(LoginRequest login) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login.getEmail());
            pstmt.setString(2, login.getPassword());
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    UserData user = new UserData(
                            result.getString("email"),
                            result.getString("username"),
                            result.getString("password"));
                    user.setId(result.getInt("id"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkUser(UserData user) {
        String sql = "SELECT * FROM users WHERE email = ? OR username = ?";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            try (ResultSet result = pstmt.executeQuery()) {
                return result.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void signup(UserData user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<UserData> getAllUserData() {
        ArrayList<UserData> data = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet result = pstmt.executeQuery()) {
            while (result.next()) {
                UserData user = new UserData(
                        result.getString("email"),
                        result.getString("username"),
                        result.getString("password"));
                user.setId(result.getInt("id"));
                data.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public boolean update(UserData user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean delete(UserData user) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = mysql.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
