/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ControllerHome;
import Controller.HomeViewContract;
import View.Component.AppButtonFactory;
import View.Component.AppCard;
import View.Component.AppHeader;
import View.Component.AppTheme;
import View.User.Login;
import View.User.Register;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Ivaa
 */
public class HomeView extends JFrame implements HomeViewContract {

    public HomeView(){

        ControllerHome controller = new ControllerHome(this);

        setTitle("Lost & Found Kampus");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBackground(AppTheme.BACKGROUND);

        AppCard card = new AppCard();

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        AppHeader header = new AppHeader(
                "LOST & FOUND KAMPUS",
                "Aplikasi Barang Hilang & Ditemukan",
                AppTheme.TEXT_PRIMARY,
                AppTheme.TEXT_SECONDARY
        );

        JButton btnLogin = AppButtonFactory.primary("LOGIN");
        JButton btnRegister = AppButtonFactory.success("REGISTER");
        JButton btnExit = AppButtonFactory.danger("EXIT");

        Dimension buttonSize = new Dimension(220, 40);
        btnLogin.setMaximumSize(buttonSize);
        btnRegister.setMaximumSize(buttonSize);
        btnExit.setMaximumSize(buttonSize);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(header);
        contentPanel.add(Box.createVerticalStrut(28));
        contentPanel.add(btnLogin);
        contentPanel.add(Box.createVerticalStrut(14));
        contentPanel.add(btnRegister);
        contentPanel.add(Box.createVerticalStrut(14));
        contentPanel.add(btnExit);

        card.setContent(contentPanel);
        rootPanel.add(card);

        add(rootPanel);

        btnLogin.addActionListener(controller::handleOpenLogin);
        btnRegister.addActionListener(controller::handleOpenRegister);
        btnExit.addActionListener(controller::handleExit);
    }


    @Override
    public void openLogin() {
        dispose();
        new Login().setVisible(true);
    }

    @Override
    public void openRegister() {
        dispose();
        new Register().setVisible(true);
    }

    @Override
    public boolean confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Keluar aplikasi?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        return confirm == JOptionPane.YES_OPTION;
    }

    @Override
    public void exitApplication() {
        System.exit(0);
    }
}
