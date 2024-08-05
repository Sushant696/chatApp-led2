/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sushant
 */

package DAO.Message;

import Model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAOImplementation implements MessageDAO {
    private Connection connection;

    public MessageDAOImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void sendMessage(Message message) {
        String query = "INSERT INTO Message (senderId, recipientId, content, sentTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setInt(2, message.getRecipientId());
            stmt.setString(3, message.getContent());
            stmt.setTimestamp(4, new java.sql.Timestamp(message.getSentTime().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message";
        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int messageId = rs.getInt("messageId");
                int senderId = rs.getInt("senderId");
                int recipientId = rs.getInt("recipientId");
                String content = rs.getString("content");
                java.sql.Timestamp sentTime = rs.getTimestamp("sentTime");

                // Debugging output
                System.out.println("Retrieved Message: ID=" + messageId + ", SenderID=" + senderId +
                        ", RecipientID=" + recipientId + ", Content=" + content + ", SentTime=" + sentTime);

                Message message = new Message(messageId, senderId, recipientId, content, new Date(sentTime.getTime()));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesByUserId(int recipientId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message WHERE recipientId = ? ORDER BY sentTime";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, recipientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int messageId = rs.getInt("messageId");
                    int senderId = rs.getInt("senderId");
                    int recipientIdFromDb = rs.getInt("recipientId");
                    String content = rs.getString("content");
                    java.sql.Timestamp sentTime = rs.getTimestamp("sentTime");

                    Message message = new Message(messageId, senderId, recipientIdFromDb, content,
                            new Date(sentTime.getTime()));
                    messages.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
