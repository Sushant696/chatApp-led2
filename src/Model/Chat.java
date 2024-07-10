package Model;

import java.time.LocalDateTime;

public class Chat {
    private int id;
    private String sender;
    private String message;
    private LocalDateTime sentTime;

    // Constructor, getters, and setters
    public Chat() {
    }

    public Chat(int id, String sender, String message, LocalDateTime sentTime) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.sentTime = sentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
}
