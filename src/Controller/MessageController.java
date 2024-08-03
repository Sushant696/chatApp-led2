/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;

import DAO.Message.MessageDAO;
import Model.Message;
import java.util.List;

public class MessageController {
    private MessageDAO messageDAO;

    public MessageController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void sendMessage(Message message) {
        messageDAO.sendMessage(message);
    }

    public List<Message> getMessagesByRecipient(int recipientId) {
        return messageDAO.getMessagesByUserId(recipientId);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
}
