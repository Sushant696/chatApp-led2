/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sushant
 */
package DAO.Chat;

import Model.Chat;
import java.util.List;

 interface ChatDAO {
    List<Chat> getAllChats();
    Chat getChatById(int id);
    void addChat(Chat chat);
    void updateChat(Chat chat);
    void deleteChat(int id);
}
