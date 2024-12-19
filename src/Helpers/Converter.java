package Helpers;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Converter {
    /*public void displayInTable(Resultset data) {
        try {
            // Create TableModel
            DefaultTableModel model = new DefaultTableModel();

            // Get metadata to add column names
            ResultSetMetaData metaData = data.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows
            while (data.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = data.getObject(i);
                }
                model.addRow(row);
            }

            // Set model to JTable
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage());
        }
    }*/
}
