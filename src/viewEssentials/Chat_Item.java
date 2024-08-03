package viewEssentials;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class Chat_Item extends javax.swing.JLayeredPane {

    public Chat_Item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(102,153,255));
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txt.setOpaque(false);
        
        // Set the custom invisible caret
        txt.setCaret(new InvisibleCaret());
    }
    
    public void setUserProfile(String user) {
        
    }


    public void setText(String text) {
        txt.setText(text);
        revalidate();
        repaint();
    }
    
//    To set the time of message send
    public void setTime(String time) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(time);
        label.setForeground(new Color(10, 10, 10));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }
    
//    To send a single tick after message is send
    public void sendSuccess() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/Components/tick.png")));
        }
    }
    
//    To send double tick when message is delivered
    public void seen() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/Components/double-tick.png")));
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        txt = new viewEssentials.JIMSendTextPane();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        txt.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txt.setToolTipText("");
        txt.setSelectionColor(new Color(102, 102, 255));
        
        // Add some margin around the text pane to see the rounded rectangle
        JPanel txtPanel = new JPanel();
        txtPanel.setOpaque(false);
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.LINE_AXIS));
        txtPanel.setBorder(BorderFactory.createEmptyBorder(2, 2,2, 2));
        txtPanel.add(txt);

        add(txtPanel);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        
        // Adjust the size to draw the rounded rectangle inside the borders
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(graphics);
    }

    // Custom Caret class to make the cursor invisible
    private static class InvisibleCaret extends DefaultCaret {
        @Override
        public void paint(Graphics g) {
            // Do nothing to make the caret invisible
        }

        @Override
        public boolean isVisible() {
            return false;
        }
    }

    // Variables declaration - do not modify
    private viewEssentials.JIMSendTextPane txt;
    // End of variables declaration
    
    private JLabel label;
}
