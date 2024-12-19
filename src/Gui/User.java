package Gui;

import CustomControle.ButtonStyle1;
import CustomControle.LabelStyle1;
import CustomControle.TextFieldStyle1;
import Entities_CRUD.Customers_CRUD;
import Entities_CRUD.User_CRUD;
import components.DataGridView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.*;

public class User extends JFrame {
    public DataGridView DataGridView;
    public JPanel MainPanel;

    public User() {
        //setSize(1000, 500);
        this.setBackground(new Color(245, 245, 245));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Header
        LabelStyle1 headerLabel = new LabelStyle1("Gestion des clients", 300, 30);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Top Panel (Search and Buttons)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        TextFieldStyle1 searchField = new TextFieldStyle1(200, 30);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DataGridView.filterTable(searchField.getText());
            }
        });
        ButtonStyle1 addButton = new ButtonStyle1("Ajouter");
        ButtonStyle1 editButton = new ButtonStyle1("Modifier");
        ButtonStyle1 deleteButton = new ButtonStyle1("Supprimer");

        topPanel.add(new LabelStyle1("Rechercher:",110,30));
        topPanel.add(searchField);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table Data
        String[] columnNames = { "ID", "Nom", "Prénom", "Email", "Rôle", "Téléphone" };
        ResultSet data = User_CRUD.GetData();
        DataGridView = new DataGridView(columnNames, data);
        mainPanel.add(DataGridView, BorderLayout.CENTER);

        // Add Button Action
        addButton.addActionListener(e -> new Addutilisateur(this));

        // Edit Button Action
       
        editButton.addActionListener(e->{
            Object[] selectedRow = DataGridView.getSelectedRowData();
            if (selectedRow != null) {
                new Addutilisateur(this,selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour modifier.", "Avertissement",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(e -> {
            Object[] selectedRow = DataGridView.getSelectedRowData();
            if (selectedRow != null) {
                String idUser = String.valueOf(selectedRow[0]);

                int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer ce client ?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmation == JOptionPane.YES_OPTION) {
                    String condition = "id = " + idUser;
                    boolean result = User_CRUD.Delete("utilisateur",condition);

                    if (result) {
                        JOptionPane.showMessageDialog(this, "Suppression réussie !");
                        DataGridView.SetDataSource(Customers_CRUD.GetData()); // Refresh the table
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée pour la suppression.", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Search Action
     

        MainPanel = mainPanel;

        //this.setSize(800, 600);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }


}
