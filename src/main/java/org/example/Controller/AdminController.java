package org.example.Controller;

import org.example.*;

import javax.swing.*;
import java.util.List;

public class AdminController {
    private AdminController() {}
    public static void addMaterial(JTextArea outputArea) {
        String materialName;
        String unitOfMeasure;
        Double materialPrice;
        Integer stockQuantity;
        while (true) {

            materialName = JOptionPane.showInputDialog("Input material name");
            if (materialName == null) {
                return;
            } else if (materialName.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                continue;
            }
            unitOfMeasure = JOptionPane.showInputDialog("Input unit of measure");
            if (unitOfMeasure == null) {
                return;
            }
            else if (unitOfMeasure.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                continue;
            }
            if (unitOfMeasure.length() > 5) {
                JOptionPane.showMessageDialog(null, "Max 5 characters!");
                continue;
            }
            try {
                String stringMaterialPrice = JOptionPane.showInputDialog("Input price (per " + unitOfMeasure + ")");
                if (stringMaterialPrice == null) {
                    return;
                } else if (stringMaterialPrice.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                String stringStockQuantity = JOptionPane.showInputDialog("Input initial stock quantity (per " + unitOfMeasure + ")");
                if (stringStockQuantity == null) {
                    return;
                } else if (stringStockQuantity.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                materialPrice = Double.parseDouble(stringMaterialPrice);
                stockQuantity = Integer.parseInt(stringStockQuantity);
                break;
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
        }
        Material newMaterial = new Material(materialName, unitOfMeasure, materialPrice, stockQuantity);
        outputArea.setText("Addition successful!");
        MaterialDAO.addMaterial(newMaterial);
    }

    public static void deleteMaterial(JTextArea outputArea) {
        // 0 so that if ever an unhandled error occurs, nothing gets deleted
        // because IDs start at 1
        int materialId = 0;

        while (true) {
            try {
                String stringMaterialId = JOptionPane.showInputDialog("Enter material ID to delete");
                if (stringMaterialId == null) {
                    return;
                } else if (stringMaterialId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                materialId = Integer.parseInt(stringMaterialId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            if (MaterialDAO.getMaterial(materialId) == null) {
                outputArea.setText("Material not found");
                break;
            }
            MaterialDAO.removeMaterial(materialId);
            outputArea.setText("Deletion successful!");
            break;
        }
    }

    public static void getAllMaterials(JTextArea outputArea) {
        outputArea.setText("");
        for (Material material : MaterialDAO.getAllMaterials()) {
            outputArea.append(material.toString() + "\n\n");
        }
    }

    public static void getMaterialById(JTextArea outputArea) {
        int materialId = 0;

        while (true) {
            try {
                String stringMaterialId = JOptionPane.showInputDialog("Enter material ID to search");
                if (stringMaterialId == null) {
                    return;
                } else if (stringMaterialId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                materialId = Integer.parseInt(stringMaterialId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            Material fetchedMaterial = MaterialDAO.getMaterial(materialId);
            if (fetchedMaterial == null) {
                outputArea.setText("Material not found");
                break;
            }
            outputArea.setText(fetchedMaterial.toString());
            break;
        }
    }

    public static void getTransactionById(JTextArea outputArea) {
        int transactionId = 0;

        while (true) {
            try {
                String stringTransactionId = JOptionPane.showInputDialog("Enter transaction ID to search");
                if (stringTransactionId == null) {
                    return;
                } else if (stringTransactionId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                transactionId = Integer.parseInt(stringTransactionId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            Transaction fetchedTransaction = TransactionDAO.getTransaction(transactionId);
            if (fetchedTransaction == null) {
                outputArea.setText("Transaction not found");
                break;
            }
            outputArea.setText(fetchedTransaction.toString());
            break;
        }
    }

    public static void getAllTransactions(JTextArea outputArea) {
        outputArea.setText("");
        for (Transaction transaction : TransactionDAO.getAllTransactions()) {
            outputArea.append(transaction.toString() + "\n\n");
        }
    }
    public static void getTransactionItemById(JTextArea outputArea) {
        int transactionItemId = 0;

        while (true) {
            try {
                String stringTransactionItemId = JOptionPane.showInputDialog("Enter transaction item ID to search");
                if (stringTransactionItemId == null) {
                    return;
                } else if (stringTransactionItemId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                transactionItemId = Integer.parseInt(stringTransactionItemId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            TransactionItem fetchedTransactionItem = TransactionDAO.getTransactionItem(transactionItemId);
            if (fetchedTransactionItem == null) {
                outputArea.setText("Transaction not found");
                break;
            }
            outputArea.setText(fetchedTransactionItem.toString());
            break;
        }
    }

    public static void getAllTransactionItems(JTextArea outputArea) {
        outputArea.setText("");
        for (TransactionItem transactionItem : TransactionDAO.getAllTransactionItems()) {
            outputArea.append(transactionItem.toString() + "\n\n");
        }
    }

    public static void getTransactionItemsByTransactionId(JTextArea outputArea) {
        int transactionId = 0;
        List<TransactionItem> fetchedTransactionItems;
        outputArea.setText("");
        while (true) {
            try {
                String stringTransactionId = JOptionPane.showInputDialog("Enter transaction ID");
                if (stringTransactionId == null) {
                    return;
                } else if (stringTransactionId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                transactionId = Integer.parseInt(stringTransactionId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            fetchedTransactionItems = TransactionDAO.getTransactionItemsByTransactionId(transactionId);
            if (fetchedTransactionItems == null) {
                outputArea.setText("Transaction not found");
                return;
            }
            outputArea.setText(fetchedTransactionItems.toString());
            break;
        }
        outputArea.setText("");
        for (TransactionItem transactionItem : fetchedTransactionItems) {
            outputArea.append(transactionItem.toString() + "\n\n");
        }
    }

    public static void deleteTransaction(JTextArea outputArea) {
        int transactionId = 0;

        while (true) {
            try {
                String stringTransactionId = JOptionPane.showInputDialog("Enter transaction ID to delete\n(THIS INCLUDES ASSOCIATED TRANSACTION ITEMS)");
                if (stringTransactionId == null) {
                    return;
                } else if (stringTransactionId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    continue;
                }
                transactionId = Integer.parseInt(stringTransactionId);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
            Transaction fetchedTransaction = TransactionDAO.getTransaction(transactionId);
            if (fetchedTransaction == null) {
                outputArea.setText("Transaction not found");
                break;
            }
            TransactionDAO.deleteTransaction(transactionId);
            outputArea.setText("Deletion successful!");
            break;
        }
    }

    public static void totalMoneyFromBuying(JTextArea outputArea) { outputArea.setText("Total money from buy transactions: Php" + TransactionDAO.totalMoneyFromBuying()); }
    public static void totalMoneyFromSelling(JTextArea outputArea) { outputArea.setText("Total money from sell transactions: Php" + TransactionDAO.totalMoneyFromSelling()); }
    public static void averageMoneyFromBuying(JTextArea outputArea) { outputArea.setText("Average money from buy transactions: Php" + TransactionDAO.averageTotalFromBuying()); }
    public static void averageMoneyFromSelling(JTextArea outputArea) { outputArea.setText("Average money from sell transactions: Php" + TransactionDAO.averageTotalFromSelling()); }
}
