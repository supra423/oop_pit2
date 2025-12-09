package org.example;

import java.util.*;

public class Transaction {
    private int transactionId;
    private List<TransactionItem> transactionItems = new ArrayList<>();
    private String date;
    private double totalAmount;
    private String transactionType;

    public Transaction(int transactionId,
                       List<TransactionItem> transactionItems,
                       String date,
                       double totalAmount,
                       String transactionType) {
        this.transactionId = transactionId;
        this.transactionItems = transactionItems;
        this.date = date;
        this.totalAmount = totalAmount;
        this.transactionType = transactionType;
    }

    public Transaction(List<TransactionItem> transactionItems,
                       String date,
                       double totalAmount,
                       String transactionType) {
        this(0, transactionItems, date, totalAmount, transactionType);
    }

    public Transaction(int transactionId, String date, double totalAmount, String transactionType) {
        this(transactionId, new ArrayList<>(), date, totalAmount, transactionType);
    }

    public void calculateTotal() {
//         iterate over the list
        for (TransactionItem transactionItem : this.transactionItems) {
            this.totalAmount += transactionItem.getSubTotal();
        }
    }

    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public String getDate() {
        return this.date;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    @Override
    public String toString() {
        return "Transaction[TransactionId='" + this.transactionId + "', date(YYYY-MM-DD)='" + this.date + "', totalAmount=" + this.totalAmount + "', transactionType=" + this.transactionType + "]";
    }
}
