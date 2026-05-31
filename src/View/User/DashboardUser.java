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
import Model.User.ModelUser;
import View.Components.DashboardCard;
import View.Components.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardUser extends JFrame {

    private ModelUser user;

    private JPanel cards;

    private JTable tableBarang;

    private ControllerBarang controller;

    public DashboardUser(ModelUser user) {

        this.user = user;

        controller = new ControllerBarang();

        initComponents();
    }

    private void initComponents() {

        setTitle("Dashboard User");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // SIDEBAR
        Sidebar sidebar = new Sidebar(this);

        add(sidebar, BorderLayout.WEST);

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
                "Dashboard"
        );

        lblTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        header.add(lblTitle, BorderLayout.WEST);

        JLabel lblUser = new JLabel(
                "Halo, " + user.getNama()
        );

        lblUser.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        header.add(lblUser, BorderLayout.EAST);

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

        content.setLayout(new BoxLayout(
                content,
                BoxLayout.Y_AXIS
        ));

        // =========================
        // DASHBOARD CARDS
        // =========================

        cards = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        20,
                        0
                )
        );

        cards.setOpaque(false);

        loadCards();

        content.add(cards);

        content.add(Box.createVerticalStrut(30));

        // =========================
        // TABLE PANEL
        // =========================

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

        JLabel lblTable = new JLabel(
                "Barang Terbaru"
        );

        lblTable.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        tablePanel.add(lblTable, BorderLayout.NORTH);

        String[] columns = {
                "ID",
                "Nama Barang",
                "Kategori",
                "Lokasi",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        tableBarang = new JTable(model);

        tableBarang.setRowHeight(30);

        tableBarang.getTableHeader().setBackground(
                new Color(37,99,235)
        );

        tableBarang.getTableHeader().setForeground(
                Color.WHITE
        );

        loadTable(model);

        JScrollPane scrollPane =
                new JScrollPane(tableBarang);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        content.add(tablePanel);

        content.add(Box.createVerticalStrut(25));

        // =========================
        // HISTORY PANEL
        // =========================

        JPanel historyPanel = new JPanel(
                new BorderLayout()
        );

        historyPanel.setBackground(Color.WHITE);

        historyPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220,220,220)
                        ),
                        BorderFactory.createEmptyBorder(
                                20,20,20,20
                        )
                )
        );

        JLabel lblHistory =
                new JLabel(
                        "History Pengembalian"
                );

        lblHistory.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        historyPanel.add(
                lblHistory,
                BorderLayout.NORTH
        );

        DefaultListModel<String> historyModel =
                new DefaultListModel<>();

        List<ModelBarang> returnedList =
                controller.getReturnedBarang();

        for(ModelBarang barang : returnedList){

            historyModel.addElement(
                    barang.getNamaBarang()
                    + " - "
                    + barang.getStatus()
            );
        }

        JList<String> historyList =
                new JList<>(historyModel);

        historyPanel.add(
                new JScrollPane(historyList),
                BorderLayout.CENTER
        );

        content.add(historyPanel);

        mainPanel.add(content, BorderLayout.CENTER);


        setVisible(true);
    }

    // =========================
    // LOAD DASHBOARD CARDS
    // =========================

    private void loadCards(){

        cards.removeAll();

        cards.add(
                new DashboardCard(
                        String.valueOf(
                                controller.getTotalBarang()
                        ),
                        "Total Barang",
                        new Color(37,99,235)
                )
        );

        cards.add(
                new DashboardCard(
                        String.valueOf(
                                controller.getTotalByStatus(
                                        "Hilang"
                                )
                        ),
                        "Barang Hilang",
                        new Color(239,68,68)
                )
        );

        cards.add(
                new DashboardCard(
                        String.valueOf(
                                controller.getTotalByStatus(
                                        "Ditemukan"
                                )
                        ),
                        "Barang Ditemukan",
                        new Color(16,185,129)
                )
        );

        cards.add(
                new DashboardCard(
                        String.valueOf(
                                controller.getTotalByStatus(
                                        "Returned"
                                )
                        ),
                        "Returned",
                        new Color(234,179,8)
                )
        );

        cards.revalidate();

        cards.repaint();
    }

    // =========================
    // LOAD TABLE
    // =========================

    private void loadTable(DefaultTableModel model){

        model.setRowCount(0);

        List<ModelBarang> list =
                controller.getAll();

        for(ModelBarang barang : list){

            Object[] row = {

                    barang.getId(),

                    barang.getNamaBarang(),

                    barang.getKategori(),

                    barang.getLokasi(),

                    barang.getStatus()
            };

            model.addRow(row);
        }
    }

    
}
