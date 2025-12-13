package org.example.Model;

public class TransactionItem {
    private int transactionItemId;
    private int transactionId;
    private int materialId;
    private int quantity;
    private double subTotal;
    public TransactionItem(int transactionItemId,
                           int transactionId,
                           int materialId,
                           int quantity,
                           double subTotal) {
        this.transactionItemId = transactionItemId;
        this.transactionId = transactionId;
        this.materialId = materialId;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public TransactionItem(int materialId, int quantity) {
        this(0, 0, materialId, quantity, 0);
    }

    public void calculateSubtotal() {
        // material price needs to be queried
        double materialPrice = 0;
        if (TransactionDAO.getTransaction(this.transactionId).getTransactionType().strip().equalsIgnoreCase("buy")) {
            materialPrice = MaterialDAO.getMaterial(this.materialId).buyPrice();
        } else if (TransactionDAO.getTransaction(this.transactionId).getTransactionType().strip().equalsIgnoreCase("sell")) {
            materialPrice = MaterialDAO.getMaterial(this.materialId).sellPrice();
        } else {
            System.out.println("There was a problem with finding the price!");
        }
        this.subTotal = this.quantity * materialPrice;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getSubTotal() {
        return this.subTotal;
    }

    public int getMaterialId() {
        return this.materialId;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return "TransactionItem[TransactionItemId='" + this.transactionItemId + "', transactionId='" + this.transactionId + "', materialId=" + this.materialId + "', quantity=" + this.quantity + "', subTotal=" + this.subTotal + "]";
    }
}