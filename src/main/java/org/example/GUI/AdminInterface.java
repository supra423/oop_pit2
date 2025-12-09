package org.example.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class AdminInterface extends JFrame {
    private static final JButton[] operationButtons = {
//            new JButton("Add material"),
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
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(923, 96));
        bottomPanel.setBackground(Color.decode("#141414"));
        JPanel separator1 = new JPanel();
        separator1.setPreferredSize(new Dimension(923, 1));
        separator1.setBackground(Color.decode("#FFFFFF"));
        JPanel separator2 = new JPanel();
        separator2.setPreferredSize(new Dimension(923, 1));
        separator2.setBackground(Color.decode("#FFFFFF"));

        JTextArea outputArea = new JTextArea();
        outputArea.setPreferredSize(new Dimension(879, 650));
        outputArea.setEditable(false);

        JPanel leftAndRightPanelSeparator = new JPanel();
        leftAndRightPanelSeparator.setPreferredSize(new Dimension(1, 900));
        leftAndRightPanelSeparator.setBackground(Color.decode("#FFFFFF"));

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(364, 900));
        rightPanel.setBackground(Color.decode("#141414"));

        // scrollable panel inside the right panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(305, 1400));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        scrollPane.setPreferredSize(new Dimension(335, 875));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBorder(null);
        buttonPanel.setBackground(Color.decode("#141414"));

        add(mainPanel, BorderLayout.CENTER);
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
        middlePanel.add(outputArea, gbcOutputArea);

        gbcButtons.fill = 1;
        gbcButtons.weightx = 1;
        gbcButtons.insets = new Insets(0, 0, 50, 0);
        for (int i = 0; i < operationButtons.length; i++) {
            gbcButtons.gridy = i;
            operationButtons[i].setPreferredSize(new Dimension(0, 50));
            buttonPanel.add(operationButtons[i], gbcButtons);
        }
        rightPanel.add(scrollPane, gbc);

        pack();
    }
}
