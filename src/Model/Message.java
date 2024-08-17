
package Model;


import java.util.Date;

public class Message {

    private int messageId;
    private int senderId;
    private int recipientId;
    private String content;
    private Date sentTime;

    
  // No-argument constructor
    public Message() {
    }

    // Constructor with all fields
    public Message(int messageId, int senderId, int recipientId, String content, Date sentTime) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.sentTime = sentTime;
    }

    // Constructor without messageId
    public Message(int senderId, int recipientId, String content, Date sentTime) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.sentTime = sentTime;
    }

    // Getters and Setters
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String toString() {
        return "Message{"
                + "messageId=" + messageId
                + ", senderId=" + senderId
                + ", recipientId=" + recipientId
                + ", content='" + content + '\''
                + ", sentTime=" + sentTime
                + '}';
    }
}
