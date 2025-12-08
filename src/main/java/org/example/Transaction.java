package org.example;

import java.util.*;

public class Transaction {
    int transactionId;
    List<TransactionItem> transactionItems = new ArrayList<>();
    String date;
    double totalAmount;
    String transactionType;

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

    public void calculateTotal() {
//         iterate over the list
        for (TransactionItem transactionItem : this.transactionItems) {
            this.totalAmount += transactionItem.getSubTotal();
        }
    }
}
