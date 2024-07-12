/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;

/**
 *
 * @author sushant
 */
import DAO.User.UserDAO;
public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    public boolean authenticateUser(String username, String password) {
        // Implement authentication logic
        return false;
    }
}