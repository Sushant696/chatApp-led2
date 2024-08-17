
package DAO.FriendShip;

import Model.User;
import java.util.List;

public interface FriendshipDAO {
        void sendFriendRequest(int userId, int friendId);
    void respondToFriendRequest(int friendshipId, String status);
    List<User> getFriendsList(int userId);
}
