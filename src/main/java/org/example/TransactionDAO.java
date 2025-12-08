package org.example;

import java.sql.Connection;
import java.util.*;

// abstract for now
public class TransactionDAO {
    Connection conn = Database.getConn();

    public Transaction getTransaction(int transactionId) {
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return null;
    }

    public TransactionItem getTransactionItem(int transactionItemId) {
        return null;
    }

    public List<TransactionItem> getAllTransactionItems() {
        return null;
    }

    public void addTransaction(Transaction transaction) {

    }

    public List<TransactionItem> getTransactionItemsByTransactionId(int transactionId) {
        return null;
    }

    public void addTransactionItem(TransactionItem transactionItem) {}
    public void deleteTransaction(int transactionId) {}
    public void updateMaterialStock(Transaction transaction) {}
    public double totalMoneyFromBuying() {
        return 0.0;
    }
    public double totalMoneyFromSelling() {

        return 0.0;
    }
    public double averageTotalFromBuying() {

        return 0.0;
    }
    public double averageTotalFromSelling() {

        return 0.0;
    }
}
