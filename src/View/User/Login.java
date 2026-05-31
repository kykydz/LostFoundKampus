/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */

import Controller.ControllerLogin;
import Model.User.ModelUser;
import View.Components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField tfUsername;

    private JPasswordField pfPassword;

    private CustomButton btnLogin;

    private JButton btnRegister;

    private ControllerLogin controller;

    public Login() {

        controller = new ControllerLogin();

        initComponents();
    }

    private void initComponents() {

        setTitle("Lost & Found Kampus");

        setSize(950,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,2));

        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(new Color(241,245,249));

        leftPanel.setLayout(new BoxLayout(
                leftPanel,
                BoxLayout.Y_AXIS
        ));

        leftPanel.add(Box.createVerticalGlue());

        JLabel lblTitle = new JLabel("Lost & Found Kampus");

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 30)
        );

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(lblTitle);

        leftPanel.add(Box.createVerticalStrut(15));

        JLabel lblDesc = new JLabel(
                "Temukan barangmu, berbagi kebaikan"
        );

        lblDesc.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(lblDesc);

        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel);

        JPanel rightPanel = new JPanel(
                new GridBagLayout()
        );

        rightPanel.setBackground(Color.WHITE);

        JPanel card = new JPanel();

        card.setPreferredSize(new Dimension(350,320));

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

        JLabel lblLogin = new JLabel("Masuk ke akun Anda");

        lblLogin.setFont(
                new Font("SansSerif", Font.BOLD, 22)
        );

        lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblLogin);

        card.add(Box.createVerticalStrut(30));

        card.add(new JLabel("Username"));

        card.add(Box.createVerticalStrut(8));

        tfUsername = new JTextField();

        tfUsername.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(tfUsername);

        card.add(Box.createVerticalStrut(20));

        card.add(new JLabel("Password"));

        card.add(Box.createVerticalStrut(8));

        pfPassword = new JPasswordField();

        pfPassword.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        card.add(pfPassword);

        card.add(Box.createVerticalStrut(30));

        btnLogin = new CustomButton("LOGIN");

        btnLogin.addActionListener(e -> login());

        card.add(btnLogin);

        card.add(Box.createVerticalStrut(15));

        btnRegister = new JButton(
                "Belum punya akun? Register"
        );

        btnRegister.setBorderPainted(false);

        btnRegister.setContentAreaFilled(false);

        btnRegister.setForeground(
                new Color(37,99,235)
        );

        btnRegister.addActionListener(e -> {

            new Register().setVisible(true);

            dispose();
        });

        card.add(btnRegister);

        rightPanel.add(card);

        add(rightPanel);

        setVisible(true);
    }

    private void login(){

        try {

            String username = tfUsername.getText();

            String password = String.valueOf(
                    pfPassword.getPassword()
            );

            ModelUser user = controller.login(
                    username,
                    password
            );

            if(user != null){

                JOptionPane.showMessageDialog(
                        this,
                        "Login berhasil"
                );

                new DashboardUser(user).setVisible(true);

                dispose();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Username / password salah"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}
