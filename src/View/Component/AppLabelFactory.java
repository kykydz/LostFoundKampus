package View.Component;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public final class AppLabelFactory {

    private AppLabelFactory() {
    }

    public static JLabel title(String text) {
        return create(text, AppTheme.TITLE_FONT, AppTheme.PRIMARY, JLabel.CENTER);
    }

    public static JLabel sectionTitle(String text) {
        return create(text, AppTheme.SECTION_TITLE_FONT, AppTheme.TEXT_PRIMARY, JLabel.LEFT);
    }

    public static JLabel body(String text) {
        return create(text, AppTheme.BODY_FONT, AppTheme.TEXT_SECONDARY, JLabel.LEFT);
    }

    public static JLabel inverseSectionTitle(String text) {
        return create(text, AppTheme.SECTION_TITLE_FONT, AppTheme.TEXT_ON_PRIMARY, JLabel.LEFT);
    }

    public static JLabel cardTitle(String text) {
        return create(text, AppTheme.CARD_TITLE_FONT, AppTheme.TEXT_ON_PRIMARY, JLabel.CENTER);
    }

    public static JLabel create(String text, Font font, Color color, int horizontalAlignment) {
        JLabel label = new JLabel(text, horizontalAlignment);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }
}
