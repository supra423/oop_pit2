package org.example.Controller;

import org.example.Model.*;
import org.example.View.DashboardInterface;

import javax.swing.*;
import java.util.List;

public class AdminController {
    private AdminController() {}
    public static void addMaterial(JTextArea outputArea) {
        String materialName;
        String unitOfMeasure;
        double materialBuyPrice = 0.0;
        double materialSellPrice = 0.0;
        int stockQuantity = 0;

        while (true) {
            materialName = JOptionPane.showInputDialog("Input material name");
            if (materialName == null) {
                return;
            } else if (materialName.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
            } else break;
        }
        while (true) {
            unitOfMeasure = JOptionPane.showInputDialog("Input unit of measure");
            if (unitOfMeasure == null) {
                return;
            }  else if (unitOfMeasure.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
            } else if (unitOfMeasure.length() > 5) {
                JOptionPane.showMessageDialog(null, "Max 5 characters!");
            } else break;
        }
            try {
                String stringMaterialBuyPrice, stringMaterialSellPrice, stringStockQuantity;
                while (true) {
                    stringMaterialBuyPrice = JOptionPane.showInputDialog("Input buy price (per " + unitOfMeasure + ")");
                    if (stringMaterialBuyPrice == null) {
                        return;
                    } else if (stringMaterialBuyPrice.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    } else break;
                }
                while (true) {
                    stringMaterialSellPrice = JOptionPane.showInputDialog("Input sell price (per " + unitOfMeasure + ")");
                    if (stringMaterialSellPrice == null) {
                        return;
                    } else if (stringMaterialSellPrice.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    } else break;
                }
                while (true) {
                    stringStockQuantity = JOptionPane.showInputDialog("Input initial stock quantity (per " + unitOfMeasure + ")");
                    if (stringStockQuantity == null) {
                        return;
                    } else if (stringStockQuantity.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                    } else break;
                }
                materialBuyPrice = Double.parseDouble(stringMaterialBuyPrice);
                materialSellPrice = Double.parseDouble(stringMaterialSellPrice);
                stockQuantity = Integer.parseInt(stringStockQuantity);
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Please only input numbers!");
            }
        Material newMaterial = new Material(materialName, unitOfMeasure, materialBuyPrice, materialSellPrice, stockQuantity);
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
        List<Material> materials = MaterialDAO.getAllMaterials();
        if (materials == null) {
            outputArea.setText("No rows found! Try adding a material!");
            return;
        }
        for (Material material : materials) {
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

    public static void appendMaterialQuantity(JTextArea outputArea) {
        int materialId, quantityToAdd;

        try {
            String stringMaterialId, stringQuantityToAdd;
            while (true) {
                stringMaterialId = JOptionPane.showInputDialog("Enter material ID to search");
                if (stringMaterialId == null) {
                    return;
                } else if (stringMaterialId.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                } else if (MaterialDAO.getMaterial(Integer.parseInt(stringMaterialId)) == null) {
                    JOptionPane.showMessageDialog(null, "Material not found! Make sure to input the right ID");
                } else break;
            }
            while (true) {
                stringQuantityToAdd = JOptionPane.showInputDialog("Enter amount to add");
                if (stringQuantityToAdd == null) {
                    return;
                } else if (stringQuantityToAdd.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please don't leave the field blank!");
                } else break;
            }
            materialId = Integer.parseInt(stringMaterialId);
            if (MaterialDAO.getMaterial(materialId) == null) {
                outputArea.setText("Material does not exist!");
                return;
            }
            quantityToAdd = Integer.parseInt(stringQuantityToAdd);
            MaterialDAO.incrementMaterialStock(materialId, quantityToAdd);
            outputArea.setText("Quantity successfully appended!");
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Please only input numbers!");
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
        List<Transaction> transactions = TransactionDAO.getAllTransactions();
        if (transactions == null) {
            outputArea.setText("No rows found! Try recording a transaction!");
            return;
        }
        for (Transaction transaction : transactions) {
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
        List<TransactionItem> transactionItems = TransactionDAO.getAllTransactionItems();
        if (transactionItems == null) {
            outputArea.setText("No rows found! Try adding a transaction to add associated transactions items");
            return;
        }
        for (TransactionItem transactionItem : transactionItems) {
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

    public static void rollBackTransaction(JTextArea outputArea) {
        int transactionId = 0;

        while (true) {
            try {
                String stringTransactionId = JOptionPane.showInputDialog("Enter transaction ID to roll back\n(Deletes transaction, its associated transaction items, and resets the stock)");
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
            TransactionDAO.rollBackTransaction(transactionId);
            outputArea.setText("Deletion successful!");
            break;
        }
    }

    public static void openDashboardInterface(JFrame adminFrame, JButton button) {
        button.setEnabled(false);
        adminFrame.dispose();
        SwingUtilities.invokeLater(DashboardInterface::new);
    }

    public static void totalMoneyFromBuying(JTextArea outputArea) { outputArea.setText("Total money from buy transactions: Php" + TransactionDAO.totalMoneyFromBuying()); }
    public static void totalMoneyFromSelling(JTextArea outputArea) { outputArea.setText("Total money from sell transactions: Php" + TransactionDAO.totalMoneyFromSelling()); }
    public static void averageMoneyFromBuying(JTextArea outputArea) { outputArea.setText("Average money from buy transactions: Php" + TransactionDAO.averageTotalFromBuying()); }
    public static void averageMoneyFromSelling(JTextArea outputArea) { outputArea.setText("Average money from sell transactions: Php" + TransactionDAO.averageTotalFromSelling()); }
}