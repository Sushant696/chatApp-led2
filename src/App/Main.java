
package App;

/**
 *
 * @author sushant
 */

    
import View.LoginScreen;
//import View.RegisterScreen;

public class Main {
    public static void main(String[] args) {
        // Initialize and show the login screen
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);     
        });
    }
}
