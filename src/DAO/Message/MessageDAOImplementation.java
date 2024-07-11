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
import java.util.List;

 public class MessageDAOImplementation implements MessageDAO {
    @Override
    public void sendMessage(Message message) {
        // Implementation to save the message in the database
    }

    @Override
    public List<Message> getMessagesByRecipient(int recipientId) {
        // Implementation to retrieve messages for a recipient from the database
        return null; // Replace with actual implementation
    }
}