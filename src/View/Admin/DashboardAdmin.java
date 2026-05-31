/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Model.User.UserSession;
import View.Component.AppButtonFactory;
import View.Component.AppFrame;
import View.Component.AppLabelFactory;
import View.Component.AppTheme;
import javax.swing.*;

public class DashboardAdmin extends AppFrame {

    public DashboardAdmin() {
        this(null);
    }

    public DashboardAdmin(JFrame parentFrame) {
        super("Dashboard Admin", AppTheme.WINDOW_DASHBOARD, parentFrame);

        setLayout(null);

        // =========================
        // SIDEBAR
        // =========================

        JPanel sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBounds(0,0,220,600);

        sidebar.setBackground(
                AppTheme.SIDEBAR
        );

        JLabel lblMenu = AppLabelFactory.inverseSectionTitle("ADMIN MENU");

        lblMenu.setBounds(35,30,200,30);

        JButton btnViewBarang = AppButtonFactory.primary("VIEW BARANG");

        btnViewBarang.setBounds(
                20,
                100,
                180,
                40
        );

        JButton btnInputBarang = AppButtonFactory.success("INPUT BARANG");

        btnInputBarang.setBounds(
                20,
                160,
                180,
                40
        );

        JButton btnLogout = AppButtonFactory.danger("LOGOUT");

        btnLogout.setBounds(
                20,
                220,
                180,
                40
        );

        JButton btnBack = hasParentFrame() ? AppButtonFactory.warning("BACK") : null;
        if (btnBack != null) {
            btnBack.setBounds(
                    20,
                    280,
                    180,
                    40
            );
        }

        sidebar.add(lblMenu);

        sidebar.add(btnViewBarang);

        sidebar.add(btnInputBarang);

        sidebar.add(btnLogout);
        if (btnBack != null) {
            sidebar.add(btnBack);
        }

        add(sidebar);

        JPanel content = new JPanel();

        content.setLayout(null);

        content.setBounds(220,0,680,600);

        content.setBackground(
                AppTheme.BACKGROUND
        );

        // =========================
        // TITLE DASHBOARD
        // =========================

        JLabel title = AppLabelFactory.title("DASHBOARD ADMIN");

        title.setBounds(
                180,
                30,
                400,
                40
        );

        // =========================
        // CARD 1
        // =========================

        JPanel card1 = new JPanel();

        card1.setBounds(
                60,
                120,
                180,
                120
        );

        card1.setBackground(
                AppTheme.PRIMARY
        );

        JLabel lbl1 = AppLabelFactory.cardTitle("Barang Hilang");

        card1.add(lbl1);

        // =========================
        // CARD 2
        // =========================

        JPanel card2 = new JPanel();

        card2.setBounds(
                260,
                120,
                180,
                120
        );

        card2.setBackground(
                AppTheme.SUCCESS
        );

        JLabel lbl2 = AppLabelFactory.cardTitle("Barang Ditemukan");

        card2.add(lbl2);

        // =========================
        // CARD 3
        // =========================

        JPanel card3 = new JPanel();

        card3.setBounds(
                460,
                120,
                180,
                120
        );

        card3.setBackground(
                AppTheme.WARNING
        );

        JLabel lbl3 = AppLabelFactory.cardTitle("Sudah Diklaim");

        card3.add(lbl3);

        // =========================
        // INFO TEXT
        // =========================

        JLabel info = AppLabelFactory.body("Selamat datang di sistem Lost & Found Kampus");

        info.setBounds(
                120,
                320,
                500,
                30
        );

        // =========================
        // ADD CONTENT
        // =========================

        content.add(title);

        content.add(card1);

        content.add(card2);

        content.add(card3);

        content.add(info);

        add(content);

        // =========================
        // ACTION BUTTON
        // =========================

        btnViewBarang.addActionListener(e -> showChildFrame(new ViewBarang(this)));

        btnInputBarang.addActionListener(e -> showChildFrame(new InputBarang(this)));

        if (btnBack != null) {
            btnBack.addActionListener(e -> backToParent());
        }

        btnLogout.addActionListener(e -> {
            UserSession.clear();
            dispose();
            new View.User.Login().setVisible(true);
        });
    }

}
