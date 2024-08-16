
package Controller;


import DAO.User.UserDAO;
import DAO.User.UserDAOImplementation;
import Model.User;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImplementation();
    }

    public void signUp(String username, String password) {
        if (userDAO.getUserByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userDAO.addUser(user);
        } else {
            System.out.println("Username already exists");
        }
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
