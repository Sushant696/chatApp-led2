/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author sushant
 */


import DAO.FriendShip.FriendshipDAO;
import Model.User;
import java.util.List;

public class FriendshipController {
    private FriendshipDAO friendshipDAO;

    public FriendshipController(FriendshipDAO friendshipDAO) {
        this.friendshipDAO = friendshipDAO;
    }

    public void sendFriendRequest(int userId, int friendId) {
        friendshipDAO.sendFriendRequest(userId, friendId);
    }

    public void respondToFriendRequest(int friendshipId, String status) {
        friendshipDAO.respondToFriendRequest(friendshipId, status);
    }

    public List<User> getFriendsList(int userId) {
        return friendshipDAO.getFriendsList(userId);
    }
}
