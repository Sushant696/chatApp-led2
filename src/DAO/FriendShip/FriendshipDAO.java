/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.FriendShip;

/**
 *
 * @author sushant
 */
import Model.User;
import java.util.List;

public interface FriendshipDAO {
        void sendFriendRequest(int userId, int friendId);
    void respondToFriendRequest(int friendshipId, String status);
    List<User> getFriendsList(int userId);
}
