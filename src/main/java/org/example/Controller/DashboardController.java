package org.example.Controller;

import org.example.Model.*;
import org.example.View.AdminInterface;
import org.example.View.LandingPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DashboardController {
    public static void openAdminInterface(JFrame dashboardFrame, JButton button) {
        button.setEnabled(false);
        dashboardFrame.dispose();
        SwingUtilities.invokeLater(AdminInterface::new);
    }

    public static void openLandingPage(JFrame dashboardFrame, JButton button) {
        button.setEnabled(false);
        dashboardFrame.dispose();
        SwingUtilities.invokeLater(LandingPage::new);
    }

    public static DefaultTableModel getAllMaterials() {
        List<Material> materials = MaterialDAO.getAllMaterials();
        if (materials == null) {
            return new DefaultTableModel();
        }
        String[] columnNames = {"ID", "Material name", "Unit of measure", "Buy price", "Sell price","Stock quantity"};
        Object[][] data = new Object[materials.size()][columnNames.length];
        for (int i = 0; i < materials.size(); i++) {
            // basically the 0 1 2 3 4 5 are the columns
            // 0 = id,
            // 1 = material name,
            // 2 = unit of measure, and so on...
            data[i][0] = materials.get(i).materialId();
            data[i][1] = materials.get(i).name();
            data[i][2] = materials.get(i).unitOfMeasure();
            data[i][3] = materials.get(i).buyPrice();
            data[i][4] = materials.get(i).sellPrice();
            data[i][5] = materials.get(i).stockQuantity();
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void materialsTableRowListener(JTable materialsTable, Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = materialsTable.rowAtPoint(e.getPoint());
                if (row != -1) {
                    int columnCount = materialsTable.getColumnCount();
                    Object[] rowData = new Object[columnCount];
                    for (int col = 0; col < columnCount; col++) {
                        rowData[col] = materialsTable.getValueAt(row, col);
                    }

                    if (!currTransaction.getTransactionItems().isEmpty()) {
                        for (TransactionItem transactionItem : currTransaction.getTransactionItems()) {
                            if (transactionItem.getMaterialId() == (int) rowData[0]) {
                                JOptionPane.showMessageDialog(null, "Product can't be chosen twice!");
                                return;
                            }
                        }
                    }

                    if ((int) rowData[5] <= 0) { // 5 is stock quantity
                        JOptionPane.showMessageDialog(null, "Out of stock!");
                        return;
                    }

                    String sQuantity = JOptionPane.showInputDialog("Input quantity");
                    if (sQuantity == null) {
                        return;
                    }

                    if (sQuantity.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                        return;
                    }

                    int quantity = Integer.parseInt(sQuantity);

                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Please input a quantity more than 0!");
                        return;
                    }

                    currTransaction.getTransactionItems().add(
                            new TransactionItem((int) rowData[0], quantity) // 0 is materialId
                    );

                    itemsAreaString.append(rowData[1] + " - " + quantity + rowData[2] +
                            "\nBuy subtotal: " + ((double) rowData[3] * quantity) + // 3 is buy price
                            "\nSell subtotal: " + ((double) rowData[4] * quantity) + // 4 is sell price
                            "\n\n"
                    );

                    double buyTotal = 0.0;
                    double sellTotal = 0.0;

                    for (TransactionItem transactionItem : currTransaction.getTransactionItems()) {
                        Material material = MaterialDAO.getMaterial(transactionItem.getMaterialId());
                        buyTotal += material.buyPrice() * transactionItem.getQuantity();
                        sellTotal += material.sellPrice() * transactionItem.getQuantity();
                    }

                    itemsArea.setText(itemsAreaString + " BUY TOTAL: " + buyTotal + " SELL TOTAL: " + sellTotal);
                }
            }
        };
        materialsTable.addMouseListener(mouseAdapter);
    }

    public static void clearButton(Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString) {
        int option = JOptionPane.showConfirmDialog(null, "Clear text?");
        if (option == JOptionPane.YES_OPTION) {
            itemsArea.setText("");
            itemsAreaString.setLength(0);
            currTransaction.getTransactionItems().clear();
        }
    }

    public static void recordBuyButton(Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString, JTable materialsTable) {
        if (currTransaction.getTransactionItems().isEmpty()) {
            return;
        }
        currTransaction.setTransactionType("buy");
        MaterialDAO.updateMaterialStock(currTransaction);
        TransactionDAO.addTransaction(currTransaction);
        int transactionId = TransactionDAO.getIdOfMostRecentTransaction();
        if (transactionId != 0) { // if getIdOfMostRecentTransaction() returns 0, then there is no Transaction Row recorded
            for (TransactionItem transactionItem : currTransaction.getTransactionItems()) {
                transactionItem.setTransactionId(transactionId);
                transactionItem.calculateSubtotal();
                TransactionDAO.addTransactionItem(transactionItem);
            }
        } else {
            JOptionPane.showMessageDialog(null, "An unknown error occurred while recording the buy transaction, please try again.");
            return;
        }
        currTransaction.calculateTotal();
        TransactionDAO.updateTotalAmount(currTransaction.getTotalAmount(), transactionId);
        currTransaction.getTransactionItems().clear();
        currTransaction.setTotalAmount(0.0);
        itemsArea.setText("");
        itemsAreaString.setLength(0);
        materialsTable.setModel(getAllMaterials());
    }

    public static void recordSellButton(Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString, JTable materialsTable) {
        if (!ifStockQuantityIsSufficient(currTransaction.getTransactionItems())) {
            JOptionPane.showMessageDialog(null, "Insufficient quantity!");
            return;
        }
        if (currTransaction.getTransactionItems().isEmpty()) return;
        currTransaction.setTransactionType("sell");
        MaterialDAO.updateMaterialStock(currTransaction);
        TransactionDAO.addTransaction(currTransaction);
        int transactionId = TransactionDAO.getIdOfMostRecentTransaction();
        if (transactionId != 0) { // if getIdOfMostRecentTransaction() returns 0, then there is no Transaction Row recorded
            for (TransactionItem transactionItem : currTransaction.getTransactionItems()) {
                transactionItem.setTransactionId(transactionId);
                transactionItem.calculateSubtotal();
                TransactionDAO.addTransactionItem(transactionItem);
            }
        } else {
            JOptionPane.showMessageDialog(null, "An unknown error occurred while recording the sell transaction, please try again.");
            return;
        }
        currTransaction.calculateTotal();
        TransactionDAO.updateTotalAmount(currTransaction.getTotalAmount(), transactionId);
        currTransaction.getTransactionItems().clear();
        currTransaction.setTotalAmount(0.0);
        itemsArea.setText("");
        itemsAreaString.setLength(0);
        materialsTable.setModel(getAllMaterials());
    }

    public static boolean ifStockQuantityIsSufficient(List<TransactionItem> transactionItems) {
        // this method is only for the sell transaction,
        // this makes sure that the transaction doesn't
        // continue when one of the transaction item's quantity
        // is greater than the stock quantity
        for (TransactionItem transactionItem : transactionItems) {
            if (transactionItem.getQuantity() > MaterialDAO.getMaterial(transactionItem.getMaterialId()).stockQuantity()) {
                return false;
            }
        }
        return true;
    }
}