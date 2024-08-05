//package view;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Settings extends javax.swing.JFrame {
//    private String name;
//    private static String email;
//    private byte[] imageData;
//    private javax.swing.JPanel p1;
//    private javax.swing.JPanel p2;
//    private javax.swing.JLabel profile;
//    private javax.swing.JButton accountSettingBtn;
//    private javax.swing.JButton profileBtn;
//    private javax.swing.JButton securityBtn;
//    private javax.swing.JLabel emailLabel;
//    private javax.swing.JLabel username;
//
//    public Settings(String email) {
//        this.email = email;
//        initComponents();
//        fetchData();
//        customizeComponents();
//    }
//
//    private void initComponents() {
//        p1 = new javax.swing.JPanel();
//        p2 = new javax.swing.JPanel();
//        profile = new javax.swing.JLabel();
//        accountSettingBtn = new javax.swing.JButton();
//        profileBtn = new javax.swing.JButton();
//        securityBtn = new javax.swing.JButton();
//        emailLabel = new javax.swing.JLabel();
//        username = new javax.swing.JLabel();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        p1.setBackground(new java.awt.Color(16, 29, 112));
//        p2.setBackground(new java.awt.Color(33, 35, 54));
//
//        accountSettingBtn.setText("Account Setting");
//        profileBtn.setText("Profile");
//        securityBtn.setText("Security");
//
//        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
//        emailLabel.setText("Email: ");
//        username.setForeground(new java.awt.Color(255, 255, 255));
//        username.setText("Name: ");
//
//        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
//        p1.setLayout(p1Layout);
//        p1Layout.setHorizontalGroup(
//            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(p1Layout.createSequentialGroup()
//                    .addGap(150, 150, 150)
//                    .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(accountSettingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(securityBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addContainerGap(10, Short.MAX_VALUE))
//                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p1Layout.createSequentialGroup()
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGap(150, 150, 150))
//        );
//        p1Layout.setVerticalGroup(
//            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(p1Layout.createSequentialGroup()
//                    .addGap(20, 20, 20)
//                    .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGap(50, 50, 50)
//                    .addComponent(accountSettingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGap(30, 30, 30)
//                    .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGap(30, 30, 30)
//                    .addComponent(securityBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addContainerGap(150, Short.MAX_VALUE))
//        );
//
//        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
//        p2.setLayout(p2Layout);
//        p2Layout.setHorizontalGroup(
//            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(p2Layout.createSequentialGroup()
//                    .addGap(110, 110, 110)
//                    .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        p2Layout.setVerticalGroup(
//            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(p2Layout.createSequentialGroup()
//                    .addGap(100, 100, 100)
//                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addGap(50, 50, 50)
//                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addContainerGap(450, Short.MAX_VALUE))
//        );
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup()
//                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                    .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//
//        pack();
//    }
//
//    private void fetchData() {
//        try {
//            String query = "SELECT name, image_data FROM userDetails WHERE email = ?";
//            conn conn = new conn();
//            PreparedStatement pstmt = conn.c.prepareStatement(query);
//            pstmt.setString(1, email);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    // Retrieve name
//                    name = rs.getString("name");
//
//                    // Retrieve image data
//                    imageData = rs.getBytes("image_data");
//
//                    // Pass data to the Server class
//                } else {
//                    System.out.println("No record found with the provided email.");
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void customizeComponents() {
//        // Fetch and place image data
//        if (imageData != null) {
//            try {
//                InputStream in = new ByteArrayInputStream(imageData);
//                BufferedImage img = ImageIO.read(in);
//
//                // Create a circular version of the image
//                int diameter = 150;
//                BufferedImage circularImg = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
//                Graphics2D g2 = circularImg.createGraphics();
//
//                // Enable anti-aliasing
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//                // Draw the circular clip
//                Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);
//                g2.setClip(clip);
//
//                // Draw the image inside the circular clip
//                g2.drawImage(img, 0, 0, diameter, diameter, null);
//
//                // Dispose the graphics context
//                g2.dispose();
//
//                ImageIcon imageIcon = new ImageIcon(circularImg);
//                profile.setIcon(imageIcon);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        emailLabel.setText("Email: " + email);
//        username.setText("Name: " + name);
//    }
//
//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(() -> new Settings(email).setVisible(true));
//    }
//}
