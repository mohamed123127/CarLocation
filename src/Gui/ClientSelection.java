package Gui;

import java.awt.*;
import java.sql.ResultSet;
import java.util.function.Consumer;
import javax.swing.*;

import Entities_CRUD.Car_CRUD;
import Entities_CRUD.Customers_CRUD;
import components.DataGridView;

public class ClientSelection extends JFrame {
    public ClientSelection(Consumer<Object[]> onCarSelected) {
        setTitle("Select Car");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); 

        String[] columnNames = { "id_client ", "nom", "prenom", "telephone", "email", "numero_permis"};

        ResultSet data = Customers_CRUD.GetData();
        DataGridView dataGridView = new DataGridView(columnNames, data);

        JScrollPane scrollPane = new JScrollPane(dataGridView);

        // Confirm button
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.addActionListener(e -> {
            Object[] selectedCar = dataGridView.getSelectedRowData();
            if (selectedCar != null) {
               

                onCarSelected.accept(selectedCar); 
                setVisible(false);
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
