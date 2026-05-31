/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

import Controller.ControllerBarang;
import Controller.ControllerClaimRequest;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;
import Model.Claim.ModelClaimRequest;
import Model.User.UserSession;
import View.Component.AppButtonFactory;
import View.Component.AppFrame;
import View.Component.AppLabelFactory;
import View.Component.AppTableFactory;
import View.Component.AppTheme;

import javax.swing.*;
import java.util.List;

public class LihatBarang extends AppFrame {

    private final JTable tableBarang;

    private final JTextField txtSearch;

    public LihatBarang() {
        this(null);
    }

    public LihatBarang(JFrame parentFrame){
        super("Lihat Barang", AppTheme.WINDOW_TABLE, parentFrame);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(AppTheme.BACKGROUND);

        JLabel title = AppLabelFactory.sectionTitle("DAFTAR BARANG");

        title.setBounds(280,20,250,30);

        txtSearch = new JTextField();
        AppTableFactory.styleSearchField(txtSearch);

        txtSearch.setBounds(
                50,
                70,
                250,
                30
        );

        JButton btnSearch = AppButtonFactory.primary("SEARCH");

        btnSearch.setBounds(
                320,
                70,
                100,
                30
        );

        JButton btnRefresh = AppButtonFactory.success("REFRESH");

        btnRefresh.setBounds(
                440,
                70,
                100,
                30
        );

        JButton btnClaim = AppButtonFactory.warning("CLAIM");

        btnClaim.setBounds(
                560,
                70,
                100,
                30
        );

        JButton btnBack = hasParentFrame() ? AppButtonFactory.danger("BACK") : null;
        if (btnBack != null) {
            btnBack.setBounds(
                    680,
                    70,
                    100,
                    30
            );
        }

        tableBarang = new JTable();
        AppTableFactory.style(tableBarang);

        JScrollPane scroll =
                new JScrollPane(
                        tableBarang
                );

        scroll.setBounds(
                50,
                130,
                680,
                280
        );

        panel.add(title);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);
        panel.add(btnClaim);
        if (btnBack != null) {
            panel.add(btnBack);
        }
        panel.add(scroll);

        add(panel);

        loadTable();

        btnSearch.addActionListener(e -> searchData());

        btnRefresh.addActionListener(e -> {
            txtSearch.setText("");
            loadTable();
        });

        btnClaim.addActionListener(e -> claimSelectedBarang());
        if (btnBack != null) {
            btnBack.addActionListener(e -> backToParent());
        }

        txtSearch.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    public void keyReleased(
                            java.awt.event.KeyEvent evt
                    ){

                        searchData();
                    }
                }
        );
    }

    private void loadTable(){

        ControllerBarang controller =
                new ControllerBarang();

        List<ModelBarang> list =
                controller.getAll();

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);
    }

    private void searchData(){

        ControllerBarang controller =
                new ControllerBarang();

        List<ModelBarang> list =
                controller.search(
                        txtSearch.getText()
                );

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);
    }

    private void claimSelectedBarang() {
        int selectedRow = tableBarang.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang ingin diklaim terlebih dahulu");
            return;
        }

        int currentUserId = UserSession.getCurrentUserId();
        if (currentUserId == 0) {
            JOptionPane.showMessageDialog(this, "Silakan login kembali untuk mengajukan claim");
            return;
        }

        int barangId = Integer.parseInt(tableBarang.getValueAt(selectedRow, 0).toString());
        ControllerBarang controllerBarang = new ControllerBarang();
        ModelBarang barang = controllerBarang.getById(barangId);

        if (barang == null) {
            JOptionPane.showMessageDialog(this, "Data barang tidak ditemukan");
            return;
        }

        if (barang.getUserId() == currentUserId) {
            JOptionPane.showMessageDialog(this, "Barang milik Anda sendiri tidak perlu diajukan claim");
            return;
        }

        if ("Sudah Diklaim".equalsIgnoreCase(barang.getStatusClaim())) {
            JOptionPane.showMessageDialog(this, "Barang ini sudah diklaim");
            return;
        }

        ControllerClaimRequest controllerClaimRequest = new ControllerClaimRequest();
        if (controllerClaimRequest.existsPendingRequest(barangId, currentUserId)) {
            JOptionPane.showMessageDialog(this, "Anda sudah mengajukan claim untuk barang ini");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Ajukan claim untuk barang: " + barang.getNamaBarang() + "?",
                "Konfirmasi Claim",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ModelClaimRequest request = new ModelClaimRequest();
        request.setBarangId(barangId);
        request.setRequesterUserId(currentUserId);
        request.setStatus("Pending");

        controllerClaimRequest.insert(request);
        JOptionPane.showMessageDialog(this, "Claim berhasil diajukan dan menunggu persetujuan admin");
        loadTable();
    }
}