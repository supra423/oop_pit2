package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Launch the landing page on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LandingPage landingPage = new LandingPage();
            landingPage.setVisible(true);
        });
    }
}