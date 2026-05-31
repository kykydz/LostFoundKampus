package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseSchemaManager {

    private DatabaseSchemaManager() {
    }

    public static void ensureClaimWorkflowSchema(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            ensureClaimedByColumn(connection);
            ensureClaimRequestsTable(connection);
        } catch (SQLException e) {
            System.out.println("Gagal menyiapkan skema claim: " + e.getMessage());
        }
    }

    private static void ensureClaimedByColumn(Connection connection) throws SQLException {
        if (hasColumn(connection, "barang", "claimed_by_user_id")) {
            return;
        }

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("ALTER TABLE barang ADD COLUMN claimed_by_user_id INT NULL AFTER user_id");
        }
    }

    private static void ensureClaimRequestsTable(Connection connection) throws SQLException {
        if (hasTable(connection, "claim_requests")) {
            return;
        }

        String query = "CREATE TABLE claim_requests ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "barang_id INT NOT NULL,"
                + "requester_user_id INT NOT NULL,"
                + "status ENUM('Pending', 'Approved', 'Rejected') NOT NULL DEFAULT 'Pending',"
                + "reviewed_by_user_id INT NULL,"
                + "requested_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "reviewed_at TIMESTAMP NULL DEFAULT NULL,"
                + "FOREIGN KEY (barang_id) REFERENCES barang(id) ON UPDATE CASCADE ON DELETE CASCADE,"
                + "FOREIGN KEY (requester_user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,"
                + "FOREIGN KEY (reviewed_by_user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE SET NULL"
                + ")";

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
        }
    }

    private static boolean hasTable(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(connection.getCatalog(), null, tableName, null)) {
            return rs.next();
        }
    }

    private static boolean hasColumn(Connection connection, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getColumns(connection.getCatalog(), null, tableName, columnName)) {
            return rs.next();
        }
    }
}

