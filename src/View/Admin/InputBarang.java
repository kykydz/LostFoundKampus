/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import Model.User.UserSession;
import View.Component.AppButtonFactory;
import View.Component.AppFrame;
import View.Component.AppLabelFactory;
import View.Component.AppTheme;
import javax.swing.*;

/**
 *
 * @author Ivaa
 */


public class InputBarang extends AppFrame {

    private final JTextField txtNamaBarang;

    private final JComboBox<String> cbKategori;

    private final JTextArea txtDeskripsi;

    private final JTextField txtLokasi;

    private final JComboBox<String> cbStatus;

    private final JComboBox<String> cbStatusClaim;

    public InputBarang() {
        super("Input Barang", AppTheme.WINDOW_FORM);

        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBackground(AppTheme.BACKGROUND);
        

        JLabel title = AppLabelFactory.sectionTitle("INPUT BARANG");

        title.setBounds(150,20,250,30);

        JLabel lblNama =
                new JLabel("Nama Barang");
        lblNama.setFont(AppTheme.LABEL_FONT);
        lblNama.setForeground(AppTheme.TEXT_PRIMARY);

        lblNama.setBounds(50,80,120,25);

        txtNamaBarang =
                new JTextField();

        txtNamaBarang.setBounds(
                180,
                80,
                220,
                25
        );

        JLabel lblKategori =
                new JLabel("Kategori");
        lblKategori.setFont(AppTheme.LABEL_FONT);
        lblKategori.setForeground(AppTheme.TEXT_PRIMARY);

        lblKategori.setBounds(
                50,
                120,
                120,
                25
        );

        cbKategori =
                new JComboBox<>(
                        new String[]{
                                "Elektronik",
                                "Dokumen",
                                "Aksesoris",
                                "Pakaian",
                                "Kendaraan",
                                "Peralatan Kuliah"
                        }
                );

        cbKategori.setBounds(
                180,
                120,
                220,
                25
        );

        JLabel lblDeskripsi =
                new JLabel("Deskripsi");
        lblDeskripsi.setFont(AppTheme.LABEL_FONT);
        lblDeskripsi.setForeground(AppTheme.TEXT_PRIMARY);

        lblDeskripsi.setBounds(
                50,
                160,
                120,
                25
        );

        txtDeskripsi =
                new JTextArea();
        txtDeskripsi.setFont(AppTheme.BODY_FONT);

        JScrollPane sp =
                new JScrollPane(
                        txtDeskripsi
                );

        sp.setBounds(
                180,
                160,
                220,
                80
        );

        JLabel lblLokasi =
                new JLabel("Lokasi");
        lblLokasi.setFont(AppTheme.LABEL_FONT);
        lblLokasi.setForeground(AppTheme.TEXT_PRIMARY);

        lblLokasi.setBounds(
                50,
                260,
                120,
                25
        );

        txtLokasi =
                new JTextField();

        txtLokasi.setBounds(
                180,
                260,
                220,
                25
        );

        JLabel lblStatus =
                new JLabel("Status");
        lblStatus.setFont(AppTheme.LABEL_FONT);
        lblStatus.setForeground(AppTheme.TEXT_PRIMARY);

        lblStatus.setBounds(
                50,
                300,
                120,
                25
        );

        cbStatus =
                new JComboBox<>(
                        new String[]{
                                "Hilang",
                                "Ditemukan"
                        }
                );

        cbStatus.setBounds(
                180,
                300,
                220,
                25
        );

        JLabel lblClaim =
                new JLabel("Status Claim");
        lblClaim.setFont(AppTheme.LABEL_FONT);
        lblClaim.setForeground(AppTheme.TEXT_PRIMARY);

        lblClaim.setBounds(
                50,
                340,
                120,
                25
        );

        cbStatusClaim =
                new JComboBox<>(
                        new String[]{
                                "Belum Diklaim",
                                "Sudah Diklaim",
                                "Sudah Ditemukan"
                        }
                );

        cbStatusClaim.setBounds(
                180,
                340,
                220,
                25
        );

        JButton btnSimpan = AppButtonFactory.success("SIMPAN");

        btnSimpan.setBounds(
                50,
                430,
                120,
                35
        );
        JButton btnReset = AppButtonFactory.warning("RESET");

        btnReset.setBounds(
                190,
                430,
                120,
                35
        );
        JButton btnBack = AppButtonFactory.danger("BACK");

        btnBack.setBounds(
                330,
                430,
                120,
                35
        );
        txtNamaBarang.setFont(AppTheme.BODY_FONT);
        txtLokasi.setFont(AppTheme.BODY_FONT);
        cbKategori.setFont(AppTheme.BODY_FONT);
        cbStatus.setFont(AppTheme.BODY_FONT);
        cbStatusClaim.setFont(AppTheme.BODY_FONT);

        panel.add(title);

        panel.add(lblNama);
        panel.add(txtNamaBarang);

        panel.add(lblKategori);
        panel.add(cbKategori);

        panel.add(lblDeskripsi);
        panel.add(sp);

        panel.add(lblLokasi);
        panel.add(txtLokasi);

        panel.add(lblStatus);
        panel.add(cbStatus);

        panel.add(lblClaim);
        panel.add(cbStatusClaim);

        panel.add(btnSimpan);
        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        btnSimpan.addActionListener(event -> simpanData());

        btnReset.addActionListener(event -> resetForm());

        btnBack.addActionListener(event -> {
            dispose();
            new DashboardAdmin().setVisible(true);
        });
    }

    private void simpanData() {

        ModelBarang barang =
                new ModelBarang();

        barang.setNamaBarang(
                txtNamaBarang.getText()
        );

        barang.setKategori(
                String.valueOf(cbKategori.getSelectedItem())
        );

        barang.setDeskripsi(
                txtDeskripsi.getText()
        );

        barang.setLokasi(
                txtLokasi.getText()
        );

        barang.setStatus(
                String.valueOf(cbStatus.getSelectedItem())
        );

        barang.setStatusClaim(
                String.valueOf(cbStatusClaim.getSelectedItem())
        );

        int currentUserId = UserSession.getCurrentUserId();
        barang.setUserId(currentUserId == 0 ? 1 : currentUserId);

        ControllerBarang controller =
                new ControllerBarang();

        controller.insert(barang);

        JOptionPane.showMessageDialog(
                this,
                "Data berhasil disimpan"
        );

        resetForm();
    }

    private void resetForm() {

        txtNamaBarang.setText("");

        txtDeskripsi.setText("");

        txtLokasi.setText("");

        cbKategori.setSelectedIndex(0);

        cbStatus.setSelectedIndex(0);

        cbStatusClaim.setSelectedIndex(0);
    }
}

