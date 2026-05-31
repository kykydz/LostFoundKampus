package View.Component;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class LabeledInput extends JPanel {

    private final JComponent input;

    private LabeledInput(String labelText, JComponent inputField) {
        this.input = inputField;

        setLayout(new BorderLayout(8, 4));
        setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(AppTheme.LABEL_FONT);
        label.setForeground(AppTheme.TEXT_PRIMARY);

        if (inputField instanceof JTextComponent textComponent) {
            textComponent.setFont(AppTheme.BODY_FONT);
            textComponent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.PRIMARY, 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
            ));
        }

        add(label, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
    }

    public static LabeledInput text(String labelText, int columns) {
        JTextField field = new JTextField(columns);
        return new LabeledInput(labelText, field);
    }

    public static LabeledInput password(String labelText, int columns) {
        JPasswordField field = new JPasswordField(columns);
        return new LabeledInput(labelText, field);
    }

    public String getText() {
        if (input instanceof JTextField textField) {
            return textField.getText();
        }
        return "";
    }

    public char[] getPassword() {
        if (input instanceof JPasswordField passwordField) {
            return passwordField.getPassword();
        }
        return new char[0];
    }

    public void setText(String value) {
        if (input instanceof JTextComponent textComponent) {
            textComponent.setText(value);
        }
    }

    public void clear() {
        setText("");
    }
}
