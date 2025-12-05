package org.example;

import java.sql.Connection;
import java.util.*;

// abstract for now
public abstract class TransactionDAO {
    Connection conn = Database.getConn();
    public abstract Transaction getTransaction(int transactionId);
    public abstract List<Transaction> getAllTransactions();
    public abstract TransactionItem getTransactionItem(int transactionItemId);
    public abstract List<TransactionItem> getAllTransactionItems();
    public abstract void addTransaction(Transaction transaction);
    public abstract List<TransactionItem> getTransactionItemsByTransactionId(int transactionId);
    public abstract void addTransactionItem(TransactionItem transactionItem);
    public abstract void deleteTransaction(int transactionId);
    public abstract void updateMaterialStock(Transaction transaction);
    public abstract double totalMoneyFromBuying();
    public abstract double totalMoneyFromSelling();
    public abstract double averageTotalFromBuying();
    public abstract double averageTotalFromSelling();
}
