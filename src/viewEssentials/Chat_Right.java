
package viewEssentials;


public class Chat_Right extends javax.swing.JLayeredPane {

  
    public Chat_Right() {
        initComponents();
    }
    
    public void setText (String text) {
        txt.setText(text);
        txt.setTime("10:35 PM");
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new viewEssentials.Chat_Item();

        setLayout(new java.awt.BorderLayout());

        txt.setBackground(new java.awt.Color(102, 153, 255));
        add(txt, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private viewEssentials.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
