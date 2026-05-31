/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerBarang;
import Controller.ControllerClaimRequest;
import Model.Claim.ModelClaimRequest;
import Model.Barang.ModelBarang;
import Model.Barang.ModelTableBarang;
import Model.User.DAOUser;
import Model.User.ModelUser;
import Model.User.UserSession;
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
        this(null);
    }

    public ViewBarang(JFrame parentFrame) {
        super("View Barang", AppTheme.WINDOW_TABLE, parentFrame);

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

        JButton btnReviewClaim = AppButtonFactory.warning("REVIEW CLAIM");
        btnReviewClaim.setBounds(680,70,130,30);

        JButton btnBack = hasParentFrame() ? AppButtonFactory.danger("BACK") : null;
        if (btnBack != null) {
            btnBack.setBounds(50, 420, 100, 30);
        }

        tableBarang = new JTable();
        AppTableFactory.style(tableBarang);

        JScrollPane scroll = new JScrollPane(tableBarang);

        scroll.setBounds(50,120,680,280);

        panel.add(title);
        panel.add(txtSearch);
        panel.add(btnSearch);
        panel.add(btnRefresh);
        panel.add(btnDelete);
        panel.add(btnReviewClaim);
        if (btnBack != null) {
            panel.add(btnBack);
        }
        panel.add(scroll);
        
        add(panel);

        loadTable();

        btnSearch.addActionListener(event -> searchData());

        btnRefresh.addActionListener(event -> {
            txtSearch.setText("");
            loadTable();
        });
        
        btnDelete.addActionListener(event -> deleteData());
        btnReviewClaim.addActionListener(event -> reviewClaim());
        if (btnBack != null) {
            btnBack.addActionListener(event -> backToParent());
        }

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

    private void reviewClaim() {
        int row = tableBarang.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data barang terlebih dahulu");
            return;
        }

        int barangId = Integer.parseInt(tableBarang.getValueAt(row, 0).toString());
        ControllerBarang controllerBarang = new ControllerBarang();
        ModelBarang barang = controllerBarang.getById(barangId);

        if (barang == null) {
            JOptionPane.showMessageDialog(this, "Data barang tidak ditemukan");
            return;
        }

        ControllerClaimRequest controllerClaimRequest = new ControllerClaimRequest();
        java.util.List<ModelClaimRequest> pendingRequests = controllerClaimRequest.getPendingRequestsByBarang(barangId);

        if (!pendingRequests.isEmpty()) {
            ModelClaimRequest request = choosePendingRequest(pendingRequests);
            if (request == null) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Setujui claim dari " + request.getRequesterName() + " untuk barang " + request.getBarangName() + "?",
                    "Persetujuan Claim",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            int adminUserId = UserSession.getCurrentUserId();
            controllerClaimRequest.approveRequest(request.getId(), adminUserId == 0 ? 1 : adminUserId);
            JOptionPane.showMessageDialog(this, "Claim berhasil disetujui");
            loadTable();
            return;
        }

        ModelUser selectedUser = chooseUserForManualClaim(barang);
        if (selectedUser == null) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Admin akan langsung menandai barang ini diklaim oleh " + selectedUser.getNama() + ". Lanjutkan?",
                "Manual Claim",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int adminUserId = UserSession.getCurrentUserId();
        controllerClaimRequest.manualClaim(barangId, selectedUser.getId(), adminUserId == 0 ? 1 : adminUserId);
        JOptionPane.showMessageDialog(this, "Barang berhasil diklaim secara manual");
        loadTable();
    }

    private ModelClaimRequest choosePendingRequest(List<ModelClaimRequest> pendingRequests) {
        String[] options = pendingRequests.stream()
                .map(request -> request.getRequesterName() + " (@" + request.getRequesterUsername() + ") - " + request.getRequestedAt())
                .toArray(String[]::new);

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Pilih request claim yang akan ditinjau",
                "Daftar Claim Pending",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected == null) {
            return null;
        }

        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selected)) {
                return pendingRequests.get(i);
            }
        }

        return null;
    }

    private ModelUser chooseUserForManualClaim(ModelBarang barang) {
        java.util.List<ModelUser> users = new DAOUser().getAll().stream()
                .filter(user -> "user".equalsIgnoreCase(user.getRole()))
                .filter(user -> user.getId() != barang.getUserId())
                .toList();

        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada user yang dapat dipilih untuk claim manual");
            return null;
        }

        String[] options = users.stream()
                .map(user -> user.getNama() + " (@" + user.getUsername() + ")")
                .toArray(String[]::new);

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Tidak ada request pending. Pilih user untuk claim manual",
                "Manual Claim",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected == null) {
            return null;
        }

        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(selected)) {
                return users.get(i);
            }
        }

        return null;
    }
}
