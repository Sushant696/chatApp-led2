
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
