package Gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.jdatepicker.impl.*;
import Helpers.DateLabelFormatter;

public class Payment extends JFrame {
    private JTextField montantField;
    private JComboBox<String> modePaiementComboBox;
    private JDatePickerImpl datePicker;
    private String idReservation;

    public Payment(String idReservation, double montantTotal) {
        this.idReservation = idReservation;

        setTitle("Payment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Montant (Read-Only Field)
        JLabel montantLabel = new JLabel("Montant Total:");
        montantField = new JTextField(String.valueOf(montantTotal));
        montantField.setEditable(false); // Make it read-only

        // Date Picker
        JLabel dateLabel = new JLabel("Date de Paiement:");
   

        // Mode de Paiement (Dropdown)
        JLabel modePaiementLabel = new JLabel("Mode de Paiement:");
        modePaiementComboBox = new JComboBox<>(new String[]{"Carte Bancaire", "Espèces", "Chèque"});

        // Payer Button
        JButton payerButton = new JButton("Confirmer le Paiement");
        //payerButton.addActionListener(e -> handlePayment());

        // Add Components to Frame
        add(montantLabel);
        add(montantField);
        add(dateLabel);
    
        add(modePaiementLabel);
        add(modePaiementComboBox);
        add(new JLabel()); // Empty space
        add(payerButton);

        setVisible(true);
    }}