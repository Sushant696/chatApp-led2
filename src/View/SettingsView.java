package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.User.UserDAOImplementation;
import Model.User;

public class SettingsView extends JFrame {
    private JTextField usernameField;
    private JButton updateButton;
    private JButton deleteAccountButton;
    private UserDAOImplementation userDAO;
    private String email;

    public SettingsView(String email) {
        this.email = email;
        this.userDAO = new UserDAOImplementation();
        initComponents();
    }

    private void initComponents() {
        setTitle("Settings");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        usernameField = new JTextField(20);
        updateButton = new JButton("Update Username");
        deleteAccountButton = new JButton("Delete Account");

        add(new JLabel("New Username:"), gbc);
        add(usernameField, gbc);
        add(updateButton, gbc);
        add(deleteAccountButton, gbc);

        updateButton.addActionListener(e -> updateUsername());
        deleteAccountButton.addActionListener(e -> deleteAccount());
    }

    private void updateUsername() {
        String newUsername = usernameField.getText().trim();
        if (!newUsername.isEmpty()) {
            User user = userDAO.getUserByEmail(email);
            if (user != null) {
                user.setUsername(newUsername);
                userDAO.updateUser(user);
                JOptionPane.showMessageDialog(this, "Username updated successfully!");
                // Optionally, you can refresh the main window here to show the updated username
            } else {
                JOptionPane.showMessageDialog(this, "User not found!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid username.");
        }
    }

    private void deleteAccount() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete your account? This action cannot be undone.", "Delete Account",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            User user = userDAO.getUserByEmail(email);
            if (user != null) {
                userDAO.deleteUser(user.getId());
                JOptionPane.showMessageDialog(this, "Your account has been deleted.");
                this.dispose();
                new LoginScreen().setVisible(true);
            }
        }
    }
}