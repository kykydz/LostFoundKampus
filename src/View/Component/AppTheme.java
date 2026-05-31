package View.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class AppTheme {

    public static final Color BACKGROUND = new Color(236, 240, 241);
    public static final Color SURFACE = Color.WHITE;
    public static final Color SIDEBAR = new Color(44, 62, 80);
    public static final Color PRIMARY = new Color(52, 152, 219);
    public static final Color DANGER = new Color(231, 76, 60);
    public static final Color SUCCESS = new Color(46, 204, 113);
    public static final Color WARNING = new Color(241, 196, 15);
    public static final Color TEXT_PRIMARY = new Color(44, 62, 80);
    public static final Color TEXT_SECONDARY = new Color(127, 140, 141);
    public static final Color TEXT_ON_PRIMARY = Color.WHITE;
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font SECTION_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font CARD_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);

    public static final Dimension WINDOW_AUTH = new Dimension(420, 320);
    public static final Dimension WINDOW_AUTH_REGISTER = new Dimension(420, 360);
    public static final Dimension WINDOW_HOME = new Dimension(520, 420);
    public static final Dimension WINDOW_FORM = new Dimension(560, 620);
    public static final Dimension WINDOW_TABLE = new Dimension(860, 560);
    public static final Dimension WINDOW_DASHBOARD = new Dimension(960, 640);
    public static final Dimension WINDOW_COMPACT = new Dimension(420, 320);

    private AppTheme() {
    }
}
