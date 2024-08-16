
package DAO.Chat;

import Model.Chat;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOImplementation implements ChatDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override   
    public List<Chat> getAllChats() {
        List<Chat> chats = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM chats")) {
            while (rs.next()) {
                Chat chat = new Chat();
                chat.setId(rs.getInt("id"));
                chat.setSender(rs.getString("sender"));
                chat.setMessage(rs.getString("message"));
                chat.setSentTime(rs.getTimestamp("sent_time").toLocalDateTime());
                chats.add(chat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }

    @Override
    public Chat getChatById(int id) {
        Chat chat = null;
        String sql = "SELECT * FROM chats WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    chat = new Chat();
                    chat.setId(rs.getInt("id"));
                    chat.setSender(rs.getString("sender"));
                    chat.setMessage(rs.getString("message"));
                    chat.setSentTime(rs.getTimestamp("sent_time").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chat;
    }

    @Override
    public void addChat(Chat chat) {
        String sql = "INSERT INTO chats (sender, message, sent_time) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, chat.getSender());
            pstmt.setString(2, chat.getMessage());
            pstmt.setTimestamp(3, Timestamp.valueOf(chat.getSentTime()));
            pstmt.executeUpdate();

            // Retrieve auto-generated ID if needed
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chat.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateChat(Chat chat) {
        String sql = "UPDATE chats SET sender = ?, message = ?, sent_time = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, chat.getSender());
            pstmt.setString(2, chat.getMessage());
            pstmt.setTimestamp(3, Timestamp.valueOf(chat.getSentTime()));
            pstmt.setInt(4, chat.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChat(int id) {
        String sql = "DELETE FROM chats WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
