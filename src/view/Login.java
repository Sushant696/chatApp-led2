/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Database.AccountDetails;
import javax.swing.JOptionPane;

/**
 *
 * @author Gaurab
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        setSize(600,600);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerPanel.setBackground(new java.awt.Color(54, 52, 51));

        emailAddress_label.setFont(new java.awt.Font("Trebuchet MS", 1, 26)); // NOI18N
        emailAddress_label.setForeground(new java.awt.Color(227, 225, 218));
        emailAddress_label.setText("Email Address");
        emailAddress_label.setToolTipText("Email Address");

        greetingLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        greetingLabel.setForeground(new java.awt.Color(227, 225, 218));
        greetingLabel.setText("Welcome Back!!");
        greetingLabel.setToolTipText("");

        emailInput.setBackground(new java.awt.Color(204, 204, 204));
        emailInput.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        emailInput.setBorder(passwordField.getBorder());
        emailInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        passwordLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 26)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(227, 225, 218));
        passwordLabel.setText("Password");

        passwordField.setBackground(new java.awt.Color(204, 204, 204));
        passwordField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        passwordField.setToolTipText("");

        loginButton.setBackground(new java.awt.Color(38, 94, 237));
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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

        signuptext.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        signuptext.setForeground(new java.awt.Color(227, 225, 218));
        signuptext.setText("New here?");

        showButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/eye-3-48.png"))); // NOI18N
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
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField)
                            .addComponent(emailInput)
                            .addComponent(emailAddress_label)
                            .addComponent(passwordLabel)
                            .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(showButton))
                    .addGroup(containerPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(signuptext, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerButton))
                    .addGroup(containerPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(greetingLabel)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(greetingLabel)
                .addGap(36, 36, 36)
                .addComponent(emailAddress_label)
                .addGap(18, 18, 18)
                .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addGap(18, 18, 18)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signuptext)
                    .addComponent(registerButton))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(containerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 480, 440));

        backgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/background.png"))); // NOI18N
        backgroundImage.setToolTipText("backgroundImage");
        getContentPane().add(backgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked
        // TODO add your handling code here:
        new Register().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerButtonMouseClicked

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        // TODO add your handling code here:
        AccountDetails ad = new AccountDetails();
        ad.makeConnection();
        String e = emailInput.getText();
        String p = passwordField.getText();
        int b = ad.checkCredentials(e, p);
        switch (b) {
            case 1:
                JOptionPane.showMessageDialog(rootPane, "Sucessfully logged in. \nOpening chat application");
                this.dispose();
                break;
            case 0:
                JOptionPane.showMessageDialog(rootPane, "Invalid Email");
                break;
            default:
                JOptionPane.showMessageDialog(rootPane, "Wrong Password");
                break;
        }
    }//GEN-LAST:event_loginButtonMouseClicked

    private void showButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showButtonMouseReleased
        // TODO add your handling code here:
        passwordField.setEchoChar('*');
    }//GEN-LAST:event_showButtonMouseReleased

    private void showButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showButtonMousePressed
        // TODO add your handling code here:
        passwordField.setEchoChar((char) 0);
    }//GEN-LAST:event_showButtonMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables
}
