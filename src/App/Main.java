/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

/**
 *
 * @author sushant
 */


    
import View.LoginScreen;
import View.RegisterScreen;

public class Main {
    public static void main(String[] args) {
        // Initialize and show the login screen
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);     
        });
    }
}
