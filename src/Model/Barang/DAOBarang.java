/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Barang;

import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Ivaa
 */
public class DAOBarang implements InterfaceDAOBarang {
    Connection connection;

    public DAOBarang(){
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public void insert(ModelBarang barang) {
        if(
            barang.getNamaBarang() == null ||
            barang.getNamaBarang().isEmpty()
            ){
            throw new IllegalArgumentException(
                        "Nama barang wajib diisi!"
            );
            }

        try {
            String query = "INSERT INTO barang (nama_barang, kategori, "
                    + "deskripsi, lokasi, status, status_claim, user_id) "
                    + "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getKategori());
            ps.setString(3, barang.getDeskripsi());
            ps.setString(4, barang.getLokasi());
            ps.setString(5, barang.getStatus());
            ps.setString(6, barang.getStatusClaim());
            ps.setInt(7, barang.getUserId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(ModelBarang barang) {
        try {
            String query = "UPDATE barang SET nama_barang=?, kategori=?,"
                    + "deskripsi=?, lokasi=?, status=?, status_claim=? "
                    + "WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, barang.getNamaBarang());
            ps.setString(2, barang.getKategori());
            ps.setString(3, barang.getDeskripsi());
            ps.setString(4, barang.getLokasi());
            ps.setString(5, barang.getStatus());
            ps.setString(6, barang.getStatusClaim());
            ps.setInt(7, barang.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM barang WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ModelBarang> getAll() {
        List<ModelBarang> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String query = "SELECT * FROM barang ORDER BY id DESC";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ModelBarang barang = new ModelBarang();
                barang.setId(
                        rs.getInt("id")
                );

                barang.setNamaBarang(
                        rs.getString("nama_barang")
                );

                barang.setKategori(
                        rs.getString("kategori")
                );

                barang.setDeskripsi(
                        rs.getString("deskripsi")
                );

                barang.setLokasi(
                        rs.getString("lokasi")
                );

                barang.setStatus(
                        rs.getString("status")
                );
                
                barang.setStatusClaim(
                        rs.getString("status_claim")
                );

                barang.setCreatedAt(
                    rs.getString("created_at")
                );

                list.add(barang);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<ModelBarang> search(String keyword) {
        List<ModelBarang> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM barang WHERE nama_barang LIKE ? "
                    + "OR kategori LIKE ? OR lokasi LIKE ?";

            PreparedStatement ps = connection.prepareStatement(query);

            String cari = "%" + keyword + "%";
            ps.setString(1,cari);
            ps.setString(2,cari);
            ps.setString(3,cari);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ModelBarang barang = new ModelBarang();
                barang.setId(
                        rs.getInt("id")
                );

                barang.setNamaBarang(
                        rs.getString("nama_barang")
                );

                barang.setKategori(
                        rs.getString("kategori")
                );

                barang.setDeskripsi(
                        rs.getString("deskripsi")
                );

                barang.setLokasi(
                        rs.getString("lokasi")
                );

                barang.setStatus(
                        rs.getString("status")
                );

                list.add(barang);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    @Override
    public ModelBarang getById(int id) {

        ModelBarang barang = null;

        try {

            String query =
                    "SELECT * FROM barang WHERE id=?";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                barang = new ModelBarang();
                
                barang.setId(rs.getInt("id"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setKategori(rs.getString("kategori"));
                barang.setDeskripsi(rs.getString("deskripsi"));
                barang.setLokasi(rs.getString("lokasi"));
                barang.setStatus(rs.getString("status"));
                barang.setStatusClaim(rs.getString("status_claim"));
                barang.setUserId(rs.getInt("user_id"));
                barang.setCreatedAt(rs.getString("created_at"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return barang;
    }
    public int getTotalBarang(){

        try {

            String query =
                    "SELECT COUNT(*) as total "
                  + "FROM barang";

            Statement stmt =
                    connection.createStatement();

            ResultSet rs =
                    stmt.executeQuery(query);

            if(rs.next()){

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    public int getTotalByStatus(String status){

        try {

            String query =
                    "SELECT COUNT(*) as total "
                  + "FROM barang "
                  + "WHERE status=?";

            PreparedStatement stmt =
                    connection.prepareStatement(query);

            stmt.setString(1,status);

            ResultSet rs =
                    stmt.executeQuery();

            if(rs.next()){

                return rs.getInt("total");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
    public List<ModelBarang> getReturnedBarang(){

        List<ModelBarang> list =
                new ArrayList<>();

        try {

            String query =
                    "SELECT * FROM barang "
                  + "WHERE status='Returned'";

            Statement stmt =
                    connection.createStatement();

            ResultSet rs =
                    stmt.executeQuery(query);

            while(rs.next()){

                ModelBarang barang =
                        new ModelBarang();

                barang.setId(
                        rs.getInt("id")
                );

                barang.setNamaBarang(
                        rs.getString("nama_barang")
                );

                barang.setKategori(
                        rs.getString("kategori")
                );

                barang.setLokasi(
                        rs.getString("lokasi")
                );

                barang.setStatus(
                        rs.getString("status")
                );
                barang.setStatusClaim(
                rs.getString("status_claim")
                );

                barang.setCreatedAt(
                        rs.getString("created_at")
                );

                list.add(barang);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    
}
