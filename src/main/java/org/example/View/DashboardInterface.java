package org.example.View;

import org.example.Controller.DashboardController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class DashboardInterface extends JFrame {
    private static JTextArea totalArea = new JTextArea();
    private static JTable materialsTable;

    static ImageIcon logo;
    static ImageIcon rawLogo = new ImageIcon(
            Objects.requireNonNull(DashboardInterface.class.getResource("/Logo.png")));

    static {
        Image resizedImage = rawLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedImage);
    }

    public DashboardInterface() {
        setTitle("The Garage - Dashboard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#141414"));

        // Top Panel with logo and title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1288, 108));
        topPanel.setBackground(Color.decode("#141414"));

        JPanel topPanelComponentsBundle = new JPanel();
        topPanelComponentsBundle.setBackground(Color.decode("#141414"));
        JLabel logoLabel = new JLabel(logo);
        JPanel topPanelSeparator = new JPanel();
        topPanelSeparator.setBackground(Color.decode("#FFFFFF"));
        topPanelSeparator.setPreferredSize(new Dimension(1, 90));
        JLabel titleLabel = new JLabel("Buy & Sell");
        titleLabel.setFont(new Font("Montserrat", Font.PLAIN, 35));
        titleLabel.setForeground(Color.decode("#FFFFFF"));

        logoLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        topPanelSeparator.setBorder(new EmptyBorder(0, 10, 0, 10));
        titleLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        topPanelComponentsBundle.add(logoLabel);
        topPanelComponentsBundle.add(topPanelSeparator);
        topPanelComponentsBundle.add(titleLabel);
        topPanel.add(topPanelComponentsBundle, BorderLayout.WEST);

        JPanel separator1 = new JPanel();
        separator1.setPreferredSize(new Dimension(1, 1));
        separator1.setBackground(Color.decode("#FFFFFF"));

        // Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.decode("#141414"));

        // Left Panel - Materials Available
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.decode("#141414"));

        JLabel materialsLabel = new JLabel("Materials Available");
        materialsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        materialsLabel.setForeground(Color.decode("#FFFFFF"));
        materialsLabel.setBackground(Color.decode("#3C3C3C"));
        materialsLabel.setOpaque(true);
        materialsLabel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Table for materials
        materialsTable = new JTable(DashboardController.getAllMaterials());
        materialsTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        materialsTable.setRowHeight(25);
        materialsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane tableScrollPane = new JScrollPane(materialsTable);

        leftPanel.add(materialsLabel, BorderLayout.NORTH);
        leftPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Vertical separator
        JPanel verticalSeparator = new JPanel();
        verticalSeparator.setPreferredSize(new Dimension(1, 1));
        verticalSeparator.setBackground(Color.decode("#FFFFFF"));

        // Right Panel - Total Display
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.decode("#141414"));

        JLabel totalLabel = new JLabel("TOTAL:");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalLabel.setForeground(Color.decode("#FFFFFF"));
        totalLabel.setBackground(Color.decode("#3C3C3C"));
        totalLabel.setOpaque(true);
        totalLabel.setBorder(new EmptyBorder(10, 15, 10, 15));

        totalArea.setEditable(false);
        totalArea.setFont(new Font("Monospaced", Font.BOLD, 36));
        totalArea.setBackground(Color.decode("#D9D9D9"));
        JScrollPane totalScrollPane = new JScrollPane(totalArea);

        rightPanel.add(totalLabel, BorderLayout.NORTH);
        rightPanel.add(totalScrollPane, BorderLayout.CENTER);

        // Add left and right panels to content panel
        GridBagConstraints contentGbc = new GridBagConstraints();
        contentGbc.fill = GridBagConstraints.BOTH;
        contentGbc.weightx = 1.0;
        contentGbc.weighty = 1.0;
        contentGbc.gridx = 0;
        contentGbc.gridy = 0;
        contentGbc.insets = new Insets(50, 50, 50, 50); //Material panel (iya spacing)
        contentPanel.add(leftPanel, contentGbc);

        contentGbc.fill = GridBagConstraints.VERTICAL;
        contentGbc.weightx = 0;
        contentGbc.gridx = 1;
        contentGbc.insets = new Insets(10, 5, 10, 5); //Line between Material and Total panel (iya spacing)
        contentPanel.add(verticalSeparator, contentGbc);

        contentGbc.fill = GridBagConstraints.BOTH;
        contentGbc.weightx = 0.5;
        contentGbc.gridx = 2;
        contentGbc.insets = new Insets(20, 35, 20, 35); //Total panel (iya spacing)
        contentPanel.add(rightPanel, contentGbc);

        JPanel separator2 = new JPanel();
        separator2.setPreferredSize(new Dimension(1, 1));
        separator2.setBackground(Color.decode("#FFFFFF"));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 23));
        bottomPanel.setPreferredSize(new Dimension(1, 96));
        bottomPanel.setBackground(Color.decode("#141414"));

        JButton recordBuyButton = StyledButtonCreator.createStyledButton("RECORD A BUY", 18);
        recordBuyButton.setPreferredSize(new Dimension(250, 55));

        JButton recordSellButton = StyledButtonCreator.createStyledButton("RECORD A SELL", 18);
        recordSellButton.setPreferredSize(new Dimension(250, 55));

        JButton adminButton = StyledButtonCreator.createStyledButton("ADMIN", 18);
        JButton backButton = StyledButtonCreator.createStyledButton("BACK", 18);
        adminButton.setPreferredSize(new Dimension(250, 55));
        adminButton.addActionListener(e-> DashboardController.openAdminInterface(this, adminButton));

        backButton.setPreferredSize(new Dimension(250, 55));
        backButton.addActionListener(e-> DashboardController.openLandingPage(this, backButton));

        bottomPanel.add(backButton);
        bottomPanel.add(recordBuyButton);
        bottomPanel.add(recordSellButton);
        bottomPanel.add(adminButton);

        // Add all panels to main panel with proper weights
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(topPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.gridy = 1;
        mainPanel.add(separator1, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.gridy = 2;
        mainPanel.add(contentPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.gridy = 3;
        mainPanel.add(separator2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.gridy = 4;
        mainPanel.add(bottomPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        pack();
    }
}