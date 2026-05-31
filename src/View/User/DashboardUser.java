/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

import Model.User.UserSession;
import View.Admin.InputBarang;
import View.Component.AppButtonFactory;
import View.Component.AppCard;
import View.Component.AppFrame;
import View.Component.AppHeader;
import View.Component.AppTheme;

import javax.swing.*;
import java.awt.*;

public class DashboardUser extends AppFrame {

    public DashboardUser() {
        this(null);
    }

    public DashboardUser(JFrame parentFrame){
        super("Dashboard User", AppTheme.WINDOW_AUTH_REGISTER, parentFrame);

        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBackground(AppTheme.BACKGROUND);

        AppCard card = new AppCard();

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        AppHeader header = new AppHeader(
                "DASHBOARD USER",
                "Input barang hilang/temuan lalu ajukan claim dari daftar barang"
        );

        JButton btnLihatBarang = AppButtonFactory.primary("LIHAT BARANG");
        JButton btnTambahBarang = AppButtonFactory.success("TAMBAH BARANG");
        JButton btnLogout = AppButtonFactory.danger("LOGOUT");
        JButton btnBack = hasParentFrame() ? AppButtonFactory.warning("BACK") : null;

        Dimension buttonSize = new Dimension(220, 40);
        btnLihatBarang.setMaximumSize(buttonSize);
        btnTambahBarang.setMaximumSize(buttonSize);
        btnLogout.setMaximumSize(buttonSize);
        btnLihatBarang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTambahBarang.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (btnBack != null) {
            btnBack.setMaximumSize(buttonSize);
            btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        contentPanel.add(header);
        contentPanel.add(Box.createVerticalStrut(28));
        contentPanel.add(btnLihatBarang);
        contentPanel.add(Box.createVerticalStrut(14));
        contentPanel.add(btnTambahBarang);
        contentPanel.add(Box.createVerticalStrut(14));
        if (btnBack != null) {
            contentPanel.add(btnBack);
            contentPanel.add(Box.createVerticalStrut(14));
        }
        contentPanel.add(btnLogout);

        card.setContent(contentPanel);
        rootPanel.add(card);

        add(rootPanel);

        btnLihatBarang.addActionListener(e -> showChildFrame(new LihatBarang(this)));

        btnTambahBarang.addActionListener(e -> showChildFrame(new InputBarang(this)));

        if (btnBack != null) {
            btnBack.addActionListener(e -> backToParent());
        }

        btnLogout.addActionListener(e -> {
            UserSession.clear();
            dispose();
            new Login().setVisible(true);
        });
    }
}