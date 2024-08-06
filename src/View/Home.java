package View;

import Controller.MessageController;
import DAO.Message.MessageDAOImplementation;
import DAO.User.UserDAOImplementation;
import Model.User;
import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Home extends javax.swing.JFrame {
        private MessageController messageController;
        private UserDAOImplementation userDAO;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JButton jButton1;
        private String email;
        private JLabel userPhotoLabel;

        public Home(String email) {
                initComponents();
                this.email = email;
                jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
                jPanel1.setPreferredSize(new Dimension(200, getHeight()));
                Connection connection = getConnection();
                messageController = new MessageController(new MessageDAOImplementation(connection));
                userDAO = new UserDAOImplementation();
                displayMessages();
                displayUsers();
                displayCurrentUser();
                displayUserPhoto();
                setPreferredSize(new Dimension(1000, 700));
                pack();

        }

        public Home() {

        }

        private Connection getConnection() {
                try {
                        return DriverManager.getConnection("jdbc:mysql://localhost:3306/leddatabase", "root",
                                        "Sqlroot");
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        private void displayMessages() {
                List<Message> messages = messageController.getAllMessages();
                jPanel3.removeAll();
                for (Message message : messages) {
                        String content = message.getContent() != null ? message.getContent() : "No content";
                        String sentTime = message.getSentTime() != null ? message.getSentTime().toString() : "No time";
                        JLabel label = new JLabel(content + " (" + sentTime + ")");
                        jPanel3.add(label);
                }
                jPanel3.revalidate();
                jPanel3.repaint();
        }

        private void displayCurrentUser() {
                try {
                        User currentUser = userDAO.getUserByEmail(this.email); // Assuming a method exists in userDAO
                        if (currentUser != null) {
                                JLabel currentUserLabel = new JLabel("Logged in as: " + currentUser.getUsername() + " ("
                                                + currentUser.getEmail() + ")");
                                currentUserLabel.setFont(new Font("Arial", Font.BOLD, 16));
                                currentUserLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                                jPanel2.add(currentUserLabel, BorderLayout.NORTH); // Assuming jPanel2 is used for the
                                                                                   // top panel
                                System.out.println("Displayed current user: " + currentUser.getUsername());
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error displaying current user: " + e.getMessage());
                }
        }

        private void displayUserPhoto() {
                try {
                        User currentUser = userDAO.getUserByEmail(this.email);
                        System.out.println(currentUser + "current user");
                        System.out.println(currentUser + "user");
                        if (currentUser != null) {
                                byte[] photoData = userDAO.getUserPhoto(currentUser.getId());

                                if (photoData != null) {
                                        ImageIcon icon = new ImageIcon(photoData);
                                        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                                        userPhotoLabel = new JLabel(new ImageIcon(img));
                                        jPanel2.add(userPhotoLabel, BorderLayout.WEST);
                                } else {
                                        userPhotoLabel = new JLabel("No photo");
                                        jPanel2.add(userPhotoLabel, BorderLayout.WEST);
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error displaying user photo: " + e.getMessage());
                }
        }

        private void displayUsers() {
                try {
                        List<User> users = userDAO.getAllUsers();
                        System.out.println("Fetched " + users.size() + " users");
                        jPanel1.removeAll();
                        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

                        for (User user : users) {
                                if (user.getEmail().equals(this.email)) {
                                        // Skip the current user
                                        continue;
                                }

                                JPanel userPanel = new JPanel();
                                userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
                                userPanel.setBackground(new Color(220, 220, 220)); // Light gray background
                                userPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding
                                userPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left

                                // Fetch and display user photo
                                byte[] photoData = userDAO.getUserPhoto(user.getId());
                                JLabel photoLabel;
                                if (photoData != null && photoData.length > 0) {
                                        ImageIcon icon = new ImageIcon(photoData);
                                        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                                        photoLabel = new JLabel(new ImageIcon(img));
                                } else {
                                        photoLabel = new JLabel("No photo");
                                }
                                photoLabel.setPreferredSize(new Dimension(40, 40));
                                userPanel.add(photoLabel);

                                // Add gap between photo and username
                                userPanel.add(Box.createRigidArea(new Dimension(10, 0)));

                                // Display username
                                JLabel userLabel = new JLabel(user.getUsername());
                                userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                                userPanel.add(userLabel);

                                // Add glue to push everything to the left
                                userPanel.add(Box.createHorizontalGlue());

                                // Wrap userPanel in another panel to make it take full width
                                JPanel wrapperPanel = new JPanel(new BorderLayout());
                                wrapperPanel.setBackground(new Color(240, 240, 240)); // Slightly lighter gray for
                                                                                      // separation
                                wrapperPanel.add(userPanel, BorderLayout.CENTER);
                                wrapperPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                                                wrapperPanel.getPreferredSize().height));

                                jPanel1.add(wrapperPanel);
                                jPanel1.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between user panels
                        }

                        jPanel1.revalidate();
                        jPanel1.repaint();
                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error displaying users: " + e.getMessage());
                }
        }

        private void sendMessage() {
                String content = jTextField1.getText();
                Message message = new Message(1, 2, content, new Date());
                message.setSenderId(1); // Assuming logged-in user ID is 1
                message.setRecipientId(2); // Assuming recipient ID is 2 for demo
                messageController.sendMessage(message);
                displayMessages();
                jTextField1.setText("");
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jPanel4 = new javax.swing.JPanel();
                jTextField1 = new javax.swing.JTextField();
                jPanel5 = new javax.swing.JPanel();
                jButton1 = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(102, 102, 102));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 259, Short.MAX_VALUE));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));

                jPanel2.setForeground(new java.awt.Color(102, 153, 255));

                jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jTextField1.setText("  Type your message...");
                jTextField1.addActionListener(evt -> jTextField1ActionPerformed(evt));

                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Components/send.png"))); // NOI18N
                jButton1.setBorder(null);
                jButton1.addActionListener(evt -> sendMessage());

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jTextField1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                483,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                67,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jButton1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                41,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));

                jPanel3.setForeground(new java.awt.Color(153, 153, 255));
                jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical arrangement

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jPanel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
        }

        private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
                // No action needed for now
        }

        public static void main(String args[]) {
                java.awt.EventQueue.invokeLater(() -> new Home().setVisible(true));
        }
}
