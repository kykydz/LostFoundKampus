/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import View.Component.AppContentPanel;
import View.Component.AppFrame;
import View.Component.AppLabelFactory;
import View.Component.AppTheme;
import javax.swing.*;
import java.awt.BorderLayout;

public class EditBarang extends AppFrame {
    public EditBarang() {
        super("Edit Barang", AppTheme.WINDOW_COMPACT);

        JPanel panel = createScreenPanel();
        JPanel content = new AppContentPanel(new BorderLayout());
        JLabel label = AppLabelFactory.sectionTitle("FORM EDIT BARANG");

        content.add(label, BorderLayout.CENTER);
        panel.add(content, BorderLayout.CENTER);
        setScreenContent(panel);
    }
}
