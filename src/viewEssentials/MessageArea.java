
package viewEssentials;

import viewEssentials.Chat_Left_With_Profile;
import viewEssentials.Chat_Right;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class MessageArea extends javax.swing.JPanel {

    public MessageArea() {
        initComponents();
        init();
        addItemLeft("does not word wrap it at all showing all the text "
                + "in one line only instead. It would be interesting to support word wrap on does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on");
        addItemRight("hello\nerererew\newewe");
        addItemLeft("hello\nerererew\newewe");
        addItemRight("does not word wrap it at all showing all the text "
                + "in one line only instead. It would be interesting to support word wrap on does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on");
        addItemLeft("does not word wrap it at all showing all the text "
                + "in one line only instead. It would be interesting to support word wrap on does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on");
        addItemRight("hello\nerererew\newewe");
        addItemLeft("hello\nerererew\newewe");
        addItemRight("does not word wrap it at all showing all the text "
                + "in one line only instead. It would be interesting to support word wrap on does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on");

    }
    
    private void init() {

        jPanel1.setLayout(new MigLayout("fillx", "", "10[]10"));
        jScrollPane1.setVerticalScrollBar(new ScrollBar());
    }
    
     public void endOfChat() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    
    public void addItemLeft(String text) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        jPanel1.add(item, "wrap, w ::80%");
        jPanel1.repaint();
        jPanel1.revalidate();
        endOfChat();
    }
    
     public void addItemRight(String text) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        jPanel1.add(item, "wrap, al right,  w ::80%");
        jPanel1.repaint();
        jPanel1.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
