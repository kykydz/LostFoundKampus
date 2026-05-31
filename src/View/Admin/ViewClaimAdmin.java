/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

/**
 *
 * @author karina
 */

import Controller.ControllerClaim;
import Model.Claim.ModelClaim;
import View.Components.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewClaimAdmin extends JFrame {

    private JTable tableClaim;

    private JButton btnApprove;

    private JButton btnReject;

    private ControllerClaim controller;

    public ViewClaimAdmin(){

        controller = new ControllerClaim();

        initComponents();
    }

    private void initComponents(){

        setTitle("Manajemen Claim");

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
                "Manajemen Claim"
        );

        lblTitle.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        header.add(lblTitle, BorderLayout.WEST);

        mainPanel.add(header, BorderLayout.NORTH);

        // CONTENT
        JPanel content = new JPanel(
                new BorderLayout()
        );

        content.setBackground(
                new Color(241,245,249)
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        25,25,25,25
                )
        );

        String[] columns = {
                "ID Claim",
                "ID Barang",
                "ID User",
                "Alasan",
                "Status",
                "Tanggal"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        tableClaim = new JTable(model);

        tableClaim.setRowHeight(30);

        loadData(model);

        JScrollPane scrollPane =
                new JScrollPane(tableClaim);

        content.add(scrollPane, BorderLayout.CENTER);

        // ACTION PANEL
        JPanel actionPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT
                )
        );

        btnApprove =
                new JButton("Approve");

        btnReject =
                new JButton("Reject");

        actionPanel.add(btnApprove);

        actionPanel.add(btnReject);

        content.add(actionPanel, BorderLayout.SOUTH);

        mainPanel.add(content, BorderLayout.CENTER);

        // APPROVE
        btnApprove.addActionListener(
                e -> approveClaim()
        );

        // REJECT
        btnReject.addActionListener(
                e -> rejectClaim()
        );

        setVisible(true);
    }

    private void loadData(DefaultTableModel model){

        model.setRowCount(0);

        List<ModelClaim> list =
                controller.getAllClaim();

        for(ModelClaim claim : list){

            Object[] row = {

                    claim.getIdClaim(),

                    claim.getIdBarang(),

                    claim.getIdUser(),

                    claim.getAlasanClaim(),

                    claim.getStatusClaim(),

                    claim.getTanggalClaim()
            };

            model.addRow(row);
        }
    }

    private void approveClaim(){

        int selectedRow =
                tableClaim.getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih claim terlebih dahulu!"
            );

            return;
        }

        int idClaim =
                Integer.parseInt(
                        tableClaim.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        controller.approveClaim(idClaim);

        JOptionPane.showMessageDialog(
                this,
                "Claim berhasil diapprove!"
        );

        refreshTable();
    }

    private void rejectClaim(){

        int selectedRow =
                tableClaim.getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih claim terlebih dahulu!"
            );

            return;
        }

        int idClaim =
                Integer.parseInt(
                        tableClaim.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );

        controller.rejectClaim(idClaim);

        JOptionPane.showMessageDialog(
                this,
                "Claim berhasil direject!"
        );

        refreshTable();
    }

    private void refreshTable(){

        DefaultTableModel model =
                (DefaultTableModel)
                        tableClaim.getModel();

        loadData(model);
    }
}