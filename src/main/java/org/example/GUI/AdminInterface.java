package org.example.GUI;

import org.example.*;
import org.example.TransactionDAO.*;
import org.example.MaterialDAO.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class AdminInterface extends JFrame {
    private static JTextArea outputArea = new JTextArea();
    private static final JButton[] operationButtons = {
            LandingPage.createStyledButton("Add material", 12),
            LandingPage.createStyledButton("Delete material", 12),
            LandingPage.createStyledButton("Get all materials", 12),
            LandingPage.createStyledButton("Get material by ID", 12),
            LandingPage.createStyledButton("Get transaction by ID", 12),
            LandingPage.createStyledButton("Get all transactions", 12),
            LandingPage.createStyledButton("Get transaction item by ID", 12),
            LandingPage.createStyledButton("Get all transaction items", 12),
            LandingPage.createStyledButton("Get transaction items by transaction ID", 12),
            LandingPage.createStyledButton("<html>Delete transaction<br>(Includes associated transaction items)</html>", 12),
            LandingPage.createStyledButton("Total money from buying", 12),
            LandingPage.createStyledButton("Total money from selling", 12),
            LandingPage.createStyledButton("Average money from buying", 12),
            LandingPage.createStyledButton("Average money from selling", 12),
    };
    static ImageIcon logo;
    static ImageIcon rawLogo = new ImageIcon(
            Objects.requireNonNull(AdminInterface.class.getResource("/Logo.png")));
    static {
        Image resizedImage = rawLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedImage);
        operationButtons[0].addActionListener(e -> { // add material
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
        });
        operationButtons[1].addActionListener(e -> { // delete material
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
        });
        operationButtons[2].addActionListener(e -> { // get all materials
            outputArea.setText("");
            for (Material material : MaterialDAO.getAllMaterials()) {
                outputArea.append(material.toString() + "\n\n");
            }
        });
        operationButtons[3].addActionListener(e -> { // get material by id
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
        });
        operationButtons[4].addActionListener(e -> { // get transaction by id
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
        });
        operationButtons[5].addActionListener(e -> { // get all transactions
            outputArea.setText("");
            for (Transaction transaction : TransactionDAO.getAllTransactions()) {
                outputArea.append(transaction.toString() + "\n\n");
            }
        });
        operationButtons[6].addActionListener(e -> { // get transaction item by id
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
        });
        operationButtons[7].addActionListener(e -> { // get all transaction items
            outputArea.setText("");
            for (TransactionItem transactionItem : TransactionDAO.getAllTransactionItems()) {
                outputArea.append(transactionItem.toString() + "\n\n");
            }
        });
        operationButtons[8].addActionListener(e -> { // get transaction items by transaction id
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
        });
        operationButtons[9].addActionListener(e -> { // delete transaction by transaction ID (including associated transaction items)
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
        });
        operationButtons[10].addActionListener(e -> { // total money from buying
            outputArea.setText("Total money from buy transactions: Php" + TransactionDAO.totalMoneyFromBuying());
        });
        operationButtons[11].addActionListener(e -> { // total money from selling

            outputArea.setText("Total money from sell transactions: Php" + TransactionDAO.totalMoneyFromSelling());
        });
        operationButtons[12].addActionListener(e -> { // average money from buying

            outputArea.setText("Average money from buy transactions: Php" + TransactionDAO.averageTotalFromBuying());
        });
        operationButtons[13].addActionListener(e -> { // average money from selling

            outputArea.setText("Average money from sell transactions: Php" + TransactionDAO.averageTotalFromSelling());
        });
    }
    public AdminInterface() {
        setTitle("The Garage");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbcOutputArea = new GridBagConstraints();
        GridBagConstraints gbcButtons = new GridBagConstraints();
        GridBagConstraints gbcLogo = new GridBagConstraints();

        JPanel topPanelComponentsBundle = new JPanel();
        topPanelComponentsBundle.setBackground(Color.decode("#141414"));
        JLabel logoLabel = new JLabel(logo);
        JPanel topPanelSeparator = new JPanel();
        topPanelSeparator.setBackground(Color.decode("#FFFFFF"));
        topPanelSeparator.setPreferredSize(new Dimension(1, 90));
        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        adminLabel.setForeground(Color.decode("#FFFFFF"));

        logoLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        topPanelSeparator.setBorder(new EmptyBorder(0, 10, 0, 10));
        adminLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.setBackground(Color.decode("#141414"));
        mainPanel.setPreferredSize(new Dimension(1288, 900));

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(923, 900));
        leftPanel.setBackground(Color.decode("#141414"));

        // these are panels inside the leftPanel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(923, 108));
        topPanel.setBackground(Color.decode("#141414"));
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setPreferredSize(new Dimension(923, 686));
        middlePanel.setBackground(Color.decode("#141414"));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(923, 96));
        bottomPanel.setBackground(Color.decode("#141414"));
        JPanel separator1 = new JPanel();
        separator1.setPreferredSize(new Dimension(923, 1));
        separator1.setBackground(Color.decode("#FFFFFF"));
        JPanel separator2 = new JPanel();
        separator2.setPreferredSize(new Dimension(923, 1));
        separator2.setBackground(Color.decode("#FFFFFF"));

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBackground(Color.decode("#141414"));
        backButtonPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        JButton backButton = LandingPage.createStyledButton("BACK", 24);
        backButton.setPreferredSize(new Dimension(150, 50));

        JScrollPane scrollOutput = new JScrollPane(outputArea);
        scrollOutput.setPreferredSize(new Dimension(879, 650));
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel leftAndRightPanelSeparator = new JPanel();
        leftAndRightPanelSeparator.setPreferredSize(new Dimension(1, 900));
        leftAndRightPanelSeparator.setBackground(Color.decode("#FFFFFF"));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(364, 900));
        rightPanel.setBackground(Color.decode("#141414"));

        // scrollable panel inside the right panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(305, 1400));
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        buttonScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buttonScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        buttonScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        buttonScrollPane.setPreferredSize(new Dimension(335, 875));
        buttonScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        buttonScrollPane.setBorder(null);
        buttonScrollPane.getViewport().setBorder(null);
        buttonPanel.setBackground(Color.decode("#141414"));

        add(mainScrollPane, BorderLayout.CENTER);
        gbc.gridx = 0;
        mainPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        mainPanel.add(leftAndRightPanelSeparator, gbc);
        gbc.gridx = 2;
        mainPanel.add(rightPanel, gbc);
        gbc.gridx = 0;

        gbc.gridy = 0;
        leftPanel.add(topPanel, gbc);
        gbc.gridy = 1;
        leftPanel.add(separator1, gbc);
        gbc.gridy = 2;
        leftPanel.add(middlePanel, gbc);
        gbc.gridy = 3;
        leftPanel.add(separator2, gbc);
        gbc.gridy = 4;
        leftPanel.add(bottomPanel, gbc);
        gbc.gridy = 0;

        topPanelComponentsBundle.add(logoLabel);
        topPanelComponentsBundle.add(topPanelSeparator);
        topPanelComponentsBundle.add(adminLabel);

        topPanel.add(topPanelComponentsBundle, BorderLayout.WEST);

        gbcOutputArea.insets = new Insets(10, 10, 10, 10);
        middlePanel.add(scrollOutput, gbcOutputArea);

        gbcButtons.fill = 1;
        gbcButtons.weightx = 1;
        gbcButtons.insets = new Insets(0, 0, 50, 0);
        for (int i = 0; i < operationButtons.length; i++) {
            gbcButtons.gridy = i;
            operationButtons[i].setPreferredSize(new Dimension(0, 50));
            buttonPanel.add(operationButtons[i], gbcButtons);
        }
        rightPanel.add(buttonScrollPane, gbc);

        backButtonPanel.add(backButton);
        bottomPanel.add(backButtonPanel, BorderLayout.WEST);
        pack();
    }
}
