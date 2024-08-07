
/**
 *
 * @author sushant
 */

package App;

import View.LoginScreen;
import sockets.ChatServer;

public class Main {
    public static void main(String[] args) {
        // Start the chat server in a separate thread
        new Thread(() -> {
            try {
                ChatServer.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Initialize and show the login screen
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }
}