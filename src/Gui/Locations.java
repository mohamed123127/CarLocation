package Gui;

import CustomControle.*;
import Entities_CRUD.Locations_CRUD;
import components.DataGridView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.*;


public class Locations extends JFrame {

    private JLabel selectedClientLabel; // لعرض اسم العميل المختار
    public DataGridView DataGridView;
    public JPanel MainPanel;

    public Locations() {
        //setTitle("Ajouter une réservation");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(1000, 500);
        //setLocationRelativeTo(null);
        
        this.setBackground(new Color(245, 245, 245));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        TextFieldStyle1 searchTextField = new TextFieldStyle1();
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DataGridView.filterTable(searchTextField.getText());
            }
        });
        ButtonStyle1 addButton = new ButtonStyle1("Ajouter ");
        addButton.addActionListener(e -> {
        
             new AddLocations(this);
            
        });
        ButtonStyle1 deleteButton= new ButtonStyle1("supprimer");
        deleteButton.addActionListener(e -> {
            var selectedRow = DataGridView.getSelectedRowData();

            if (selectedRow != null) {
               
                String idReservation = String.valueOf(selectedRow[0]); 
        
                int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cette réservation ?",  // Message de confirmation en français
                    "Confirmation de suppression",  // Titre de la boîte de dialogue en français
                    JOptionPane.YES_NO_OPTION  // Options Oui et Non
                );
        
                if (confirmation == JOptionPane.YES_OPTION) {
                    
                    String condition = "id_reservation = " + idReservation;
                    boolean result = Locations_CRUD.Delete("reservation", condition);
        
                    if (result) {
                    
                        JOptionPane.showMessageDialog(this, "Suppression réussie !");
                      
                      ResultSet data = Locations_CRUD.GetAll();
             
                      DataGridView.SetDataSource(data);
                    } else {
                        JOptionPane.showMessageDialog(this, "La réservation n'a pas été supprimée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée pour la suppression !", "Erreur", JOptionPane.WARNING_MESSAGE);
          if(selectedRow != null){
                CarDamageEvaluation instance = new CarDamageEvaluation(selectedRow,this);
                instance.setVisible(true);
                JOptionPane.showMessageDialog(null, "s");
            }else{
                JOptionPane.showMessageDialog(null, "Not selected row");

            }
        }});
        ButtonStyle1 returnCarButton = new ButtonStyle1("Restituer la voiture");
        returnCarButton.setPreferredSize(new Dimension(150,30));
        returnCarButton.addActionListener(e->{
            var selectedRow = DataGridView.getSelectedRowData();
            if(selectedRow != null){
                CarDamageEvaluation instance = new CarDamageEvaluation(selectedRow,this);
                instance.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée");
            }            
        });

     
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new LabelStyle1("Rechercher:",110,30));
        topPanel.add(searchTextField);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(returnCarButton);
       
        mainPanel.add(topPanel, BorderLayout.NORTH);

    
        ResultSet data = Locations_CRUD.GetAll(); 
        String[] columnNames = {"id_reservation", "client", "voiture", "statut", "montant_total", "date_debut", "date_fin", "clientId", "carId"};
        DataGridView = new DataGridView(columnNames, data);
        
       

        mainPanel.add(DataGridView, BorderLayout.CENTER);

        //setVisible(true);
        MainPanel = mainPanel;

    }

    public JPanel getMainPanel(){
        return MainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Locations());
    }
}

