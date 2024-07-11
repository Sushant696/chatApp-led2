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

public interface MessageDAO {
    void sendMessage(Message message);
    List<Message> getMessagesByRecipient(int recipientId);
}   