/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sushant
 */
package DAO.User;
import Model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    User getUserById(int userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
}
