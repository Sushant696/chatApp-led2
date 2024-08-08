package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import DAO.User.UserDAOImplementation;
import Model.User;

public class UserProfileView extends javax.swing.JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JButton updateButton;
    private JButton deleteAccountButton;
    private UserDAOImplementation userDAO;
    private String email;
    private Home homeFrame;

    public UserProfileView(String email, Home homeFrame) {
        this.email = email;
        this.userDAO = new UserDAOImplementation();
        this.homeFrame = homeFrame;
        initComponents();
    }

    private void initComponents() {
        setTitle("User Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        User user = userDAO.getUserByEmail(this.email);
        if (user != null) {
            usernameField = new JTextField(user.getUsername(), 20);
            emailField = new JTextField(user.getEmail(), 20);
        } else {
            usernameField = new JTextField(20);
            emailField = new JTextField(20);
        }

        updateButton = new JButton("Update Profile");
        deleteAccountButton = new JButton("Delete Account");

        add(new JLabel("Username:"), gbc);
        add(usernameField, gbc);
        add(new JLabel("Email:"), gbc);
        add(emailField, gbc);
        add(updateButton, gbc);
        add(deleteAccountButton, gbc);

        updateButton.addActionListener(e -> updateProfile());
        deleteAccountButton.addActionListener(e -> deleteAccount());
    }

    private void updateProfile() {
        String newUsername = usernameField.getText().trim();
        String newEmail = emailField.getText().trim();

        if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
            User user = userDAO.getUserByEmail(this.email);
            if (user != null) {
                user.setUsername(newUsername);
                user.setEmail(newEmail);
                userDAO.updateUser(user);
                JOptionPane.showMessageDialog(this,
                        "Profile updated successfully. Please log in again with your new credentials.");
                this.dispose(); // Close the UserProfileView frame
                if (homeFrame != null) {
                    homeFrame.dispose(); // Close the Home frame
                }
                new LoginScreen().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "User not found for the given email.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter valid username and email.");
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