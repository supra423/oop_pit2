package org.example.Controller;

import org.example.View.AdminInterface;
import org.example.View.LandingPage;
import org.example.Model.Material;
import org.example.Model.MaterialDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DashboardController {

    public static void openAdminInterface(JFrame dashboardFrame, JButton button) {
        button.setEnabled(false);
        dashboardFrame.dispose();
        SwingUtilities.invokeLater(AdminInterface::new);
    }

    public static void openLandingPage(JFrame dashboardFrame, JButton button) {
        button.setEnabled(false);
        dashboardFrame.dispose();
        SwingUtilities.invokeLater(LandingPage::new);
    }

    public static DefaultTableModel getAllMaterials() {
        Material[] materials = MaterialDAO.getAllMaterials().toArray(new Material[0]);
        String[] columnNames = {"Material name", "Unit of measure", "Buy price", "Sell price","Stock quantity"};
        Object[][] data = new Object[materials.length][columnNames.length];
        for (int i = 0; i < materials.length; i++) {
            data[i][0] = materials[i].name();
            data[i][1] = materials[i].unitOfMeasure();
            data[i][2] = materials[i].buyPrice();
            data[i][3] = materials[i].sellPrice();
            data[i][4] = materials[i].stockQuantity();
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
}
