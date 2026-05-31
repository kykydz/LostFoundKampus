/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ControllerHome;
import Controller.HomeViewContract;
import View.User.Login;
import View.User.Register;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
/**
 *
 * @author Ivaa
 */
public class HomeView extends JFrame implements HomeViewContract {

    private final transient ControllerHome controller;
    private final JButton btnLogin;
    private final JButton btnRegister;
    private final JButton btnExit;

    public HomeView(){

        this.controller = new ControllerHome(this);

        setTitle("Lost & Found Kampus");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.setBackground(new Color(44,62,80));

        JLabel title = new JLabel("LOST & FOUND KAMPUS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(Color.WHITE);
        title.setBounds(95,40,320,40);

        JLabel subtitle =
                new JLabel(
                        "Aplikasi Barang Hilang & Ditemukan"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(105,90,320,30);

        btnLogin = new JButton("LOGIN");

        btnLogin.setBounds(
                150,
                160,
                180,
                40
        );

        styleButton(btnLogin);

        btnRegister = new JButton("REGISTER");

        btnRegister.setBounds(
                150,
                220,
                180,
                40
        );

        styleButton(btnRegister);

        btnExit = new JButton("EXIT");

        btnExit.setBounds(
                150,
                280,
                180,
                40
        );
        styleButton(btnExit);

        panel.add(title);
        panel.add(subtitle);
        panel.add(btnLogin);
        panel.add(btnRegister);
        panel.add(btnExit);

        add(panel);

        btnLogin.addActionListener(loginEventHandler());
        btnRegister.addActionListener(registerEventHandler());
        btnExit.addActionListener(exitEventHandler());
    }

    private ActionListener loginEventHandler() {
        return e -> controller.handleOpenLogin();
    }

    private ActionListener registerEventHandler() {
        return e -> controller.handleOpenRegister();
    }

    private ActionListener exitEventHandler() {
        return e -> controller.handleExit();
    }

    private void styleButton(JButton button){
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(44,62,80));
        button.setFont( new Font("Segoe UI", Font.BOLD, 14));

        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder( new LineBorder(Color.WHITE, 1, true ));
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
