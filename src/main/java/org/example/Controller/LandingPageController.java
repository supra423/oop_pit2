package org.example.Controller;

import org.example.View.DashboardInterface;

import javax.swing.*;

public class LandingPageController {
    public static void openDashboard(JFrame landingPageFrame, JButton button) {
        button.setEnabled(false);
        landingPageFrame.dispose();
        SwingUtilities.invokeLater(DashboardInterface::new);
    }
}