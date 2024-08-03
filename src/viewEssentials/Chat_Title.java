/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package viewEssentials;

import java.awt.Color;

/**
 *
 * @author Dell
 */
public class Chat_Title extends javax.swing.JPanel {

    /**
     * Creates new form Chat_Title
     */
    public Chat_Title() {
        initComponents();
    }
   
    
    public void setUserName(String Name) {
        userName.setText(Name);
    }

    public void setStatusActive() {
        statusLabel.setText("Active now");
        statusLabel.setForeground(new java.awt.Color(35, 163, 59));
    }
    
    public void setStatusText(String text) {
        statusLabel.setText(text);
        statusLabel.setForeground(new Color(160,160,160));
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        userName = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));

        jLayeredPane1.setLayout(new java.awt.GridLayout(0, 1));

        userName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userName.setText("Name");
        jLayeredPane1.add(userName);

        statusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(35, 163, 59));
        statusLabel.setText("Active now");
        jLayeredPane1.add(statusLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

}