package Gui;

import Entities_CRUD.Car_CRUD;
import components.DataGridView;
import java.awt.*;
import java.sql.ResultSet;
import java.util.function.Consumer;
import javax.swing.*;

public class CarSelection extends JFrame {
    public CarSelection(Consumer<Object[]> onCarSelected) {
        setTitle("Select Car");
        setSize(600, 500);
        setLocationRelativeTo(null); 

        String[] columnNames = { "ID", "Marque", "Modele", "Annee", "Type", "Carburant", "Prix par jour", "Etat" };

        ResultSet data = Car_CRUD.GetAll();
        DataGridView dataGridView = new DataGridView(columnNames, data);

        JScrollPane scrollPane = new JScrollPane(dataGridView);

        // Confirm button
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> {
            Object[] selectedCar = dataGridView.getSelectedRowData();
            if (selectedCar != null) {
                // الحصول على السعر لكل يوم من العمود 6
                
                

                onCarSelected.accept(selectedCar); 
                setVisible(false);
                dispose();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a car!");
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
