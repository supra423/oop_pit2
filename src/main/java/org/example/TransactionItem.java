package org.example;

public class TransactionItem {
    private int transactionItemId;
    private int transactionId;
    private int materialId;
    private double quantity;
    private double subTotal;
    public TransactionItem(int transactionItemId,
                           int transactionId,
                           int materialId,
                           double quantity,
                           double subTotal) {
        this.transactionItemId = transactionItemId;
        this.transactionId = transactionId;
        this.materialId = materialId;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public TransactionItem(int materialId,
                           double quantity) {
        this(0, 0, materialId, quantity, 0);
    }

    public void calculateSubtotal() {
        // material price needs to be queried
        double materialPrice = MaterialDAO.getMaterial(this.materialId).price();
        this.subTotal = this.quantity * materialPrice;
    }

    public double getSubTotal() {
        return this.subTotal;
    }
}
