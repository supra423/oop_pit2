package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("The Garage");
        setSize(1288, 966); // Match your image dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create panel with background image
        JPanel mainPanel = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = new ImageIcon(getClass().getResource("/landingpage.png")).getImage();
                } catch (Exception e) {
                    System.out.println("Background image not found: " + e.getMessage());
                }
            }

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

        // Add spacing to push button to bottom
        mainPanel.add(Box.createVerticalGlue());

        // START button
        JButton startButton = createStyledButton("START");
        startButton.addActionListener(e -> openDashboard());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make transparent to show background
        buttonPanel.add(startButton);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Paint rounded background
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); // 30 is corner radius

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(200, 60));
        button.setFont(new Font("Montserrat", Font.BOLD, 24));
        button.setForeground(new Color(26, 26, 26));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false); // Don't paint default background
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(224, 224, 224));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(204, 204, 204));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(224, 224, 224));
            }
        });

        return button;
    }

    private void openDashboard() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            DashboardInterface dashboard = new DashboardInterface();
            dashboard.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPage landingPage = new LandingPage();
            landingPage.setVisible(true);
        });
    }
}