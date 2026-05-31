/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;
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

    public LihatBarang(){
        super("Lihat Barang", AppTheme.WINDOW_TABLE);

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
        panel.add(scroll);

        add(panel);

        loadTable();

        btnSearch.addActionListener(e -> searchData());

        btnRefresh.addActionListener(e -> {
            txtSearch.setText("");
            loadTable();
        });

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
}