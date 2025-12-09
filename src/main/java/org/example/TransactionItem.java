package org.example;

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

    public void calculateSubtotal() {
        // material price needs to be queried
        double materialPrice = MaterialDAO.getMaterial(this.materialId).price();
        this.subTotal = this.quantity * materialPrice;
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
