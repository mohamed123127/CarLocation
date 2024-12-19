package Gui;

import CustomControle.ButtonStyle1;
import CustomControle.ComboBoxStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;

public class Addutilisateur extends JFrame {
    private JButton submitButton;
    private JButton cancelButton;

    public Addutilisateur(User instance) {
        this.setTitle("Ajouter un Utilisateur");
        setSize(450, 450);
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

        LabelStyle1 passwordLabel = new LabelStyle1("Mot de passe:");
        TextFieldStyle1 passwordField = new TextFieldStyle1();
        addComponent(mainPanel, passwordLabel, passwordField, gbc, 3);

        LabelStyle1 roleLabel = new LabelStyle1("Rôle:");
        ComboBoxStyle1 roleComboBox = new ComboBoxStyle1(new String[]{"Administrateur", "Gestionnaire"});
        addComponent(mainPanel, roleLabel, roleComboBox, gbc, 4);

        // Add radio buttons for sex selection
     

        

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
            String motDePasse = passwordField.getText().trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

         
         

            String columns = "nom, prenom, email, mot_de_passe, role";
            String values = "'" + nom + "', '" + prenom + "', '" + email + "', '" + motDePasse + "', '" + role + "'";

            boolean result = User_CRUD.Insert(columns, values);

            if (result) {
                JOptionPane.showMessageDialog(this, "L'utilisateur a été ajouté avec succès !");
                ResultSet data = User_CRUD.GetData();
                instance.DataGridView.SetDataSource(data);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de l'ajout de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Handle cancel button click
        cancelButton.addActionListener(e -> dispose());

        this.add(mainPanel);
        this.setVisible(true);
    }

    public Addutilisateur(User instance,Object[] selectedRow) {
        this.setTitle("Ajouter un Utilisateur");
        setSize(450, 450);
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

        LabelStyle1 passwordLabel = new LabelStyle1("Mot de passe:");
        TextFieldStyle1 passwordField = new TextFieldStyle1();
        addComponent(mainPanel, passwordLabel, passwordField, gbc, 3);

        LabelStyle1 roleLabel = new LabelStyle1("Rôle:");
        ComboBoxStyle1 roleComboBox = new ComboBoxStyle1(new String[]{"Administrateur", "Gestionnaire"});
        addComponent(mainPanel, roleLabel, roleComboBox, gbc, 4);

        nomField.setText(selectedRow[1].toString());
        prenomField.setText(selectedRow[2].toString());
        emailField.setText(selectedRow[3].toString());
        passwordField.setText(selectedRow[4].toString());
        roleComboBox.setSelectedItem(selectedRow[5].toString());

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
            String motDePasse = passwordField.getText().trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Attention", JOptionPane.WARNING_MESSAGE);
                return;
            }

            User_CRUD.Update(Integer.parseInt(selectedRow[0].toString()), nom,prenom, email, motDePasse,role);
                ResultSet data = User_CRUD.GetData();
                if(instance != null){
                    instance.DataGridView.SetDataSource(data);
                }else{
                    JOptionPane.showMessageDialog(null, "null");
                }
                JOptionPane.showMessageDialog(this, "utilisateur modifier avec succès!", "Succès",JOptionPane.INFORMATION_MESSAGE);
                dispose();
        });

        // Handle cancel button click
        cancelButton.addActionListener(e -> dispose());

        this.add(mainPanel);
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
