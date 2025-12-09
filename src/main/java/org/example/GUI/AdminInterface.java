package org.example.GUI;

import javax.swing.*;
import java.awt.*;

public class AdminInterface extends JFrame {
    public AdminInterface() {
        setTitle("The Garage");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(1293, 900));
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBackground(Color.decode("#141414"));
        add(mainPanel, BorderLayout.CENTER);
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel separator1 = new JPanel();
        JPanel separator2 = new JPanel();
        topPanel.setBackground(Color.decode("#141414"));
        middlePanel.setBackground(Color.decode("#141414"));
        bottomPanel.setBackground(Color.decode("#141414"));
        separator1.setBackground(Color.decode("#FFFFFF"));
        separator2.setBackground(Color.decode("#FFFFFF"));
        topPanel.setPreferredSize(new Dimension(1293, 116));
        middlePanel.setPreferredSize(new Dimension(1293, 660));
        bottomPanel.setPreferredSize(new Dimension(1293, 116));
        separator1.setPreferredSize(new Dimension(1293, 1));
        separator2.setPreferredSize(new Dimension(1293, 1));
        gbc.gridy = 0;
        mainPanel.add(topPanel, gbc);
        gbc.gridy = 1;
        mainPanel.add(separator1, gbc);
        gbc.gridy = 2;
        mainPanel.add(middlePanel, gbc);
        gbc.gridy = 3;
        mainPanel.add(separator2, gbc);
        gbc.gridy = 4;
        mainPanel.add(bottomPanel, gbc);
        pack();
    }
}
