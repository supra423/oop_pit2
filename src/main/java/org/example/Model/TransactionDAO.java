package org.example.Model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.*;

// just for clarification, the Transaction_ table is named like that
// because the word Transaction itself is already a SQL keyword,
// so i have to put a "_" to make it a valid identifier

public class TransactionDAO {
    private static final Connection conn = Database.getConn();

    private TransactionDAO() {}

    public static Transaction getTransaction(int transactionId) {
        Transaction transaction = null;
        String sql = "SELECT * FROM Transaction_ WHERE transactionId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, transactionId);
            try (ResultSet rslt1 = stmt1.executeQuery()) {
                if (rslt1.next()) {
                    transaction = new Transaction(
                            rslt1.getInt("transactionId"),
                            rslt1.getString("transactionDate"),
                            rslt1.getDouble("totalAmount"),
                            rslt1.getString("transactionType")
                    );
                } else return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction_";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
             ResultSet rslt1 = stmt1.executeQuery()) {
                if (!rslt1.next()) return null;
                do {
                    transactions.add(new Transaction(
                            rslt1.getInt("transactionId"),
                            rslt1.getString("transactionDate"),
                            rslt1.getDouble("totalAmount"),
                            rslt1.getString("transactionType")
                    ));
                } while (rslt1.next());
                return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TransactionItem getTransactionItem(int transactionItemId) {
        TransactionItem transactionItem = null;
        String sql = "SELECT * FROM TransactionItem WHERE transactionItemId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, transactionItemId);
            try (ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                transactionItem = new TransactionItem(
                        rslt1.getInt("transactionItemId"),
                        rslt1.getInt("transactionId"),
                        rslt1.getInt("materialId"),
                        rslt1.getInt("quantity"),
                        rslt1.getDouble("subTotal")
                    );
                } else return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return transactionItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TransactionItem> getAllTransactionItems() {
        List<TransactionItem> transactionItems = new ArrayList<>();
        String sql = "SELECT * FROM TransactionItem";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
        ResultSet rslt1 = stmt1.executeQuery()) {
            if (!rslt1.next()) return null;
            do {
                transactionItems.add(new TransactionItem(
                        rslt1.getInt("transactionItemId"),
                        rslt1.getInt("transactionId"),
                        rslt1.getInt("materialId"),
                        rslt1.getInt("quantity"),
                        rslt1.getDouble("subTotal")
                ));
            } while (rslt1.next());
            return transactionItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transaction_ (transactionDate, totalAmount, transactionType)" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setString(1, transaction.getDate());
            stmt1.setDouble(2, transaction.getTotalAmount());
            stmt1.setString(3, transaction.getTransactionType());
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getIdOfMostRecentTransaction() {
        String sql = "SELECT transactionId FROM Transaction_ ORDER BY transactionId DESC LIMIT 1";
        int transactionId = 0;
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
             ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                transactionId = rslt1.getInt("transactionId");
            }
            return transactionId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TransactionItem> getTransactionItemsByTransactionId(int transactionId) {
        List<TransactionItem> transactionItems = new ArrayList<>();
        String sql = "SELECT * FROM TransactionItem WHERE transactionId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, transactionId);
            try (ResultSet rslt1 = stmt1.executeQuery()) {
                if (!rslt1.next()) return null;
                do {
                    transactionItems.add(new TransactionItem(
                            rslt1.getInt("transactionItemId"),
                            rslt1.getInt("transactionId"),
                            rslt1.getInt("materialId"),
                            rslt1.getInt("quantity"),
                            rslt1.getDouble("subTotal")
                    ));
                } while (rslt1.next());
            }
            return transactionItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTransactionItem(TransactionItem transactionItem) {
        String sql = "INSERT INTO TransactionItem (transactionId, materialId, quantity, subTotal)" +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, transactionItem.getTransactionId());
            stmt1.setInt(2, transactionItem.getMaterialId());
            stmt1.setInt(3, transactionItem.getQuantity());
            stmt1.setDouble(4, transactionItem.getSubTotal());
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteTransaction(int transactionId) {
        // this bad boy deletes TransactionItems that are referencing a particular
        // Transaction row, then after that it deletes the Transaction row itself
        String sql = "DELETE FROM TransactionItem WHERE transactionId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setInt(1, transactionId);
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql1 = "DELETE FROM Transaction_ WHERE transactionId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
            stmt1.setInt(1, transactionId);
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static double totalMoneyFromBuying() {
        String sql = "SELECT SUM(totalAmount) as total FROM Transaction_ WHERE transactionType = 'buy'";
        double total = 0.0;
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
            ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                total = rslt1.getDouble("total");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static double totalMoneyFromSelling() {
        String sql = "SELECT SUM(totalAmount) as total FROM Transaction_ WHERE transactionType = 'sell'";
        double total = 0.0;
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
             ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                total = rslt1.getDouble("total");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static double averageTotalFromBuying() {
        String sql = "SELECT AVG(totalAmount) as total FROM Transaction_ WHERE transactionType = 'buy'";
        double total = 0.0;
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
             ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                total = rslt1.getDouble("total");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static double averageTotalFromSelling() {
        String sql = "SELECT AVG(totalAmount) as total FROM Transaction_ WHERE transactionType = 'sell'";
        double total = 0.0;
        try (PreparedStatement stmt1 = conn.prepareStatement(sql);
             ResultSet rslt1 = stmt1.executeQuery()) {
            if (rslt1.next()) {
                total = rslt1.getDouble("total");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTotalAmount(double totalAmount, int transactionId) {
        String sql = "UPDATE Transaction_ SET totalAmount = ? WHERE transactionId = ?";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql)) {
            stmt1.setDouble(1, totalAmount);
            stmt1.setInt(2, transactionId);
            stmt1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}