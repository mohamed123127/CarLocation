package Gui;

import CustomControle.ButtonStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Customers_CRUD;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;

public class AddClient extends JFrame {
    private JButton submitButton;
    private JButton cancelButton;
    private String sex;

    public AddClient(Client instance) {
        this.setTitle("Ajouter un Client");
        setSize(600, 500);
        setLocationRelativeTo(null);
        this.setBackground(new Color(245, 245, 245));

        

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        // Add fields
        LabelStyle1 nomLabel = new LabelStyle1("Nom:");
        TextFieldStyle1 nomField = new TextFieldStyle1();
        addComponent(mainPanel, nomLabel, nomField, gbc, 0);

        LabelStyle1 prenomLabel = new LabelStyle1("Prénom:");
        TextFieldStyle1 prenomField = new TextFieldStyle1();
        addComponent(mainPanel, prenomLabel, prenomField, gbc, 1);

        LabelStyle1 emailLabel = new LabelStyle1("Email:");
        TextFieldStyle1 emailField = new TextFieldStyle1();
        addComponent(mainPanel, emailLabel, emailField, gbc, 2);

        LabelStyle1 telephoneLabel = new LabelStyle1("Téléphone:");
        TextFieldStyle1 telephoneField = new TextFieldStyle1();
        addComponent(mainPanel, telephoneLabel, telephoneField, gbc, 3);

        LabelStyle1 permisLabel = new LabelStyle1("Numéro de Permis:");
        TextFieldStyle1 permisField = new TextFieldStyle1();
        addComponent(mainPanel, permisLabel, permisField, gbc, 4);

        // Add radio buttons for sex selection
        LabelStyle1 sexLabel = new LabelStyle1("Sexe:");
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton maleButton = new JRadioButton("Homme");
        JRadioButton femaleButton = new JRadioButton("Femme");

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleButton);
        sexGroup.add(femaleButton);

        sexPanel.add(maleButton);
        sexPanel.add(femaleButton);

        addComponent(mainPanel, sexLabel, sexPanel, gbc, 5);

        // Add buttons
        submitButton = new ButtonStyle1("Add");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(submitButton, gbc);

        cancelButton = new ButtonStyle1("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(cancelButton, gbc);

        // Handle submit button click
        submitButton.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String telephone = telephoneField.getText().trim();
            String numeroPermis = permisField.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || numeroPermis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Determine the selected sex
            if (maleButton.isSelected()) {
                sex = "Men";
            } else if (femaleButton.isSelected()) {
                sex = "Women";
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner le sexe.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String columns1 = "nom, prenom, telephone, email, numero_permis, sex";
            String values = "'" + nom + "', '" + prenom + "', '" + telephone + "', '" + email + "', '" + numeroPermis + "', '" + sex + "'";

            boolean result = Customers_CRUD.Insert(columns1, values);

            if (result) {
                JOptionPane.showMessageDialog(null, "Le client a été ajouté avec succès !");
                ResultSet data = Customers_CRUD.GetData();
                instance.DataGridView.SetDataSource(data);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de l'ajout du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Handle cancel button click
        cancelButton.addActionListener(e -> dispose());

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public AddClient(Client instance,Object[] selectedRow) {
        this.setTitle("Ajouter un Client");
        setSize(600, 450);
        setLocationRelativeTo(null);
        this.setBackground(new Color(245, 245, 245));

        

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        // Add fields
        LabelStyle1 nomLabel = new LabelStyle1("Nom:");
        TextFieldStyle1 nomField = new TextFieldStyle1();
        addComponent(mainPanel, nomLabel, nomField, gbc, 0);

        LabelStyle1 prenomLabel = new LabelStyle1("Prénom:");
        TextFieldStyle1 prenomField = new TextFieldStyle1();
        addComponent(mainPanel, prenomLabel, prenomField, gbc, 1);

        LabelStyle1 emailLabel = new LabelStyle1("Email:");
        TextFieldStyle1 emailField = new TextFieldStyle1();
        addComponent(mainPanel, emailLabel, emailField, gbc, 2);

        LabelStyle1 telephoneLabel = new LabelStyle1("Téléphone:");
        TextFieldStyle1 telephoneField = new TextFieldStyle1();
        addComponent(mainPanel, telephoneLabel, telephoneField, gbc, 3);

        LabelStyle1 permisLabel = new LabelStyle1("Numéro de Permis:");
        TextFieldStyle1 permisField = new TextFieldStyle1();
        addComponent(mainPanel, permisLabel, permisField, gbc, 4);

        

        nomField.setText(selectedRow[1].toString());
        prenomField.setText(selectedRow[2].toString());
        emailField.setText(selectedRow[4].toString());
        telephoneField.setText(selectedRow[3].toString());
        permisField.setText(selectedRow[5].toString());



        // Add buttons
        submitButton = new ButtonStyle1("Modify");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(submitButton, gbc);

        cancelButton = new ButtonStyle1("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(cancelButton, gbc);

        // Handle submit button click
        submitButton.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String telephone = telephoneField.getText().trim();
            String numeroPermis = permisField.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || numeroPermis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

          

           Customers_CRUD.Update(Integer.parseInt(selectedRow[0].toString()), nom,prenom, telephone, email,numeroPermis,sex);
                ResultSet data = Customers_CRUD.GetData();
                if(instance != null){
                    instance.DataGridView.SetDataSource(data);
                }else{
                    JOptionPane.showMessageDialog(null, "null");
                }
                JOptionPane.showMessageDialog(this, "Client modifier avec succès!", "Succès",JOptionPane.INFORMATION_MESSAGE);
                dispose();
        });

        // Handle cancel button click
        cancelButton.addActionListener(e -> dispose());

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panel.add(component, gbc);
    }
}
