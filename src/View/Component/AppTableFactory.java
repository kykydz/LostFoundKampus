package View.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public final class AppTableFactory {

    private AppTableFactory() {
    }

    public static void style(JTable table) {
        table.setFont(AppTheme.BODY_FONT);
        table.setRowHeight(25);
        table.setGridColor(AppTheme.BACKGROUND);
        table.setSelectionBackground(AppTheme.PRIMARY);
        table.setSelectionForeground(AppTheme.TEXT_ON_PRIMARY);

        JTableHeader header = table.getTableHeader();
        header.setBackground(AppTheme.PRIMARY);
        header.setForeground(AppTheme.TEXT_ON_PRIMARY);
        header.setFont(AppTheme.BUTTON_FONT);
    }

    public static void styleSearchField(JTextField field) {
        field.setFont(AppTheme.BODY_FONT);
        field.setBorder(BorderFactory.createLineBorder(AppTheme.PRIMARY, 2));
    }
}

