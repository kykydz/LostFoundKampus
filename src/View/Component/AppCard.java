package View.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AppCard extends JPanel {

    public AppCard() {
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(AppTheme.SURFACE);
        setBorder(
            new CompoundBorder(
                new LineBorder(new Color(0, 0, 0, 24), 1, true),
                new EmptyBorder(28, 28, 28, 28)
            )
        );
    }

    public void setContent(JPanel panel) {
        removeAll();
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(380, 280);
    }
}

