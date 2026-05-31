/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

/**
 *
 * @author Ivaa
 */
import Model.Barang.ModelTableBarang;
import View.Components.Sidebar;
import View.Components.CustomButton;

import javax.swing.*;
import java.awt.*;
import Controller.ControllerBarang;
import Model.Barang.ModelBarang;
import java.util.List;

public class ViewBarang extends JFrame {

    private JTable tableBarang;

    private JTextField tfSearch;

    private CustomButton btnTambah;

    private JButton btnEdit;

    private JButton btnHapus;

    private JButton btnRefresh;

    private JButton btnBack;
    
    private ControllerBarang controller;

    public ViewBarang() {

        controller = new ControllerBarang();

        initComponents();

        loadTable();
    }

    private void initComponents() {

        setTitle("Data Barang");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // SIDEBAR
        add(new Sidebar(this), BorderLayout.WEST);

        // MAIN PANEL
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
                "Data Barang"
        );

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        header.add(lblTitle, BorderLayout.WEST);

        btnBack = new JButton("← Dashboard");

        btnBack.addActionListener(e -> {

            new DashboardAdmin().setVisible(true);

            dispose();
        });

        header.add(btnBack, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // CONTENT
        JPanel content = new JPanel();

        content.setBackground(
                new Color(241,245,249)
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        25,25,25,25
                )
        );

        content.setLayout(new BorderLayout());

        // TOP PANEL
        JPanel topPanel = new JPanel(
                new BorderLayout(15,0)
        );

        topPanel.setOpaque(false);

        tfSearch = new JTextField();

        tfSearch.setPreferredSize(
                new Dimension(300,40)
        );

        topPanel.add(tfSearch, BorderLayout.WEST);
        tfSearch.addKeyListener(
        new java.awt.event.KeyAdapter() {

            public void keyReleased(
                    java.awt.event.KeyEvent evt
            ){

                searchBarang();
            }
        }
);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT
                )
        );

        buttonPanel.setOpaque(false);

        btnTambah = new CustomButton(
                "Tambah Barang"
        );

        btnTambah.addActionListener(e -> {

            new InputBarang().setVisible(true);

            dispose();
        });

        buttonPanel.add(btnTambah);

        btnRefresh = new JButton("Refresh");

        buttonPanel.add(btnRefresh);
        
        btnRefresh.addActionListener(e -> {

            loadTable();
        });

        topPanel.add(buttonPanel, BorderLayout.EAST);

        content.add(topPanel, BorderLayout.NORTH);

        // TABLE PANEL
        JPanel tablePanel = new JPanel(
                new BorderLayout()
        );

        tablePanel.setBackground(Color.WHITE);

        tablePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220,220,220)
                        ),
                        BorderFactory.createEmptyBorder(
                                20,20,20,20
                        )
                )
        );

        tableBarang = new JTable();

        String[][] data = {};

        tableBarang = new JTable(data, columns);

        tableBarang.setRowHeight(32);

        JScrollPane scrollPane =
                new JScrollPane(tableBarang);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ACTION PANEL
        JPanel actionPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT
                )
        );

        actionPanel.setBackground(Color.WHITE);

        btnEdit = new JButton("Edit");

        btnHapus = new JButton("Hapus");

        actionPanel.add(btnEdit);

        actionPanel.add(btnHapus);

        tablePanel.add(actionPanel, BorderLayout.SOUTH);

        content.add(tablePanel, BorderLayout.CENTER);

        mainPanel.add(content, BorderLayout.CENTER);

        setVisible(true);
    }
    private void loadTable(){

        List<ModelBarang> list =
                controller.getAll();

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);

        tableBarang.setRowHeight(32);

        tableBarang.getTableHeader().setBackground(
                new Color(37,99,235)
        );

        tableBarang.getTableHeader().setForeground(
                Color.WHITE
        );
    }
    
    private void searchBarang(){

        List<ModelBarang> list =
                controller.search(
                        tfSearch.getText()
                );

        ModelTableBarang model =
                new ModelTableBarang(list);

        tableBarang.setModel(model);
    }
}