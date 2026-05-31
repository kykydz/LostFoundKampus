/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author karina
 */
import Controller.ControllerClaim;
import View.Components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class ModalClaimBarang extends JDialog {

    private JTextArea taAlasan;

    private CustomButton btnSubmit;

    private ControllerClaim controller;

    private int idBarang;

    private int idUser;

    public ModalClaimBarang(
            JFrame parent,
            int idBarang,
            int idUser
    ) {

        super(parent, true);

        this.idBarang = idBarang;

        this.idUser = idUser;

        controller = new ControllerClaim();

        initComponents();
    }

    private void initComponents() {

        setTitle("Claim Barang");

        setSize(450,350);

        setLocationRelativeTo(getParent());

        setLayout(new BorderLayout());

        JPanel content = new JPanel();

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        25,25,25,25
                )
        );

        content.setLayout(new BoxLayout(
                content,
                BoxLayout.Y_AXIS
        ));

        JLabel lblTitle = new JLabel(
                "Klaim Barang Ini"
        );

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 22)
        );

        lblTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        content.add(lblTitle);

        content.add(Box.createVerticalStrut(25));

        content.add(new JLabel("Alasan Claim"));

        content.add(Box.createVerticalStrut(8));

        taAlasan = new JTextArea(6,20);

        JScrollPane scrollPane =
                new JScrollPane(taAlasan);

        content.add(scrollPane);

        content.add(Box.createVerticalStrut(25));

        btnSubmit = new CustomButton(
                "SUBMIT CLAIM"
        );

        btnSubmit.addActionListener(e -> submitClaim());

        content.add(btnSubmit);

        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    private void submitClaim() {

        try {

            controller.submitClaim(
                    idBarang,
                    idUser,
                    taAlasan.getText()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Claim berhasil dikirim!"
            );

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}