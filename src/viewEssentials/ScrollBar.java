
package viewEssentials;

import viewEssentials.ModernScrollBarUI;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {
     public ScrollBar() {
         setUI(new ModernScrollBarUI());
         setPreferredSize(new Dimension(6,6));
     }
}
