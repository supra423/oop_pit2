package org.example.View;

import org.example.Controller.LandingPageController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LandingPage extends JFrame {
    private static Image backgroundImage;

    static {
        try {
            backgroundImage = new ImageIcon(
                    Objects.requireNonNull(LandingPage.class.getResource("/landingpage.png"))
            ).getImage();
        } catch (Exception e) {
            System.out.println("Background image not found: " + e.getMessage());
        }
    }

    public LandingPage() {
        setTitle("The Garage");
        setSize(1288, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, -300, 0));

        mainPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton startButton = StyledButtonCreator.createStyledButton("START", 24);
        startButton.addActionListener(e -> LandingPageController.openDashboard(this, startButton));
        buttonPanel.add(startButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        add(mainPanel);
        setVisible(true);
    }
}