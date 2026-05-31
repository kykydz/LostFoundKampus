package View.Component;

import java.awt.Color;
import javax.swing.JButton;

public final class AppButtonFactory {

    private AppButtonFactory() {
    }

    public static JButton primary(String text) {
        return create(text, AppTheme.PRIMARY);
    }

    public static JButton success(String text) {
        return create(text, AppTheme.SUCCESS);
    }

    public static JButton warning(String text) {
        return create(text, AppTheme.WARNING);
    }

    public static JButton danger(String text) {
        return create(text, AppTheme.DANGER);
    }

    private static JButton create(String text, Color background) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(resolveForeground(background));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(AppTheme.BUTTON_FONT);
        return button;
    }

    private static Color resolveForeground(Color background) {
        double brightness = (
            (background.getRed() * 0.299)
                + (background.getGreen() * 0.587)
                + (background.getBlue() * 0.114)
        );

        return brightness >= 170 ? AppTheme.TEXT_ON_LIGHT : AppTheme.TEXT_ON_PRIMARY;
    }
}
