/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

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
/**
 *
 * @author Ivaa
 */
public class ViewBarang extends AppFrame {
    private final JTable tableBarang;

    private final JTextField txtSearch;
    
    public ViewBarang() {
        super("View Barang", AppTheme.WINDOW_TABLE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(AppTheme.BACKGROUND);

        JLabel title = AppLabelFactory.sectionTitle("DATA BARANG");
        title.setBounds(300,20,250,30);

        txtSearch = new JTextField();
        txtSearch.setBounds(50,70,250,30);
        AppTableFactory.styleSearchField(txtSearch);

        JButton btnSearch = AppButtonFactory.primary("SEARCH");
        btnSearch.setBounds(320,70,100,30);

        JButton btnRefresh = AppButtonFactory.success("REFRESH");
        btnRefresh.setBounds(440,70,100,30);

        JButton btnDelete = AppButtonFactory.danger("DELETE");
        btnDelete.setBounds(560,70,100,30);

        tableBarang = new JTable();
        AppTableFactory.style(tableBarang);

        JScrollPane scroll = new JScrollPane(tableBarang);

        scroll.setBounds(50,120,680,280);

        panel.add(title);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);
        panel.add(btnDelete);
        panel.add(scroll);
        
        add(panel);

        loadTable();

        btnSearch.addActionListener(_ -> searchData());

        btnRefresh.addActionListener(_ -> {
            txtSearch.setText("");
            loadTable();
        });
        
        btnDelete.addActionListener(_ -> deleteData());

        txtSearch.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent ignored){
                        searchData();
                    }
                }
        );
    }
    
    private void loadTable(){
        ControllerBarang controller = new ControllerBarang();
        List<ModelBarang> list = controller.getAll();
        ModelTableBarang model = new ModelTableBarang(list);
        tableBarang.setModel(model);
    }
    
    private void searchData() {
        ControllerBarang controller = new ControllerBarang();
        List<ModelBarang> list = controller.search(txtSearch.getText());
        ModelTableBarang model = new ModelTableBarang(list);
        tableBarang.setModel(model);
    }
    
    private void deleteData(){
        int row = tableBarang.getSelectedRow();
        
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
            return;
        }
        
        int id = Integer.parseInt(tableBarang.getValueAt(row, 0).toString());
        
        ControllerBarang controller = new ControllerBarang();
        controller.delete(id);
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        
        loadTable();
    }
}
