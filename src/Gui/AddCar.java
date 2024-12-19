package Gui;

import CustomControle.ButtonStyle1;
import CustomControle.ComboBoxStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Car_CRUD;
import Helpers.DbOperation;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;

public class AddCar extends JFrame {
    public boolean isAddOperation = false;
    private JButton submitButton;

    public AddCar(Car instance) {
        this.setTitle("Console de ajouter des voitures");
        this.setSize(500, 400);
        this.setBackground(new Color(245, 245, 245));

        //UIManager.put("Panel.background", new Color(245, 245, 245));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);


        JLabel titleLabel = new JLabel("Ajouter un Nouveau Véhicule", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 63, 65));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10); // Espacement supplémentaire pour le titre
        mainPanel.add(titleLabel, gbc);

        // Réinitialisation des contraintes pour les champs suivants
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10); // Espacement réduit
        gbc.anchor = GridBagConstraints.CENTER;

        

        // Use the custom LabelStyle1 and TextFieldStyle1
        LabelStyle1 marqueLabel = new LabelStyle1("Marque:",110,30);
        TextFieldStyle1 marqueField = new TextFieldStyle1();
        addComponent(mainPanel, marqueLabel, marqueField, gbc, 1);

        LabelStyle1 modeleLabel = new LabelStyle1("Modèle:",110,30);
        TextFieldStyle1 modeleField = new TextFieldStyle1();
        addComponent(mainPanel, modeleLabel, modeleField, gbc, 2);

        LabelStyle1 anneeLabel = new LabelStyle1("Année:",110,30);
        TextFieldStyle1 anneeField = new TextFieldStyle1();
        addComponent(mainPanel, anneeLabel, anneeField, gbc, 3);

        LabelStyle1 typeLabel = new LabelStyle1("Type:",110,30);
        String[] types = { "SUV", "voitures de luxe", "voitures de prestige", "voitures de prestige", "petites voitures" };
        ComboBoxStyle1 typeComboBox = new ComboBoxStyle1(types);
        addComponent(mainPanel, typeLabel, typeComboBox, gbc, 4);

        LabelStyle1 carburantLabel = new LabelStyle1("Carburant:",110,30);
        String[] carburants = { "Essence", "Gazole" };
        ComboBoxStyle1 carburantComboBox = new ComboBoxStyle1(carburants);
        addComponent(mainPanel, carburantLabel, carburantComboBox, gbc, 5);

        LabelStyle1 prixLabel = new LabelStyle1("Prix par jour:",110,30);
        TextFieldStyle1 prixField = new TextFieldStyle1();
        addComponent(mainPanel, prixLabel, prixField, gbc, 6);

        // Submit button
        submitButton = new ButtonStyle1("Add");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        // Database connection test
        DbOperation dbOp = new DbOperation();
        if (!dbOp.testConnection()) {
            JOptionPane.showMessageDialog(this, "Échec de la connexion à la base de données. Vérifiez la connexion.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        //if(isAddOperation){
            submitButton.addActionListener(e -> {
                String marque = marqueField.getText();
                String modele = modeleField.getText();
                String annee = anneeField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                String carburant = (String) carburantComboBox.getSelectedItem();
                String prix = prixField.getText();

                String tableName = "vehicule";
                String columns = "marque, modele, annee, type, carburant, prix_location_jour, etat";
                String values = "'" + marque + "', '" + modele + "', '" + annee + "', '" + type + "', '" + carburant
                        + "', '" + prix + "',' disponible'";

                if (dbOp.Insert(tableName, columns, values)) {
                    ResultSet data = Car_CRUD.GetAll();
                    if(instance != null){
                        instance.dataGridView.SetDataSource(data);
                    }else{
                        JOptionPane.showMessageDialog(null, "null");
                    }
                    JOptionPane.showMessageDialog(this, "Véhicule ajouté avec succès!", "Succès",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de l'ajout du véhicule.\nErreur: " + dbOp.getLastError(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });
        //}

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public AddCar(Car instance,Object[] selectedRow){
        this.setTitle("Console de ajouter des voitures");
        this.setSize(500, 400);
        this.setBackground(new Color(245, 245, 245));

        //UIManager.put("Panel.background", new Color(245, 245, 245));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);


        JLabel titleLabel = new JLabel("Ajouter un Nouveau Véhicule", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 63, 65));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10); // Espacement supplémentaire pour le titre
        mainPanel.add(titleLabel, gbc);

        // Réinitialisation des contraintes pour les champs suivants
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10); // Espacement réduit
        gbc.anchor = GridBagConstraints.CENTER;

        

        // Use the custom LabelStyle1 and TextFieldStyle1
        LabelStyle1 marqueLabel = new LabelStyle1("Marque:",110,30);
        TextFieldStyle1 marqueField = new TextFieldStyle1();
        addComponent(mainPanel, marqueLabel, marqueField, gbc, 1);
        
        LabelStyle1 modeleLabel = new LabelStyle1("Modèle:",110,30);
        TextFieldStyle1 modeleField = new TextFieldStyle1();
        addComponent(mainPanel, modeleLabel, modeleField, gbc, 2);

        LabelStyle1 anneeLabel = new LabelStyle1("Année:",110,30);
        TextFieldStyle1 anneeField = new TextFieldStyle1();
        addComponent(mainPanel, anneeLabel, anneeField, gbc, 3);

        LabelStyle1 typeLabel = new LabelStyle1("Type:",110,30);
        String[] types = { "SUV", "voitures de luxe", "voitures de prestige", "voitures de prestige", "petites voitures" };
        ComboBoxStyle1 typeComboBox = new ComboBoxStyle1(types);
        addComponent(mainPanel, typeLabel, typeComboBox, gbc, 4);

        LabelStyle1 carburantLabel = new LabelStyle1("Carburant:",110,30);
        String[] carburants = { "Essence", "Gazole" };
        ComboBoxStyle1 carburantComboBox = new ComboBoxStyle1(carburants);
        addComponent(mainPanel, carburantLabel, carburantComboBox, gbc, 5);

        LabelStyle1 prixLabel = new LabelStyle1("Prix par jour:",110,30);
        TextFieldStyle1 prixField = new TextFieldStyle1();
        addComponent(mainPanel, prixLabel, prixField, gbc, 6);

        

        marqueField.setText(selectedRow[1].toString());
        modeleField.setText(selectedRow[2].toString());
        anneeField.setText(selectedRow[3].toString());
        typeComboBox.setSelectedItem(selectedRow[4].toString());
        carburantComboBox.setSelectedItem(selectedRow[5].toString());
        prixField.setText(selectedRow[6].toString());
        // Submit button
        submitButton = new ButtonStyle1("Edit");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, gbc);

        // Database connection test
        DbOperation dbOp = new DbOperation();
        if (!dbOp.testConnection()) {
            JOptionPane.showMessageDialog(this, "Échec de la connexion à la base de données. Vérifiez la connexion.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        //if(isAddOperation){
            submitButton.addActionListener(e -> {
                String marque = marqueField.getText();
                String modele = modeleField.getText();
                String annee = anneeField.getText();
                String type = (String) typeComboBox.getSelectedItem();
                String carburant = (String) carburantComboBox.getSelectedItem();
                String prix = prixField.getText();

                Car_CRUD.Update(Integer.parseInt(selectedRow[0].toString()), marque, modele, Integer.parseInt(annee), type, carburant,Integer.parseInt(prix));
                ResultSet data = Car_CRUD.GetAll();
                if(instance != null){
                    instance.dataGridView.SetDataSource(data);
                }else{
                    JOptionPane.showMessageDialog(null, "null");
                }
                JOptionPane.showMessageDialog(this, "Véhicule modifier avec succès!", "Succès",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            });
        //}

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
   /*public AddCar(boolean IsAddOperation) {
        this();
        isAddOperation = IsAddOperation;
        if (!isAddOperation) {
            submitButton.setText("Edit");
            
        }
    } */ 

    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }

    public static void main(String[] args) {
      
    }
}
