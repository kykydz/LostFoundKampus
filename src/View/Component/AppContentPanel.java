package View.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
public class AppContentPanel extends JPanel {
    public AppContentPanel(LayoutManager layout) {
        super(layout);
        setOpaque(true);
        setBackground(AppTheme.BACKGROUND);
        setBorder(new EmptyBorder(24, 24, 24, 24));
    }
}
