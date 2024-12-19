package Gui;

import javax.swing.*;
import org.jdatepicker.impl.*;
import java.awt.*;
import java.util.Properties;
import Entities_CRUD.Locations_CRUD;
import Entities_CRUD.Payment_CRUD;
import Helpers.DateLabelFormatter;
import components.DataGridView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import CustomControle.*;

public class AddLocations extends JFrame {

    private LabelStyle1 selectedClientLabel;
    private DataGridView DataGridView;
    private TextFieldStyle1 montantField;  // This should be used to show the total amount

    private String selectedClientId = null;
    private String selectedCarId = null;
    private double montantParJour;

    public AddLocations(Locations instance) {
        setTitle("Add Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        this.setBackground(new Color(245, 245, 245));

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ButtonStyle1 selectClientButton = new ButtonStyle1("Select Client");
        selectedClientLabel = new LabelStyle1("Selected Client: None", 200, 30);
        selectClientButton.addActionListener(e -> {
            new ClientSelection(selectedClient -> {
                selectedClientId = String.valueOf(selectedClient[0]);
                selectedClientLabel.setText("Selected Client: " + selectedClient[1]);
            });
        });

        ButtonStyle1 selectCarButton = new ButtonStyle1("Select Car");
        LabelStyle1 selectedCarLabel = new LabelStyle1("Selected Car: None", 200, 30);

        selectCarButton.addActionListener(e -> {
            new CarSelection(selectedCarData -> {
                // اسم السيارة
                String selectedCarName = (String) selectedCarData[1];
                selectedCarLabel.setText("Selected Car: " + selectedCarName);
        
                // ID للسيارة
                selectedCarId = String.valueOf(selectedCarData[0]);
        
                montantParJour = (Integer) selectedCarData[6];
        
            });
        });
        

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        LabelStyle1 dateDebutLabel = new LabelStyle1("Start Date:");
        JDatePanelImpl dateDebutPanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl dateDebutPicker = new JDatePickerImpl(dateDebutPanel, new DateLabelFormatter());
        dateDebutPicker.setPreferredSize(new Dimension(150, 30));

        LabelStyle1 dateFinLabel = new LabelStyle1("End Date:");
        JDatePanelImpl dateFinPanel = new JDatePanelImpl(new UtilDateModel(), properties);
        JDatePickerImpl dateFinPicker = new JDatePickerImpl(dateFinPanel, new DateLabelFormatter());
        dateFinPicker.setPreferredSize(new Dimension(150, 30));

         

        LabelStyle1 modePaiementLabel = new LabelStyle1("Mode de Paiement:");
        ComboBoxStyle1 modePaiementComboBox = new ComboBoxStyle1(new String[]{"Carte Bancaire", "Espèces"});

        ButtonStyle1 addButton = new ButtonStyle1("Add");
        addButton.addActionListener(e -> {
            if (selectedClientId == null || selectedCarId == null) {
                JOptionPane.showMessageDialog(this, "Please select both client and car!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            java.util.Date startDate = (java.util.Date) dateDebutPicker.getModel().getValue();
            java.util.Date endDate = (java.util.Date) dateFinPicker.getModel().getValue();

            if (startDate == null || endDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysDifference = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
            if (daysDifference <= 0) {
                JOptionPane.showMessageDialog(this, "End date must be after start date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = sdf.format(startDate);
            String endDateString = sdf.format(endDate);

            String selectedPaymentMode = (String) modePaiementComboBox.getSelectedItem();

            if (selectedPaymentMode == null || selectedPaymentMode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a payment mode!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double montantTotal = daysDifference * montantParJour;
           // String montantTotalString = (int)montantTotal
            

            String columns1 = "id_client, id_vehicule, date_debut, date_fin, statut";
            String values = selectedClientId + ", " + selectedCarId + ", '" + startDateString + "', '" + endDateString + "', 'en cours'";

            boolean result = Locations_CRUD.Insert(columns1, values);

            if (result) {
               int reservationId = Locations_CRUD.GetLastReservationId();
                 Payment_CRUD.Add(reservationId, selectedPaymentMode ,montantTotal,selectedPaymentMode,startDateString);
              
                JOptionPane.showMessageDialog(this, "Location added successfully! Days: " + daysDifference + ", Total: " + montantTotal + " DA");
 
                selectedClientId = null;
                selectedCarId = null;
                selectedClientLabel.setText("Selected Client: None");
                selectedCarLabel.setText("Selected Car: None");
               
                dateDebutPicker.getModel().setValue(null);  
                dateFinPicker.getModel().setValue(null);  

                 dispose();
                ResultSet data = Locations_CRUD.GetAll();
                instance.DataGridView.SetDataSource(data);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add location!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        ButtonStyle1 cancelButton = new ButtonStyle1("Annuler");
        cancelButton.addActionListener(e -> {
            dispose();
        });

      
      

       

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(modePaiementLabel, gbc);

        gbc.gridx = 1;
        panel.add(modePaiementComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(addButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(cancelButton, gbc);
       

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(selectedClientLabel, gbc);

        gbc.gridx = 1;
        panel.add(selectClientButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(selectedCarLabel, gbc);

        gbc.gridx = 1;
        panel.add(selectCarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateDebutLabel, gbc);

        gbc.gridx = 1;
        panel.add(dateDebutPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dateFinLabel, gbc);

        gbc.gridx = 1;
        panel.add(dateFinPicker, gbc);

       
       

        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }
}
