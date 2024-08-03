
package viewEssentials;

import javax.swing.Icon;


public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {

  
    public Chat_Left_With_Profile() {
        initComponents();
    }
    
    public void setText (String text) {
        txt.setText(text);
        txt.setTime("10:30 PM");
        txt.seen();
    }
    
    
//    To set specific profile photo
    public void setImageProfile (Icon image) {
        imageAvatar.setImage(image);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        imageAvatar = new viewEssentials.ImageAvatar();
        txt = new viewEssentials.Chat_Item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        imageAvatar.setBorderSize(0);
        imageAvatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/Components/profile.png"))); // NOI18N
        imageAvatar.setMaximumSize(new java.awt.Dimension(31, 31));
        imageAvatar.setMinimumSize(new java.awt.Dimension(31, 31));

        jLayeredPane1.setLayer(imageAvatar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(imageAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jLayeredPane1);

        txt.setBackground(new java.awt.Color(102, 153, 255));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private viewEssentials.ImageAvatar imageAvatar;
    private javax.swing.JLayeredPane jLayeredPane1;
    private viewEssentials.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
