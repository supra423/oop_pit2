package org.example;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class MaterialDAO {
    static Connection conn = Database.getConn();

    private MaterialDAO() {}

    public static Material getMaterial(int materialId) {
        Material material = null;
        String sql = "SELECT * FROM Material WHERE materialId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, materialId);
            ResultSet rslt1 = stmt1.executeQuery();
            if (rslt1.next()) {
                material = new Material(
                        rslt1.getInt("materialId"),
                        rslt1.getString("materialName"),
                        rslt1.getString("unitOfMeasure"),
                        rslt1.getDouble("price"),
                        rslt1.getInt("stockQuantity")
                );
            }
            return material;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT * FROM Material";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
        ResultSet rslt1 = stmt1.executeQuery()) {
            while (rslt1.next()) {
                materials.add(new Material(
                        rslt1.getInt("materialId"),
                        rslt1.getString("materialName"),
                        rslt1.getString("unitOfMeasure"),
                        rslt1.getDouble("price"),
                        rslt1.getInt("stockQuantity")
                ));
            }
            return materials;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addMaterial(Material material) {
        String sql = "INSERT INTO Material (materialName, unitOfMeasure, price, stockQuantity)" +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setString(1, material.name());
            stmt1.setString(2, material.unitOfMeasure());
            stmt1.setDouble(3, material.price());
            stmt1.setInt(4, material.stockQuantity());
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMaterial(int materialId) {
        String sql = "DELETE FROM Material WHERE materialId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, materialId);
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMaterialStock(Transaction transaction) {
        if (transaction.getTransactionType().strip().equalsIgnoreCase("sell")) {
            for (TransactionItem transactionItem : transaction.getTransactionItems()) {
                String sql = "UPDATE Material SET stockQuantity = stockQuantity - ? WHERE materialId = ?";
                try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
                    stmt1.setInt(1, transactionItem.getQuantity());
                    stmt1.setInt(2, transactionItem.getMaterialId());
                    stmt1.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (transaction.getTransactionType().strip().equalsIgnoreCase("buy")) {
            for (TransactionItem transactionItem : transaction.getTransactionItems()) {
                String sql = "UPDATE Material SET stockQuantity = stockQuantity + ? WHERE materialId = ?";
                try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
                    stmt1.setInt(1, transactionItem.getQuantity());
                    stmt1.setInt(2, transactionItem.getMaterialId());
                    stmt1.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("Transaction type is not recognized.");
        }
    }
}