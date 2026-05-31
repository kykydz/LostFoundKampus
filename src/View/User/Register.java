/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */

import Controller.ControllerUser;
import View.Components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    private JTextField tfNama;

    private JTextField tfUsername;

    private JPasswordField pfPassword;

    private JPasswordField pfConfirm;

    private CustomButton btnRegister;

    private JButton btnBack;

    private ControllerUser controller;

    public Register() {

        controller = new ControllerUser();

        initComponents();
    }

    private void initComponents() {

        setTitle("Register - Lost & Found Kampus");

        setSize(950,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,2));

        // LEFT PANEL
        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(new Color(241,245,249));

        leftPanel.setLayout(new BoxLayout(
                leftPanel,
                BoxLayout.Y_AXIS
        ));

        leftPanel.add(Box.createVerticalGlue());

        JLabel lblTitle = new JLabel(
                "Buat Akun Baru"
        );

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 30)
        );

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(lblTitle);

        leftPanel.add(Box.createVerticalStrut(15));

        JLabel lblDesc = new JLabel(
                "Daftar untuk mulai menggunakan aplikasi."
        );

        lblDesc.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(lblDesc);

        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel);

        // RIGHT PANEL
        JPanel rightPanel = new JPanel(
                new GridBagLayout()
        );

        rightPanel.setBackground(Color.WHITE);

        JPanel card = new JPanel();

        card.setPreferredSize(new Dimension(360,420));

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220,220,220)
                        ),
                        BorderFactory.createEmptyBorder(
                                30,30,30,30
                        )
                )
        );

        card.setLayout(new BoxLayout(
                card,
                BoxLayout.Y_AXIS
        ));

        JLabel lblRegister = new JLabel(
                "Register"
        );

        lblRegister.setFont(
                new Font("SansSerif", Font.BOLD, 22)
        );

        lblRegister.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(lblRegister);

        card.add(Box.createVerticalStrut(25));

        card.add(new JLabel("Nama Lengkap"));

        card.add(Box.createVerticalStrut(8));

        tfNama = new JTextField();

        tfNama.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(tfNama);

        card.add(Box.createVerticalStrut(18));

        card.add(new JLabel("Username"));

        card.add(Box.createVerticalStrut(8));

        tfUsername = new JTextField();

        tfUsername.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(tfUsername);

        card.add(Box.createVerticalStrut(18));

        card.add(new JLabel("Password"));

        card.add(Box.createVerticalStrut(8));

        pfPassword = new JPasswordField();

        pfPassword.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(pfPassword);

        card.add(Box.createVerticalStrut(18));

        card.add(new JLabel("Konfirmasi Password"));

        card.add(Box.createVerticalStrut(8));

        pfConfirm = new JPasswordField();

        pfConfirm.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(pfConfirm);

        card.add(Box.createVerticalStrut(28));

        btnRegister = new CustomButton(
                "REGISTER"
        );

        btnRegister.addActionListener(e -> register());

        card.add(btnRegister);

        card.add(Box.createVerticalStrut(15));

        btnBack = new JButton(
                "← Kembali ke Login"
        );

        btnBack.setBorderPainted(false);

        btnBack.setContentAreaFilled(false);

        btnBack.setForeground(
                new Color(37,99,235)
        );

        btnBack.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        btnBack.addActionListener(e -> {

            new Login().setVisible(true);

            dispose();
        });

        card.add(btnBack);

        rightPanel.add(card);

        add(rightPanel);

        setVisible(true);
    }

    private void register() {

        try {

            controller.register(
                    tfNama.getText(),
                    tfUsername.getText(),
                    String.valueOf(
                            pfPassword.getPassword()
                    ),
                    String.valueOf(
                            pfConfirm.getPassword()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Register berhasil!"
            );

            new Login().setVisible(true);

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}