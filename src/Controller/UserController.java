/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author sushant
 */


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
            user.setPassword(password); // You should hash the password here
            userDAO.addUser(user);
        } else {
            System.out.println("Username already exists");
        }
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) { // You should hash the password here and compare
            return user;
        }
        return null;
    }
}
