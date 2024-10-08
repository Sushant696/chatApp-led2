
package View;

import Database.MySqlConnection;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class RegisterScreen extends javax.swing.JFrame {

 
    public RegisterScreen() {
        initComponents();
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        confirmLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        headingTextField = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        loginTextField = new javax.swing.JLabel();
        logInButton = new javax.swing.JButton();
        showPassword = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerPanel.setBackground(new java.awt.Color(54, 52, 51));

        emailLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email Address");

        emailTextField.setBackground(new java.awt.Color(204, 204, 204));
        emailTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); 

        usernameLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username");

        usernameTextField.setBackground(new java.awt.Color(204, 204, 204));
        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); 

        passwordLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");

        PasswordField.setBackground(new java.awt.Color(204, 204, 204));
        PasswordField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); 

        confirmLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
        confirmLabel.setForeground(new java.awt.Color(255, 255, 255));
        confirmLabel.setText("Confirm Password");

        confirmPasswordField.setBackground(new java.awt.Color(204, 204, 204));
        confirmPasswordField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); 

        headingTextField.setFont(new java.awt.Font("Trebuchet MS", 1, 35)); 
        headingTextField.setForeground(new java.awt.Color(255, 255, 255));
        headingTextField.setText("Create an account");

        signUpButton.setBackground(new java.awt.Color(38, 94, 237));
        signUpButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        signUpButton.setForeground(new java.awt.Color(255, 255, 255));
        signUpButton.setText("Sign Up");
        signUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpButtonMouseClicked(evt);
            }
        });

        loginTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); 
        loginTextField.setForeground(new java.awt.Color(255, 255, 255));
        loginTextField.setText("Already have an account?");

        logInButton.setBackground(new java.awt.Color(204, 204, 204));
        logInButton.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); 
        logInButton.setText("Log In");
        logInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInButtonMouseClicked(evt);
            }
        });

        showPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/eye-3-48.png"))); 
        showPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPasswordMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showPasswordMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerPanelLayout.createSequentialGroup()
                        .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(containerPanelLayout.createSequentialGroup()
                                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, containerPanelLayout.createSequentialGroup()
                                        .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(confirmLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(signUpButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(containerPanelLayout.createSequentialGroup()
                                .addComponent(confirmPasswordField)
                                .addGap(7, 7, 7)))
                        .addComponent(showPassword))
                    .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usernameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelLayout.createSequentialGroup()
                .addGap(0, 128, Short.MAX_VALUE)
                .addComponent(headingTextField)
                .addGap(102, 102, 102))
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(headingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logInButton))
                .addGap(16, 16, 16))
        );

        getContentPane().add(containerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 530, 540));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/background.png"))); 
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 640));

        pack();
        setLocationRelativeTo(null);
    }
    private void logInButtonMouseClicked(java.awt.event.MouseEvent evt) {
        new LoginScreen().setVisible(true);
        this.dispose();
    }
    private void showPasswordMousePressed(java.awt.event.MouseEvent evt) {
        PasswordField.setEchoChar((char) 0);
        confirmPasswordField.setEchoChar((char) 0);
    }

    private void showPasswordMouseReleased(java.awt.event.MouseEvent evt) {
        PasswordField.setEchoChar('*');
        confirmPasswordField.setEchoChar('*');
    }

    private void signUpButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String email = emailTextField.getText();
        String user = usernameTextField.getText();
        String password = PasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        ArrayList<String> format = new ArrayList<>();
        format.add("gmail");
        format.add("hotmail");
        format.add("outlook");
        format.add("yahoo");

        boolean validEmail = false;
        for (String domain : format) {
            if (email.contains("@" + domain + ".com")) {
                validEmail = true;
                break;
            }
        }
        if ((("".equals(email) || "".equals(user)) || "".equals(password)) || "".equals(confirmPassword)) {
            JOptionPane.showMessageDialog(rootPane, "Please fill all information");
        } else if (!validEmail) {
            JOptionPane.showMessageDialog(rootPane, "Email must be valid");
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(rootPane, "Password must be 8 or more");
        } else if (!confirmPassword.equals(password)) {
            JOptionPane.showMessageDialog(rootPane, "Incorrect Password");
        } else {
            MySqlConnection ad;
            ad = new MySqlConnection();
            ad.makeConnection();
            boolean ic = ad.insertCredentials(email, user, password);
            if (ic) {
                JOptionPane.showMessageDialog(rootPane, "Email already taken");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Sucessfully Registered.");
            }

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
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterScreen().setVisible(true);
            }
        });
    }

    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel confirmLabel;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel headingTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logInButton;
    private javax.swing.JLabel loginTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel showPassword;
    private javax.swing.JButton signUpButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;

}
