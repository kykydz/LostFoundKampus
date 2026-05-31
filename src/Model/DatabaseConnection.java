/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Ivaa
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    public DatabaseConnection() {
        if (connection == null) {
            connection = getConnection();
        }
    }

    public static Connection getConnection() {

        try {
            String dbHost = System.getenv().getOrDefault("DB_HOST", "localhost");
            String dbPort = System.getenv().getOrDefault("DB_PORT", "3306");
            String dbName = System.getenv().getOrDefault("DB_NAME", "lostfoundkampus");
            String user = System.getenv().getOrDefault("DB_USER", "lfk_app");
            String pass = System.getenv().getOrDefault("DB_PASS", "lfk_app_123");
            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            Connection activeConnection = DriverManager.getConnection(url, user, pass);
            DatabaseSchemaManager.ensureClaimWorkflowSchema(activeConnection);
            return activeConnection;

        } catch (Exception e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
        }
        return null;
    }
}