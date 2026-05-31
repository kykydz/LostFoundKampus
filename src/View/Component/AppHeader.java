package View.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AppHeader extends JPanel {

    public AppHeader(String titleText, String subtitleText) {
        this(titleText, subtitleText, AppTheme.TEXT_PRIMARY, AppTheme.TEXT_SECONDARY);
    }

    public AppHeader(String titleText, String subtitleText, Color titleColor, Color subtitleColor) {
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(titleText, SwingConstants.CENTER);
        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(titleColor);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(subtitleText, SwingConstants.CENTER);
        subtitleLabel.setFont(AppTheme.SUBTITLE_FONT);
        subtitleLabel.setForeground(subtitleColor);
        subtitleLabel.setAlignmentX(CENTER_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(subtitleLabel);

        add(textPanel, BorderLayout.CENTER);
    }
}

