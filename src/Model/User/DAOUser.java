/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author Ivaa
 */

import Model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser
implements InterfaceDAOUser{

    Connection connection;

    public DAOUser(){

        connection =
                DatabaseConnection
                        .getConnection();
    }

    @Override
    public void insert(ModelUser user) {

        try {

            String query =
                    "INSERT INTO users "
                    + "(username,password,"
                    + "nama,role)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement ps =
                    connection.prepareStatement(query);

            ps.setString(1,
                    user.getUsername());

            ps.setString(2,
                    user.getPassword());

            ps.setString(3,
                    user.getNama());

            ps.setString(4,
                    "user");

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public ModelUser login(
        String username,
        String password
    ) {

        try {

            String query =
                    "SELECT * FROM users "
                  + "WHERE username=? AND password=?";

            PreparedStatement stmt =
                    connection.prepareStatement(query);

            stmt.setString(1, username);

            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                ModelUser user = new ModelUser();

                user.setId(rs.getInt("id"));

                user.setUsername(
                        rs.getString("username")
                );

                user.setPassword(
                        rs.getString("password")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ModelUser> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}