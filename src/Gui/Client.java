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

public class Client extends JFrame {
    // تعريف DataGridView كمتغير على مستوى الفئة
    public DataGridView DataGridView;
    public JPanel MainPanel;

    public Client() {
  
        //setSize(1000, 500);
        this.setBackground(new Color(245, 245, 245));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

      
        LabelStyle1 headerLabel = new LabelStyle1("Gestion des clients", 300, 30);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

      
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

     
        addButton.addActionListener(e -> {
            new AddClient(this);  
        });

   
        topPanel.add(new LabelStyle1("Rechercher:",120,30));
        topPanel.add(searchField);
      
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        // إضافة اللوحة العلوية إلى اللوحة الرئيسية
        mainPanel.add(topPanel, BorderLayout.NORTH);

       
        String[] columnNames = { "ID", "Nom", "Prénom", "Email", "Rôle", "Téléphone" };
        ResultSet data = Customers_CRUD.GetData(); 

      
        DataGridView = new DataGridView(columnNames, data);
        mainPanel.add(DataGridView, BorderLayout.CENTER); 

        // إجراء عند الضغط على زر التعديل
        editButton.addActionListener(e -> {
            Object[] selectedRow = DataGridView.getSelectedRowData();
            if (selectedRow != null) {
                new AddClient(this, selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne pour modifier.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        });

        // إجراء عند الضغط على زر الحذف
        deleteButton.addActionListener(e -> {
            var selectedRow = DataGridView.getSelectedRowData();

            if (selectedRow != null) {
                String idClient = String.valueOf(selectedRow[0]);

                int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment supprimer cette réservation ?",  
                    "Confirmation de suppression",  
                    JOptionPane.YES_NO_OPTION  
                );

                if (confirmation == JOptionPane.YES_OPTION) {
                    String condition = "id_client  = " + idClient;
                    boolean result = User_CRUD.Delete("client", condition);

                    if (result) {
                        JOptionPane.showMessageDialog(this, "Suppression réussie !");
                        // تحديث البيانات في الجدول
                        ResultSet datan = Customers_CRUD.GetData();
                        DataGridView.SetDataSource(datan); // تحديث بيانات الجدول بعد الحذف
                    } else {
                        JOptionPane.showMessageDialog(this, "La réservation n'a pas été supprimée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée pour la suppression !", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        });

        // إجراءات البحث والتصفية

    
        MainPanel = mainPanel;

        // إعداد خصائص النافذة
        //this.setSize(800, 600);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        //this.setLocationRelativeTo(null);
        //this.setVisible(true);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

   private void performSearch(String searchText, String role) {
        ResultSet data = Customers_CRUD.GetFilteredData(searchText); // جلب البيانات المفلترة
        String[] columnNames = { "ID", "Nom", "Prénom", "Email", "Rôle", "Téléphone" };
        DataGridView dataGridView = new DataGridView(columnNames, data);

        // تحديث الجدول بالبيانات المفلترة
        this.getContentPane().removeAll();  // إزالة المكونات الموجودة
        this.add(dataGridView, BorderLayout.CENTER);  // إضافة DataGridView الجديدة
        this.revalidate();  // تحديث التخطيط
        this.repaint();  // إعادة رسم الإطار
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);  // إطلاق واجهة إدارة العملاء
    }
}
