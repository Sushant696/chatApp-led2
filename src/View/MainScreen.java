
package View;

import viewEssentials.SideBar;
import viewEssentials.Chat_Body;
import net.miginfocom.swing.MigLayout;

public class MainScreen extends javax.swing.JPanel {


    public MainScreen() {
        initComponents();
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("fillx ,filly","0[]5[]5[]0", ""));
        this.add(new SideBar());
        this.add(new Chat_Body());
//        this.add(new MessageBody());
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
