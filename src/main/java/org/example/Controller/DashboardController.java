package org.example.Controller;

import org.example.Model.Transaction;
import org.example.Model.TransactionItem;
import org.example.View.AdminInterface;
import org.example.View.LandingPage;
import org.example.Model.Material;
import org.example.Model.MaterialDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        Material[] materials = MaterialDAO.getAllMaterials().toArray(new Material[0]);
        String[] columnNames = {"ID", "Material name", "Unit of measure", "Buy price", "Sell price","Stock quantity"};
        Object[][] data = new Object[materials.length][columnNames.length];
        for (int i = 0; i < materials.length; i++) {
            data[i][0] = materials[i].materialId();
            data[i][1] = materials[i].name();
            data[i][2] = materials[i].unitOfMeasure();
            data[i][3] = materials[i].buyPrice();
            data[i][4] = materials[i].sellPrice();
            data[i][5] = materials[i].stockQuantity();
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

                    if ((int) rowData[5] <= 0) {
                        JOptionPane.showMessageDialog(null, "Out of stock!");
                        return;
                    }

                    String sQuantity = JOptionPane.showInputDialog("Input quantity");
                    if (sQuantity == null) {
                        JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                        return;
                    }

                    int quantity = Integer.parseInt(sQuantity);
                    currTransaction.getTransactionItems().add(
                            new TransactionItem((int) rowData[0], quantity) // 0 is materialId
                    );

                    itemsAreaString.append(rowData[1] + " - " + quantity + rowData[2] +
                            "\nBuy subtotal: " + ((double) rowData[3] * quantity) +
                            "\nSell subtotal: " + ((double) rowData[4] * quantity) +
                            "\n\n"
                    );

                    double buyTotal = 0.0;
                    double sellTotal = 0.0;

                    for (TransactionItem transactionItem : currTransaction.getTransactionItems()) {
                        Material material = MaterialDAO.getMaterial(transactionItem.getMaterialId());
                        buyTotal += material.buyPrice() * transactionItem.getQuantity();
                        sellTotal += material.sellPrice() * transactionItem.getQuantity();
                    }

                    itemsArea.setText(itemsAreaString.toString() + " BUY TOTAL: " + buyTotal + " SELL TOTAL: " + sellTotal);

                    materialsTable.setValueAt((int) rowData[5] - quantity, row, 5);
                }
            }
        };
        materialsTable.addMouseListener(mouseAdapter);
    }

    public static void recordABuyButton(Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString) {

    }

    public static void recordASellButton(Transaction currTransaction, JTextArea itemsArea, StringBuilder itemsAreaString) {

    }
}
