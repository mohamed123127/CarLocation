package Gui;

import CustomControle.ButtonStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Car_CRUD;
import components.DataGridView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.*;

public class Car extends JFrame {
    public DataGridView dataGridView;
    public JPanel MainPanel;
    public TextFieldStyle1 searchField;
    public Car() {
        // Main window setup
        //this.setTitle("Console de gestion des voitures");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(1000, 600);
        //this.getContentPane().setBackground(new Color(245, 245, 245));

        // Main panel creation
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header label using LabelStyle1
        LabelStyle1 headerLabel = new LabelStyle1("Gestion des voitures", 300, 30);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBackground(new Color(245, 245, 245));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Top panel creation
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 10));
        topPanel.setBackground(new Color(245, 245, 245));

        // Search field using TextFieldStyle1
        searchField = new TextFieldStyle1(200, 30);

        // Use ComboBoxStyle1 for categories

        // Buttons using ButtonStyle1
        ButtonStyle1 addButton = new ButtonStyle1("Ajouter");
        ButtonStyle1 editButton = new ButtonStyle1("Modifier");
       /*  editButton.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "f");
        });*/
        ButtonStyle1 deleteButton = new ButtonStyle1("Supprimer");

        // Button actions
        addButton.addActionListener(e -> {
            new AddCar(this);
        });

        // Add components to the top panel
        topPanel.add(new LabelStyle1("Rechercher:", 110, 30));
        topPanel.add(searchField);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        // Add the top panel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Setup data table
        String[] columnNames = { "ID", "Marque", "Modele", "Annee", "Type", "Carburant", "Prix par jour", "Etat" };
        ResultSet data = Car_CRUD.GetAll();

        // Use DataGridView for displaying data
        dataGridView = new DataGridView(columnNames, data);
        dataGridView.setBackground(new Color(245, 245, 245));
        mainPanel.add(dataGridView, BorderLayout.CENTER);

        // Handle Edit button click
        editButton.addActionListener(e -> {
            Object[] selectedRow = dataGridView.getSelectedRowData();
            if (selectedRow != null) {
                new AddCar(this,selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour modifier.", "Avertissement",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Handle Delete button click
        deleteButton.addActionListener(e -> {
            Object[] selectedRow = dataGridView.getSelectedRowData();
            if (selectedRow != null) {
                // Get the ID of the selected car (first column of the row)
                String carId = selectedRow[0].toString();

                // Ask for confirmation
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Êtes-vous sûr de vouloir supprimer la voiture avec ID: " + carId + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Perform the delete operation using the Car_CRUD class
                    boolean success = Car_CRUD.DeleteCarById(carId); // Assuming you implement this method

                    if (success) {
                        // Show success message
                        JOptionPane.showMessageDialog(this, "Voiture supprimée avec succès.");

                        // Refresh the table with updated data
                        ResultSet updatedData = Car_CRUD.GetAll();
                        DataGridView updatedDataGridView = new DataGridView(columnNames, updatedData);

                        // Update the UI with the new data
                        this.getContentPane().removeAll(); // Remove old components
                        this.add(updatedDataGridView, BorderLayout.CENTER); // Add updated DataGridView
                        this.add(topPanel, BorderLayout.NORTH);
                        this.revalidate(); // Refresh layout
                        this.repaint(); // Redraw frame
                    } else {
                        // Show error message if deletion fails
                        JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de la voiture.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // Show a warning if no row is selected
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour supprimer.", "Avertissement",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Handle search action
        //searchField.addActionListener(e -> performSearch(searchField.getText(),topPanel,addButton,editButton, deleteButton));
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                dataGridView.filterTable(searchField.getText());
            }
        });
        // Set up and display the main window
        this.add(mainPanel);
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
        MainPanel = mainPanel;
    }

    public JPanel getMainPanel(){
        return MainPanel;
    }


    private void performSearch(String searchText , JPanel topPanel, ButtonStyle1 addButton, ButtonStyle1 editButton, ButtonStyle1 deleteButton) {
        /*ResultSet data = Car_CRUD.GetFilteredData(searchText);
        String[] columnNames = { "ID", "Marque", "Modele", "Annee", "Type", "Carburant", "Prix par jour", "Etat" };
        dataGridView = new DataGridView(columnNames, data);
        deleteButton.addActionListener(e -> {
            Object[] selectedRow = dataGridView.getSelectedRowData();
            if (selectedRow != null) {
                String carId = selectedRow[0].toString();
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Êtes-vous sûr de vouloir supprimer la voiture avec ID: " + carId + "?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
    
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = Car_CRUD.DeleteCarById(carId);
    
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Voiture supprimée avec succès.");
                        ResultSet updatedData = Car_CRUD.GetAll();
                        DataGridView updatedDataGridView = new DataGridView(columnNames, updatedData);
    
                        // إزالة المكونات القديمة
                        this.getContentPane().removeAll();
    
                        // إضافة الجدول المحدث
                        this.add(updatedDataGridView, BorderLayout.CENTER);
    
                        // إضافة الأزرار مرة أخرى
                        this.add(topPanel, BorderLayout.NORTH);
    
                        // تحديث الواجهة
                        this.revalidate();
                        this.repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de la voiture.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        });
        // Update the table with filtered data
        this.getContentPane().removeAll(); // Remove the existing components
        this.add(dataGridView, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.revalidate(); // Refresh the layout
        this.repaint(); // Redraw the frame*/
        dataGridView.filterTable(searchText);
    }

}
