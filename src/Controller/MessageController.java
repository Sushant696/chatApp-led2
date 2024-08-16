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

    public List<Message> getMessagesBetweenUsers(int user1Id, int user2Id) {
        return messageDAO.getMessagesBetweenUsers(user1Id, user2Id);
    }
}
