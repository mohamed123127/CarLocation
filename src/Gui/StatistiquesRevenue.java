package Gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import CustomControle.LabelStyle1;

import java.sql.SQLException;


import Helpers.DbOperation;

import java.awt.*;
import java.sql.ResultSet;
public class StatistiquesRevenue extends JPanel {
    Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
    DbOperation dbOperation = new DbOperation();
    public int[] getTotalmois(ResultSet rs) {
        int[] values = new int[12]; // Array for 12 months, default initialized to 0.
    
        try {
            while (rs.next()) {
                // Extract the month as an integer
                int month = Integer.parseInt(rs.getString("month").split("-")[1]);
                int totalRentValue = rs.getInt("total_rent_value"); // Retrieve total rent value
                
                // Populate the corresponding month in the array (0-indexed)
                values[month - 1] = totalRentValue;
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception in getTotalmois: " + e.getMessage());
            e.printStackTrace();
        }
        
        return values; // Return the array of monthly values
    }
    public static String getTopValue(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int max = array[0]; // Initialize max with the first element
        int topmonth=0,i;

        for ( i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i]; // Update max if a larger value is found
                topmonth=i;
            }
        }
        if (topmonth < 1 || topmonth > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        return months[topmonth - 1];
       
    }

     public JPanel PrintpanelsTop5(int rank, String carName,double statice) {
       
         // Create a panel for a specific rank
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10)); // Grid layout with 1 row and 3 columns
        JLabel rankLabel = new JLabel( rank + "star : ");
        JLabel carNameLabel = new JLabel(carName);
        JLabel carstaticLabel = new JLabel(String.valueOf(statice)+"%");
        // Add labels to the panel
        panel.add(rankLabel);
        panel.add(carNameLabel);
        panel.add(carstaticLabel);
        return panel; // Return the created panel
    }
    public JPanel PrintpanelsUseRate(String carName,double statice) {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1, 2, 10, 30)); // Grid layout with 1 row and 3 columns
        JPanel panel1_1 = new JPanel();
        
        JPanel panel1_2 = new JPanel();
        JLabel carNameLabel = new JLabel(carName);
        JLabel carstaticLabel = new JLabel(String.valueOf(statice)+"%");
        

        panel1_1.add(carNameLabel);
        panel1_2.add(carstaticLabel);
        panelMain.add(panel1_1);
        panelMain.add(panel1_2);
        panel1_2.setBackground(Color.GRAY); 
        return panelMain;
    }
    public JPanel Printpanels(String carName,double statice) {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1, 2, 10, 30)); // Grid layout with 1 row and 3 columns
        JPanel panel1_1 = new JPanel();
        
        JPanel panel1_2 = new JPanel();
        JLabel carNameLabel = new JLabel(carName);
        JLabel carstaticLabel = new JLabel(String.valueOf(statice));
        

        panel1_1.add(carNameLabel);
        panel1_2.add(carstaticLabel);
        panelMain.add(panel1_1);
        panelMain.add(panel1_2);
        panel1_2.setBackground(Color.GRAY); 
        return panelMain;
    }
    public JPanel StatistiquesRevenue(){
        DbOperation dbOperation = new DbOperation();
        JPanel centerPanel = new JPanel();
        // Center Panel for Charts and Tables and other component
        JPanel imegePanel = new JPanel();
       
        imegePanel.setLayout(new BorderLayout());
        JLabel titelimage = new LabelStyle1("Best Month ");
   
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String query = "SELECT DATE_FORMAT(p.date_paiement, '%Y-%m') AS month, SUM(p.montant) AS total_rent_value " +
                           "FROM paiement p " +
                           "WHERE YEAR(p.date_paiement) = " + currentYear + " " +
                           "GROUP BY DATE_FORMAT(p.date_paiement, '%Y-%m') " +
                           "ORDER BY DATE_FORMAT(p.date_paiement, '%Y-%m') ASC";
        try {
           
            
            ResultSet rs2 = dbOperation.GetSpecialData(query);
            JLabel  bestmonth =new LabelStyle1(getTopValue(getTotalmois(rs2)));
           
            imegePanel.add(bestmonth,BorderLayout.SOUTH);
            // Load the image from the img folder
        
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/calender.png"));

            Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            // Add the image to the panel
            imegePanel.add(titelimage,BorderLayout.NORTH);
            imegePanel.add(imageLabel,BorderLayout.CENTER);
            imegePanel.setBorder(border);
        } catch (NullPointerException e) {
            System.out.println("Image not found: Ensure the file exists in the img folder.");
        }

        /*---------------------------------------------------------------------------------------------------- */
        ResultSet rs2 = dbOperation.GetSpecialData(query);
        int totalmois []=getTotalmois(rs2);
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        
        barDataset.addValue(totalmois [0], "Damege", "01");
        barDataset.addValue(totalmois [1], "Damege", "02");
        barDataset.addValue(totalmois [2], "Damege", "03");
        barDataset.addValue(totalmois [3], "Damege", "04");
        barDataset.addValue(totalmois [4], "Damege", "05");
        barDataset.addValue(totalmois [5], "Damege", "06");
        barDataset.addValue(totalmois [6], "Damege", "07");
        barDataset.addValue(totalmois [7], "Damege", "08");
        barDataset.addValue(totalmois [8], "Damege", "09");
        barDataset.addValue(totalmois [9], "Damege", "10");
        barDataset.addValue(totalmois [10], "Damege", "11");
        barDataset.addValue(totalmois [11], "Damege", "12");
        
        JFreeChart barChart = ChartFactory.createBarChart("revenue by type", "Type", "Revenue", barDataset);
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBorder(border);
        
        

        /*---------------------------------------------------------------------------------------------------- */
        JPanel UtilizationRatePanel = new JPanel();
        JLabel titeltopUse = new LabelStyle1("Revenue by year of vehicle");

        UtilizationRatePanel.setLayout(new GridLayout(6, 1, 20, 10));
        String query2 = "SELECT v.annee AS vehicle_year, \r\n" + //
                        "       SUM(p.montant) AS total_revenue \r\n" + //
                        "FROM paiement p \r\n" + //
                        "JOIN reservation r ON p.id_reservation = r.id_reservation \r\n" + //
                        "JOIN vehicule v ON r.id_vehicule = v.id_vehicule \r\n" + //
                        "GROUP BY v.annee \r\n" + //
                        "ORDER BY total_revenue DESC \r\n" + //
                        "LIMIT 5;\r\n" + //
                        "";
        ResultSet rs3 = dbOperation.GetSpecialData(query2);

        UtilizationRatePanel.add(titeltopUse);
        UtilizationRatePanel.setBorder(border);

        try {
            while (rs3.next()) {
                UtilizationRatePanel.add(Printpanels(
                   "vehicle year: " + rs3.getString("vehicle_year"), 
                    rs3.getDouble("total_revenue") // Use getDouble for numerical values
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // For debugging
            System.out.println("Error processing the ResultSet.");
        }
        
        /*---------------------------------------------------------------------------------------------------- */
        JPanel MaintenanceFrequencyPanel = new JPanel();
        query2 = "SELECT DAYNAME(p.date_paiement) AS day_of_week, SUM(p.montant) AS revenue_by_day FROM paiement p GROUP BY DAYNAME(p.date_paiement) ORDER BY FIELD(DAYNAME(p.date_paiement), 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');";
        rs3 = dbOperation.GetSpecialData(query2);
        JLabel titelstat =new LabelStyle1("methode of paiment ");
        
        MaintenanceFrequencyPanel.setLayout(new GridLayout(8,1,20,10));
        
        

        MaintenanceFrequencyPanel.add(titelstat);
        MaintenanceFrequencyPanel.setBorder(border);
        try {
            while (rs3.next()) {
                MaintenanceFrequencyPanel.add(Printpanels(
                   "vehicle age : "+ rs3.getString("day_of_week"), 
                    Double.parseDouble(rs3.getString("revenue_by_day"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            System.out.println("Error processing the ResultSet.");
        }
        
        
        /*---------------------------------------------------------------------------------------------------- */
        
        
        centerPanel.setLayout(new GridLayout(2, 3, 10, 10));

        // Bar Chart for Most Rented Vehicles
        ResultSet rs=dbOperation.GetSpecialData("SELECT r.id_reservation, p.montant, v.id_vehicule, v.type, v.marque FROM paiement p JOIN reservation r ON p.id_reservation = r.id_reservation JOIN vehicule v ON r.id_vehicule = v.id_vehicule ORDER BY r.id_reservation ASC;");
        int totalSUV=dbOperation.getTotalpaiment(rs,"SUV");
        DefaultCategoryDataset barDataset1 = new DefaultCategoryDataset();
        rs=dbOperation.GetSpecialData("SELECT r.id_reservation, p.montant, v.id_vehicule, v.type, v.marque FROM paiement p JOIN reservation r ON p.id_reservation = r.id_reservation JOIN vehicule v ON r.id_vehicule = v.id_vehicule ORDER BY r.id_reservation ASC;");
        int totalBerline=dbOperation.getTotalpaiment(rs,"Berline");
        barDataset1.addValue(totalSUV, "Damege", "SUV");
        barDataset1.addValue(totalBerline, "Damege", "Berline");
        
        JFreeChart barChart1 = ChartFactory.createBarChart("revenue by type", "Type", "Revenue", barDataset1);
        ChartPanel barChartPanel1 = new ChartPanel(barChart1);
        barChartPanel1.setBorder(border);
         /*---------------------------------------------------------------------------------------------------- */
        // Pie Chart for Vehicle Utilization
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        rs = dbOperation.GetSpecialData("SELECT v.type AS car_type, COALESCE(COUNT(CASE WHEN r.etat_retour = 'maintenance' THEN 1 END), 0) AS count_damaged FROM vehicule v LEFT JOIN reservation res ON v.id_vehicule = res.id_vehicule LEFT JOIN retour r ON res.id_reservation = r.id_reservation GROUP BY v.type ORDER BY v.type;");
        MaintenanceFrequencyPanel.setLayout(new GridLayout(8, 1, 20, 10));
        try {
            while (rs.next()) {
                pieDataset.setValue(rs.getString("car_type"), Double.parseDouble(rs.getString("count_damaged")));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            System.out.println("Error processing the ResultSet.");
        }
        
        JFreeChart pieChart = ChartFactory.createPieChart("Damaged Cars per Type", pieDataset, true, true, false);
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setBorder(border);
       
        centerPanel.add(imegePanel);
        centerPanel.add(barChartPanel1);
        centerPanel.add(UtilizationRatePanel);
        centerPanel.add(barChartPanel);
        centerPanel.add(MaintenanceFrequencyPanel);
        centerPanel.add(pieChartPanel);
        // Pie Chart for Vehicle Utilization
        
        
        add(centerPanel, BorderLayout.CENTER);
        return centerPanel;
    }
    public JPanel tabelStatistiquesRevenue(){
        // South Panel (Rental History Table)
     
            DbOperation dbOperation = new DbOperation();
            JPanel southPanel = new JPanel();
            southPanel.setLayout(new BorderLayout());
        
            // Define the column names for the table
            String[] columnNames = {"Month", "Total Rent Value"};
        
            // Initialize an empty data array
            Object[][] data = new Object[0][columnNames.length];
        
            try {
                // Get the current year dynamically
                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        
                // Query to get the month and total rent value for the current year
                String query = "SELECT DATE_FORMAT(p.date_paiement, '%Y-%m') AS month, SUM(p.montant) AS total_rent_value " +
                               "FROM paiement p " +
                               "WHERE YEAR(p.date_paiement) = " + currentYear + " " +
                               "GROUP BY DATE_FORMAT(p.date_paiement, '%Y-%m') " +
                               "ORDER BY DATE_FORMAT(p.date_paiement, '%Y-%m') ASC";
        
                ResultSet rs = dbOperation.GetSpecialData(query);
        
                // Count the number of rows in the result set
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                }
        
                // Initialize the data array with the appropriate number of rows
                data = new Object[rowCount][columnNames.length];
        
                // Reset the ResultSet and populate the data array
                rs = dbOperation.GetSpecialData(query);
        
                int i = 0;
                while (rs.next()) {
                    data[i][0] = rs.getString("month");               // Month
                    data[i][1] = rs.getDouble("total_rent_value");    // Total rent value
                    i++;
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQL exception
            }
        
            // Create a table with the data and column names
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            JTable monthlyStatsTable = new JTable(tableModel);
        
            // Add the table to a scroll pane
            JScrollPane tableScrollPane = new JScrollPane(monthlyStatsTable);
        
            // Add the table to the south panel
            southPanel.add(tableScrollPane, BorderLayout.CENTER);
            southPanel.setPreferredSize(new Dimension(800, 150)); // Set preferred size for the panel
        
            // Return the panel with the table
            return southPanel;
        }
        
    }

