package Gui;
import CustomControle.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Statistiques extends JFrame {
    private JPanel centerPanel; // Current center panel reference
    private JPanel southPanel;  // Current south panel reference
    public Container MainPanel;

    public Statistiques() {
        setTitle("Car Rental Management System - Reporting Section");
        setSize(1000, 800);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        // Top Panel with ComboBox
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      
        String[] types = { "Vehicle Statistics", "Customer Statistics", "Revenue Statistics" };
        ComboBoxStyle1 typeComboBox = new ComboBoxStyle1(types);
        // Set default selection
        typeComboBox.setSelectedItem("Vehicle Statistics");
        titlePanel.add(typeComboBox);
        add(titlePanel, BorderLayout.NORTH);

        // Initial Panels
        centerPanel = new StatistiquesVehicule().StatistiquesVehicule();  // Set initial panel to Vehicle Statistics
        southPanel = new StatistiquesVehicule().tabelStatistiquesVehicule();
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // ActionListener for ComboBox
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) typeComboBox.getSelectedItem();

                // Switch and replace panels dynamically
                JPanel newCenterPanel = switchCenterPanel(selected);
                replaceCenterPanel(newCenterPanel);

                JPanel newSouthPanel = switchSouthPanel(selected);
                replaceSouthPanel(newSouthPanel);
            }
        });

        //setVisible(true); // Make the frame visible after adding components
    }

    // Replace the center panel
    private void replaceCenterPanel(JPanel newPanel) {
        remove(centerPanel); // Remove the old panel
        centerPanel = newPanel; // Update reference
        add(centerPanel, BorderLayout.CENTER); // Add the new panel
        revalidate(); // Refresh layout
        repaint(); // Repaint frame
    }

    // Replace the south panel
    private void replaceSouthPanel(JPanel newPanel) {
        remove(southPanel); // Remove the old panel
        southPanel = newPanel; // Update reference
        add(southPanel, BorderLayout.SOUTH); // Add the new panel
        revalidate(); // Refresh layout
        repaint(); // Repaint frame
    }

    // Switch center panel based on the ComboBox value
    private JPanel switchCenterPanel(String selected) {
        switch (selected) {
            case "Vehicle Statistics":
                return new StatistiquesVehicule().StatistiquesVehicule();
            case "Customer Statistics":
                return new StatistiquesCustomers().StatistiquesCustomers();
            case "Revenue Statistics":
                return new StatistiquesRevenue().StatistiquesRevenue();
            default:
                JPanel defaultPanel = new JPanel();
                defaultPanel.setBackground(Color.LIGHT_GRAY);
                defaultPanel.add(new JLabel("Default Center Panel"));
                return defaultPanel;
        }
    }

    // Switch south panel based on the ComboBox value
    private JPanel switchSouthPanel(String selected) {
        switch (selected) {
            case "Vehicle Statistics":
                return new StatistiquesVehicule().tabelStatistiquesVehicule();
            case "Customer Statistics":
                return new StatistiquesCustomers().tabelStatistiquesCustomers();
            case "Revenue Statistics":
                return new StatistiquesRevenue().tabelStatistiquesRevenue();
            default:
                JPanel defaultPanel = new JPanel();
                defaultPanel.setBackground(Color.LIGHT_GRAY);
                defaultPanel.add(new JLabel("Default South Panel"));
                return defaultPanel;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Statistiques::new);
    }
}
