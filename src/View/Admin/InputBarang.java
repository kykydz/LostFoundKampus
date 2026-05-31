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

import View.Components.Sidebar;
import View.Components.CustomButton;
import Controller.ControllerBarang;
import Model.Barang.ModelBarang;

public class InputBarang extends AppFrame {

    private final JTextField txtNamaBarang;

    private final JComboBox<String> cbKategori;

    private final JTextArea txtDeskripsi;

    private final JTextField txtLokasi;

    private final JComboBox<String> cbStatus;

    private final JComboBox<String> cbStatusClaim;

    public InputBarang() {
        this(null);
    }

    public InputBarang(JFrame parentFrame) {
        super("Input Barang", AppTheme.WINDOW_FORM, parentFrame);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.setLayout(null);
        panel.setBackground(AppTheme.BACKGROUND);
        

        JLabel title = AppLabelFactory.sectionTitle("INPUT BARANG");

        title.setBounds(150,20,250,30);

        JLabel lblNama =
                new JLabel("Nama Barang");
        lblNama.setFont(AppTheme.LABEL_FONT);
        lblNama.setForeground(AppTheme.TEXT_PRIMARY);

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

        btnBack = new JButton("← Kembali");

        txtDeskripsi =
                new JTextArea();
        txtDeskripsi.setFont(AppTheme.BODY_FONT);

            new ViewBarang().setVisible(true);

            dispose();
        });

        JLabel lblLokasi =
                new JLabel("Lokasi");
        lblLokasi.setFont(AppTheme.LABEL_FONT);
        lblLokasi.setForeground(AppTheme.TEXT_PRIMARY);

        mainPanel.add(header, BorderLayout.NORTH);

        // CONTENT
        JPanel content = new JPanel(
                new GridBagLayout()
        );

        content.setBackground(
                new Color(241,245,249)
        );

        JLabel lblStatus =
                new JLabel("Status");
        lblStatus.setFont(AppTheme.LABEL_FONT);
        lblStatus.setForeground(AppTheme.TEXT_PRIMARY);

        formCard.setPreferredSize(
                new Dimension(500,450)
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

        // NAMA
        formCard.add(new JLabel("Nama Barang"));

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

        btnSimpan.addActionListener(event -> simpanData());

        btnReset.addActionListener(event -> resetForm());

        btnBack.addActionListener(event -> {
            if (hasParentFrame()) {
                backToParent();
                return;
            }
            dispose();
            if (UserSession.isAdmin()) {
                new DashboardAdmin().setVisible(true);
                return;
            }
            new View.User.DashboardUser().setVisible(true);
        });
    }

        formCard.add(Box.createVerticalStrut(8));

        tfLokasi = new JTextField();

        tfLokasi.setMaximumSize(
                new Dimension(Integer.MAX_VALUE,40)
        );

        barang.setKategori(
                String.valueOf(cbKategori.getSelectedItem())
        );

        formCard.add(Box.createVerticalStrut(18));

        // STATUS
        formCard.add(new JLabel("Status"));

        barang.setStatus(
                String.valueOf(cbStatus.getSelectedItem())
        );

        barang.setStatusClaim(
                String.valueOf(cbStatusClaim.getSelectedItem())
        );

        int currentUserId = UserSession.getCurrentUserId();
        barang.setUserId(currentUserId == 0 ? 1 : currentUserId);

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