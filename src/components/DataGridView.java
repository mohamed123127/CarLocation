package components;

import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DataGridView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    

    // Constructor without ActionListener
    public DataGridView(String[] columnNames, ResultSet data) {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        table = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel); // إعداد أداة التصفية
        table.setRowSorter(rowSorter);

        // تعبئة الجدول بالبيانات
        SetDataSource(data);
        applyAlternatingRowColors(new Color(173, 216, 230), Color.WHITE);

        // إضافة الجدول إلى شريط التمرير
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public DataGridView(String[] columnNames, ResultSet data, int[] hiddenColumnIndex) {
        this(columnNames, data);
        for (int index : hiddenColumnIndex) {
            if (index >= 0 && index < table.getColumnCount()) {
                table.getColumnModel().getColumn(index).setMinWidth(0);
                table.getColumnModel().getColumn(index).setMaxWidth(0);
                table.getColumnModel().getColumn(index).setWidth(0);
                table.getColumnModel().getColumn(index).setResizable(false);
            }
        }
        applyAlternatingRowColors(new Color(173, 216, 230), Color.WHITE);

    }

    public DataGridView(String[] columnNames, Object[][] data) {
        // تحقق من أن أسماء الأعمدة والبيانات ليست null
        if (columnNames == null || data == null) {
            columnNames = new String[0];
            data = new Object[0][0];
        }

        // تهيئة DefaultTableModel
        tableModel = new DefaultTableModel(data, columnNames);

        // إنشاء JTable باستخدام tableModel
        table = new JTable(tableModel);
        applyAlternatingRowColors(new Color(173, 216, 230), Color.WHITE);

        // إعداد JScrollPane
        // this.setViewportView(table);
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void removeRow(int rowIndex) {
        tableModel.removeRow(rowIndex);
    }

    public void SetDataSource(ResultSet data) {
        try {
            tableModel.setRowCount(0); // تفريغ البيانات الحالية
            while (data != null && data.next()) {
                int columnCount = data.getMetaData().getColumnCount();
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = data.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetRowColors() {
        // إزالة أي معالج مخصص للخلايا
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
        table.repaint(); // إعادة رسم الجدول
    }

    
    public void applyAlternatingRowColors(Color evenRowColor, Color oddRowColor) {
        table.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer(evenRowColor, oddRowColor));
        table.repaint(); // إعادة رسم الجدول
    }
    

    public Object[] getSelectedRowData() {
        int selectedRow = table.getSelectedRow(); // Get the index of the selected row
        if (selectedRow != -1) {
            int columnCount = table.getColumnCount();
            Object[] rowData = new Object[columnCount];
    
            // Loop through each column to retrieve the value for the selected row
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = table.getValueAt(selectedRow, column);
            }
    
            return rowData;  // Return the data as an array of objects
        } else {
            return null; // Return null if no row is selected
        }
    }

    public void filterTable(String searchQuery) {
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            rowSorter.setRowFilter(null); // إزالة التصفية إذا كان النص فارغًا
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchQuery)); // تصفية الصفوف بناءً على النص
        }
    }
    // Custom cell renderer for alternating row colors
    private static class AlternatingRowColorRenderer extends DefaultTableCellRenderer {
        private final Color evenRowColor;
        private final Color oddRowColor;
    
        // السماح بتمرير ألوان مخصصة
        public AlternatingRowColorRenderer(Color evenRowColor, Color oddRowColor) {
            this.evenRowColor = evenRowColor;
            this.oddRowColor = oddRowColor;
        }
    
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    
            if (!isSelected) {
                if (row % 2 == 0) {
                    component.setBackground(evenRowColor);
                } else {
                    component.setBackground(oddRowColor);
                }
                component.setForeground(Color.BLACK);
            } else {
                component.setBackground(table.getSelectionBackground());
                component.setForeground(table.getSelectionForeground());
            }
    
            return component;
        }
    }
    
}
