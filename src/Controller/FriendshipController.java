
package Controller;



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
