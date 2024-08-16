package View;

import Controller.MessageController;
import DAO.Message.MessageDAO;
import DAO.Message.MessageDAOImplementation;
import DAO.User.UserDAOImplementation;
import Model.User;
import sockets.ChatClient;
import Model.Message;
import View.UserProfileView;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.io.IOException;
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
        private JLabel userPhotoLabel;
        private ChatClient chatClient;
        private String email;
        private String username;
        private MessageDAO messageDAO;
        private JTextPane chatArea;
        private User selectedUser;
        private JScrollPane chatScrollPane;

        public Home(String email) {
                initComponents();
                this.email = email;
                jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
                jPanel1.setPreferredSize(new Dimension(200, getHeight()));

                // Initialize UserDAOImplementation
                this.userDAO = new UserDAOImplementation();

                // Fetch the username after initializing userDAO
                User currentUser = this.userDAO.getUserByEmail(this.email);
                if (currentUser != null) {
                        this.username = currentUser.getUsername();
                } else {
                        // Handle the case when user is not found
                        JOptionPane.showMessageDialog(this, "User not found for email: " + this.email);
                        return;
                }

                // Initialize MessageDAOImplementation with a database connection
                Connection connection = getConnection();
                if (connection != null) {
                        this.messageDAO = new MessageDAOImplementation(connection);
                        this.messageController = new MessageController(this.messageDAO);
                } else {
                        JOptionPane.showMessageDialog(this, "Failed to establish database connection.");
                        return;
                }

                displayUsers();
                displayCurrentUser();
                displayUserPhoto();
                setPreferredSize(new Dimension(860, 700));
                pack();
                chatClient = new ChatClient("localhost", 5000, username);
                try {
                        chatClient.connect();
                        chatClient.setMessageHandler(this::handleIncomingMessage);
                        chatClient.listenForMessages();
                } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Failed to connect to chat server.");
                }
        }

        private void handleIncomingMessage(String message) {
                SwingUtilities.invokeLater(() -> {
                        String[] parts = message.split(": ", 2);
                        if (parts.length == 2) {
                                String sender = parts[0];
                                String content = parts[1];

                                // Only display the message if it's not from the current user
                                if (!sender.equals(this.username)) {
                                        boolean isOwnMessage = false;
                                        appendToChat(sender, content, isOwnMessage);
                                }
                        }
                });
        }

        private void onUserSelected(User user) {
                this.selectedUser = user;
                displayChat();
        }

        private void displayChat() {
                jPanel3.removeAll();

                JLabel selectedUserLabel = new JLabel("Chat with " + selectedUser.getUsername());
                selectedUserLabel.setHorizontalAlignment(JLabel.CENTER);
                selectedUserLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                chatArea = new JTextPane();
                chatArea.setEditable(false);
                chatScrollPane = new JScrollPane(chatArea);

                jPanel3.setLayout(new BorderLayout());
                jPanel3.add(selectedUserLabel, BorderLayout.NORTH);
                jPanel3.add(chatScrollPane, BorderLayout.CENTER);

                loadChatHistory();

                jPanel3.revalidate();
                jPanel3.repaint();
        }

        public Home() {

        }

        private void loadChatHistory() {
                if (selectedUser == null)
                        return;

                User currentUser = userDAO.getUserByEmail(this.email);
                List<Message> messages = messageController.getMessagesBetweenUsers(
                                currentUser.getId(),
                                selectedUser.getId());

                chatArea.setText("");
                for (Message message : messages) {
                        displayMessage(message, currentUser);
                }
        }

        private Connection getConnection() {
                try {
                        return DriverManager.getConnection("jdbc:mysql://localhost:3306/leddatabase", "root",
                                        "9848688463");
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        private void displayMessage(Message message, User currentUser) {
                String sender = (message.getSenderId() == currentUser.getId()) ? "You"
                                : getUsernameById(message.getSenderId());
                String content = message.getContent();
                boolean isOwnMessage = message.getSenderId() == currentUser.getId();

                appendToChat(sender, content, isOwnMessage);
        }

        private void appendToChat(String sender, String content, boolean isOwnMessage) {
                StyledDocument doc = chatArea.getStyledDocument();
                SimpleAttributeSet keyWord = new SimpleAttributeSet();

                if (!isOwnMessage) {
                        // Set the message to be displayed on the right side
                        StyleConstants.setForeground(keyWord, Color.BLUE);
                        StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_RIGHT);
                } else {
                        // Set the message to be displayed on the left side
                        StyleConstants.setForeground(keyWord, Color.BLACK);
                        StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_LEFT);
                }

                StyleConstants.setBold(keyWord, true);

                try {
                        doc.insertString(doc.getLength(), sender + ": ", keyWord);
                        StyleConstants.setBold(keyWord, false);
                        doc.insertString(doc.getLength(), content + "\n", keyWord);
                } catch (BadLocationException e) {
                        e.printStackTrace();
                }
        }

        private String getUsernameById(int userId) {
                User user = userDAO.getUserById(userId);
                return user != null ? user.getUsername() : "Unknown User";
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

        private void logout() {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?",
                                "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                        this.dispose(); // Close the current window
                        new LoginScreen().setVisible(true); // Open the login window
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

                                userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                onUserSelected(user);
                                        }
                                });

                                userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
                                userPanel.setBackground(new Color(220, 220, 220)); // Light gray background
                                userPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding
                                userPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to the left

                                // Fetch and display user photo
                                byte[] photoData = userDAO.getUserPhoto(user.getId());
                                JLabel photoLabel;
                                if (photoData != null && photoData.length > 0) {
                                        ImageIcon icon = new ImageIcon(photoData);
                                        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                                        photoLabel = new JLabel(new ImageIcon(img));
                                } else {
                                        photoLabel = new JLabel("No photo");
                                }
                                photoLabel.setPreferredSize(new Dimension(40, 40));
                                userPanel.add(photoLabel);

                                // Add gap between photo and username
                                userPanel.add(Box.createRigidArea(new Dimension(15, 0)));

                                // Display username
                                JLabel userLabel = new JLabel(user.getUsername());
                                userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                                userPanel.add(userLabel);

                                // Add glue to push everything to the left
                                userPanel.add(Box.createHorizontalGlue());

                                // Wrap userPanel in another panel to make it take full width
                                JPanel wrapperPanel = new JPanel(new BorderLayout());
                                wrapperPanel.setBackground(new Color(240, 240, 240)); // Slightly lighter gray for
                                wrapperPanel.add(userPanel, BorderLayout.CENTER);
                                wrapperPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                                                wrapperPanel.getPreferredSize().height));

                                jPanel1.add(wrapperPanel);
                                jPanel1.add(Box.createRigidArea(new Dimension(0, 1))); // Add space between user panels
                        }

                        // Add vertical glue to push buttons to the bottom
                        jPanel1.add(Box.createVerticalGlue());

                        // Create a panel for buttons
                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        // buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 1));

                        // Add settings button
                        JButton settingsButton = new JButton("View Profile");
                        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                        settingsButton.setMaximumSize(
                                        new Dimension(Integer.MAX_VALUE, settingsButton.getPreferredSize().height));
                        settingsButton.addActionListener(e -> openSettings());
                        buttonPanel.add(settingsButton);

                        // Add some vertical space between buttons
                        buttonPanel.add(Box.createRigidArea(new Dimension(0, 3)));

                        // Add logout button
                        JButton logoutButton = new JButton("Logout");
                        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                        logoutButton.setMaximumSize(
                                        new Dimension(Integer.MAX_VALUE, logoutButton.getPreferredSize().height));
                        logoutButton.addActionListener(e -> logout());
                        buttonPanel.add(logoutButton);
                        // Add some vertical space below the logout button
                        buttonPanel.add(Box.createRigidArea(new Dimension(0, 2)));

                        // Add the button panel to jPanel1
                        jPanel1.add(buttonPanel);

                        jPanel1.revalidate();
                        jPanel1.repaint();
                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error displaying users: " + e.getMessage());
                }
        }


        private void openSettings() {
                UserProfileView userProfileView = new UserProfileView(this.email, this.getHomeReference());
                userProfileView.setVisible(true);
        }

        private void sendMessage() {
                if (selectedUser == null) {
                        JOptionPane.showMessageDialog(this, "Please select a user to chat with.");
                        return;
                }

                String content = jTextField1.getText();
                if (!content.isEmpty()) {
                        User currentUser = this.userDAO.getUserByEmail(this.email);
                        Message message = new Message(currentUser.getId(), selectedUser.getId(), content, new Date());
                        messageDAO.sendMessage(message);

                        // Display the message locally
                        appendToChat("You", content, true);

                        // Send the message to the server
                        chatClient.sendMessage(content);

                        jTextField1.setText("");
                }
        }

        @SuppressWarnings("unused")
        private void displayLocalMessage(Message message, User currentUser) {
                String sender = "You";
                chatArea.setText(chatArea.getText() + sender + ": " + message.getContent() + "\n");
        }

        @Override
        public void dispose() {
                chatClient.disconnect();
                super.dispose();
        }

        public Home getHomeReference() {
                return this;
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
                jPanel3 = new javax.swing.JPanel();
                jPanel3.setLayout(new BorderLayout());

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
