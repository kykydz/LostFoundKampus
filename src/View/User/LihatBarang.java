/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Ivaa
 */
import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;
import Model.User.ModelUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LihatBarang extends JFrame {

    JTable tableBarang;

    JTextField txtSearch;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnClaim;

    private ModelUser user;

    public LihatBarang(ModelUser user){

        this.user = user;

        setTitle("Lihat Barang");

        setSize(900,550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        JLabel title =
                new JLabel("DAFTAR BARANG");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        title.setBounds(320,20,300,30);

        txtSearch = new JTextField();

        txtSearch.setBounds(
                50,
                70,
                250,
                35
        );

        btnSearch =
                new JButton("SEARCH");

        btnSearch.setBounds(
                320,
                70,
                100,
                35
        );

        btnRefresh =
                new JButton("REFRESH");

        btnRefresh.setBounds(
                440,
                70,
                100,
                35
        );

        // BUTTON CLAIM
        btnClaim =
                new JButton("Klaim Barang");

        btnClaim.setBounds(
                650,
                70,
                170,
                35
        );

        tableBarang = new JTable();

        JScrollPane scroll =
                new JScrollPane(
                        tableBarang
                );

        scroll.setBounds(
                50,
                130,
                780,
                320
        );

        panel.add(title);

        panel.add(txtSearch);

        panel.add(btnSearch);

        panel.add(btnRefresh);

        panel.add(btnClaim);

        panel.add(scroll);

        add(panel);

        loadTable();

        // SEARCH
        btnSearch.addActionListener(
                e -> searchData()
        );

        // REFRESH
        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText("");

                    loadTable();
                }
        );

        // LIVE SEARCH
        txtSearch.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    public void keyReleased(
                            java.awt.event.KeyEvent evt
                    ){

                        searchData();
                    }
                }
        );

        // CLAIM BUTTON
        btnClaim.addActionListener(e -> claimBarang());
    }

    private void loadTable(){

        ControllerBarang controller =
                new ControllerBarang();

        List<ModelBarang> list =
                controller.getAll();

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);

        tableBarang.setRowHeight(30);

        tableBarang.getTableHeader().setBackground(
                new Color(52,152,219)
        );

        tableBarang.getTableHeader().setForeground(
                Color.WHITE
        );
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

    private void claimBarang(){

        int selectedRow =
                tableBarang.getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih barang terlebih dahulu!"
            );

            return;
        }

        // AMBIL ID BARANG DARI KOLOM 0
        int idBarang =
                Integer.parseInt(
                        tableBarang.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        // OPEN MODAL CLAIM
        new ModalClaimBarang(
                this,
                idBarang,
                user.getId()
        );
    }
}