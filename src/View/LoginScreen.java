
package View;

import DAO.User.UserDAO;
import DAO.User.UserDAOImplementation;
import Model.User;
import Database.MySqlConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoginScreen extends javax.swing.JFrame {

    final UserDAO userDAO;

    public LoginScreen() {
        userDAO = new UserDAOImplementation();
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        emailAddress_label = new javax.swing.JLabel();
        greetingLabel = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        signuptext = new javax.swing.JLabel();
        showButton = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        setResizable(false);
        setSize(600, 600);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerPanel.setBackground(new java.awt.Color(54, 52, 51));

        emailAddress_label.setFont(new java.awt.Font("Trebuchet MS", 1, 26));
        emailAddress_label.setForeground(new java.awt.Color(227, 225, 218));
        emailAddress_label.setText("Email Address");
        emailAddress_label.setToolTipText("Email Address");

        greetingLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 36));
        greetingLabel.setForeground(new java.awt.Color(227, 225, 218));
        greetingLabel.setText("Welcome Back!!");
        greetingLabel.setToolTipText("");

        emailInput.setBackground(new java.awt.Color(204, 204, 204));
        emailInput.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18));
        emailInput.setBorder(passwordField.getBorder());
        emailInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        passwordLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 26));
        passwordLabel.setForeground(new java.awt.Color(227, 225, 218));
        passwordLabel.setText("Password");

        passwordField.setBackground(new java.awt.Color(204, 204, 204));
        passwordField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18));
        passwordField.setToolTipText("");

        loginButton.setBackground(new java.awt.Color(38, 94, 237));
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 18));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Log In");
        loginButton.setToolTipText("Button to get into main app");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });

        registerButton.setBackground(new java.awt.Color(204, 204, 204));
        registerButton.setText("Register");
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
        });

        signuptext.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18));
        signuptext.setForeground(new java.awt.Color(227, 225, 218));
        signuptext.setText("New here?");

        showButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/eye-3-48.png")));
        showButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showButtonMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
                containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(containerPanelLayout.createSequentialGroup()
                                .addGroup(containerPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(containerPanelLayout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(containerPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(passwordField)
                                                        .addComponent(emailInput)
                                                        .addComponent(emailAddress_label)
                                                        .addComponent(passwordLabel)
                                                        .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                332, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(showButton))
                                        .addGroup(containerPanelLayout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(signuptext, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(registerButton))
                                        .addGroup(containerPanelLayout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(greetingLabel)))
                                .addContainerGap(33, Short.MAX_VALUE)));
        containerPanelLayout.setVerticalGroup(
                containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(containerPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(greetingLabel)
                                .addGap(36, 36, 36)
                                .addComponent(emailAddress_label)
                                .addGap(18, 18, 18)
                                .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(passwordLabel)
                                .addGap(18, 18, 18)
                                .addGroup(containerPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(containerPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(signuptext)
                                        .addComponent(registerButton))
                                .addContainerGap(27, Short.MAX_VALUE)));

        getContentPane().add(containerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 480, 440));

        backgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/background.png")));
        backgroundImage.setToolTipText("backgroundImage");
        getContentPane().add(backgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {
        new RegisterScreen().setVisible(true);
        this.dispose();
    }

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String email = emailInput.getText();
        String password = new String(passwordField.getPassword());

        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getPhoto() == null) {
                new PhotoUploadScreen(email).setVisible(true);
                this.dispose();
            } else {
                new Home(email).setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Invalid Email or Password");
        }
    }

    @SuppressWarnings("unused")
    private void showPhotoUploadDialog(int userId) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                byte[] photoData = Files.readAllBytes(selectedFile.toPath());
                if (userDAO.updateUserPhoto(userId, photoData)) {
                    JOptionPane.showMessageDialog(this, "Photo uploaded successfully!");
                    new Home(emailInput.getText()).setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to upload photo. Please try again.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        } else {
            // User cancelled photo upload, proceed to
            new Home(emailInput.getText()).setVisible(true);
            this.dispose();
        }
    }

    private void showButtonMouseReleased(java.awt.event.MouseEvent evt) {
        passwordField.setEchoChar('*');
    }

    private void showButtonMousePressed(java.awt.event.MouseEvent evt) {
        passwordField.setEchoChar((char) 0);
    }

    @SuppressWarnings("unused")
    private void login(String email, String password) {
        User user = userDAO.getUserByUsername(email);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(rootPane, "Successfully logged in. \nOpening chat application");
            new Home(email).setVisible(true); // Open the Home window
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Invalid Email or Password");
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel backgroundImage;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JLabel emailAddress_label;
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel greetingLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel showButton;
    private javax.swing.JLabel signuptext;

}