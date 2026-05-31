/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;


/**
 *
 * @author Ivaa
 */

import View.Components.Sidebar;
import View.Components.CustomButton;
import Controller.ControllerBarang;
import Model.Barang.ModelBarang;

import javax.swing.*;
import java.awt.*;

public class InputBarang extends JFrame {

    private JTextField tfNama;

    private JComboBox<String> cbKategori;

    private JTextField tfLokasi;

    private JTextArea taDeskripsi;

    private JComboBox<String> cbStatus;

    private CustomButton btnSimpan;

    private JButton btnBack;
    
    private ControllerBarang controller;

    public InputBarang() {
        controller = new ControllerBarang();
        initComponents();
    }

    private void initComponents() {

        setTitle("Input Barang");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(new Sidebar(this), BorderLayout.WEST);

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(
                new Color(241,245,249)
        );

        add(mainPanel, BorderLayout.CENTER);

        // HEADER
        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBackground(Color.WHITE);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20,25,20,25
                )
        );

        JLabel lblTitle = new JLabel(
                "Input Barang"
        );

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        header.add(lblTitle, BorderLayout.WEST);

        btnBack = new JButton("← Kembali");

        btnBack.addActionListener(e -> {

            new ViewBarang().setVisible(true);

            dispose();
        });

        header.add(btnBack, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // CONTENT
        JPanel content = new JPanel(
                new GridBagLayout()
        );

        content.setBackground(
                new Color(241,245,249)
        );

        JPanel formCard = new JPanel();

        formCard.setPreferredSize(
                new Dimension(500,450)
        );

        formCard.setBackground(Color.WHITE);

        formCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220,220,220)
                        ),
                        BorderFactory.createEmptyBorder(
                                25,25,25,25
                        )
                )
        );

        formCard.setLayout(new BoxLayout(
                formCard,
                BoxLayout.Y_AXIS
        ));

        // NAMA
        formCard.add(new JLabel("Nama Barang"));

        formCard.add(Box.createVerticalStrut(8));

        tfNama = new JTextField();

        tfNama.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        formCard.add(tfNama);

        formCard.add(Box.createVerticalStrut(18));

        // KATEGORI
        formCard.add(new JLabel("Kategori"));

        formCard.add(Box.createVerticalStrut(8));

            cbKategori = new JComboBox<>();

            cbKategori.addItem("Elektronik");

            cbKategori.addItem("Aksesoris");

            cbKategori.addItem("Dokumen");

            cbKategori.addItem("Pakaian");

            cbKategori.addItem("Lainnya");

            cbKategori.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            40
                    )
            );

            formCard.add(cbKategori);

        formCard.add(Box.createVerticalStrut(18));

        // LOKASI
        formCard.add(new JLabel("Lokasi"));

        formCard.add(Box.createVerticalStrut(8));

        tfLokasi = new JTextField();

        tfLokasi.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        formCard.add(tfLokasi);

        formCard.add(Box.createVerticalStrut(18));

        // STATUS
        formCard.add(new JLabel("Status"));

        formCard.add(Box.createVerticalStrut(8));

        cbStatus = new JComboBox<>();

        cbStatus.addItem("Hilang");

        cbStatus.addItem("Ditemukan");

        cbStatus.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        formCard.add(cbStatus);

        formCard.add(Box.createVerticalStrut(18));

        // DESKRIPSI
        formCard.add(new JLabel("Deskripsi"));

        formCard.add(Box.createVerticalStrut(8));

        taDeskripsi = new JTextArea(5,20);

        JScrollPane scrollPane =
                new JScrollPane(taDeskripsi);

        formCard.add(scrollPane);

        formCard.add(Box.createVerticalStrut(25));

        btnSimpan = new CustomButton(
                "SIMPAN"
        );

        formCard.add(btnSimpan);
        btnSimpan.addActionListener(e -> simpanBarang());

        content.add(formCard);

        mainPanel.add(content, BorderLayout.CENTER);

        setVisible(true);
    }
    private void simpanBarang(){

        try {

            ModelBarang barang =
                    new ModelBarang();

            barang.setNamaBarang(
                    tfNama.getText()
            );
            barang.setKategori(
                    cbKategori
                .getSelectedItem()
                .toString()
            );
            barang.setLokasi(
                    tfLokasi.getText()
            );

            barang.setDeskripsi(
                    taDeskripsi.getText()
            );

            barang.setStatus(
                    cbStatus.getSelectedItem().toString()
            );

            barang.setStatusClaim("None");

            barang.setUserId(1);

            controller.insert(barang);

            JOptionPane.showMessageDialog(
                    this,
                    "Barang berhasil disimpan!"
            );

            new ViewBarang().setVisible(true);

            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}