package View.Component;

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

    private static JButton create(String text, java.awt.Color background) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(AppTheme.TEXT_ON_PRIMARY);
        button.setFocusPainted(false);
        button.setFont(AppTheme.BUTTON_FONT);
        return button;
    }
}
