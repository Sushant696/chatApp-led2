package View;

import DAO.User.UserDAO;
import DAO.User.UserDAOImplementation;
import Model.User;
import View.Home;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;

public class PhotoUploadScreen extends JFrame {
    private final UserDAO userDAO;
    private final String email;
    private JLabel photoLabel;
    private JButton uploadButton;
    private JButton skipButton;

    public PhotoUploadScreen(String email) {
        this.email = email;
        this.userDAO = new UserDAOImplementation();
        initComponents();
    }

    private void initComponents() {
        User user = userDAO.getUserByEmail(email);

        // Debugging statements
        System.out.println("User Retrieved: " + user);
        if (user != null) {
            System.out.println("User ID: " + user.getId());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("Photo Data: " + (user.getPhoto() != null ? "Exists" : "No Photo"));
        }

        if (user != null && user.getPhoto() != null) {
            new Home(email).setVisible(true);
            this.dispose();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Upload Profile Photo");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Upload Your Profile Photo", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        photoLabel = new JLabel("No photo selected", JLabel.CENTER);
        photoLabel.setPreferredSize(new Dimension(150, 150));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(photoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        uploadButton = new JButton("Upload Photo");
        skipButton = new JButton("Skip");

        uploadButton.addActionListener(e -> uploadPhoto());
        skipButton.addActionListener(e -> skipUpload());

        buttonPanel.add(uploadButton);
        buttonPanel.add(skipButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void uploadPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                byte[] photoData = Files.readAllBytes(selectedFile.toPath());
                User user = userDAO.getUserByEmail(email);
                if (user == null) {
                    JOptionPane.showMessageDialog(this, "User not found for email: " + email);
                    return;
                }

                System.out.println("User ID: " + user.getId());
                System.out.println("User Email: " + user.getEmail());
                System.out.println("User Username: " + user.getUsername());
                System.out.println("Photo data size: " + photoData.length + " bytes");

                boolean updateSuccess = userDAO.updateUserPhoto(user.getId(), photoData);
                System.out.println("Update success: " + updateSuccess);

                if (updateSuccess) {
                    ImageIcon icon = new ImageIcon(photoData);
                    Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    photoLabel.setIcon(new ImageIcon(img));
                    photoLabel.setText("");
                    JOptionPane.showMessageDialog(this, "Photo uploaded successfully!");
                    proceedToMainApp();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to upload photo. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
    }

    private void skipUpload() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to skip uploading a profile photo?",
                "Skip Photo Upload",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            proceedToMainApp();
        }
    }

    private void proceedToMainApp() {
        new Home(email).setVisible(true);
        this.dispose();
    }
}