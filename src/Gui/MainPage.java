package Gui;

import CustomControle.MainHeaderItem;
import Helpers.ImageHelper;
import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame {
    private JPanel mainPanel;
    public MainPage(String Role) {
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(new Color(245, 245, 245));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainPanel = new JPanel();
        this.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout());
        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        TopPanel.setPreferredSize(new Dimension(200, 50));
        TopPanel.setBackground(new Color(98, 121, 255));
        this.add(TopPanel, BorderLayout.NORTH);
       
        ImageIcon carIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\car.png",25,25);
        JButton carButton = MainHeaderItem.getNewItem("Voiture", carIcon);
        carButton.addActionListener(e->{
            Car car = new Car();
            SetMainPage(car.getMainPanel());
        });
        ImageIcon CustomerIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Customer.png",25,25);
        JButton CustomerButton = MainHeaderItem.getNewItem("Client", CustomerIcon);
        CustomerButton.addActionListener(e->{
            Client client = new Client();
            SetMainPage(client.getMainPanel());
        });
        ImageIcon LocationIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\schedule.png",25,25);
        JButton LocationButton = MainHeaderItem.getNewItem("Reservation", LocationIcon);
        LocationButton.addActionListener(e->{
            Locations locations = new Locations();
            SetMainPage(locations.getMainPanel());
        });
        ImageIcon StatistiquesIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Statistiques.png",25,25);
        JButton StatistiquesButton = MainHeaderItem.getNewItem("Statistiques", StatistiquesIcon);
        StatistiquesButton.addActionListener(e->{
            Statistiques statistiques = new Statistiques();
            statistiques.setVisible(true);
        });
        ImageIcon AdminIcon = ImageHelper.convertIconColor("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\Resources\\Icons\\Admin.png",25,25);
        JButton AdminButton = MainHeaderItem.getNewItem("administration", AdminIcon);
        AdminButton.addActionListener(e->{
            User user = new User();
            SetMainPage(user.getMainPanel());
        });

        TopPanel.add(carButton);
        TopPanel.add(CustomerButton);
        TopPanel.add(LocationButton);
        if(Role.equals("administrateur")){
            TopPanel.add(AdminButton);
        }
        TopPanel.add(StatistiquesButton);
        carButton.doClick();

    }

    public void SetMainPage(JPanel Page){
        mainPanel.removeAll();
        if(Page!=null){
            Page.setPreferredSize(new Dimension(mainPanel.getSize().width,mainPanel.getSize().height));
            mainPanel.add(Page,BorderLayout.CENTER);
            mainPanel.revalidate(); // إعادة تهيئة المكونات
        mainPanel.repaint(); 
            //JOptionPane.showMessageDialog(null,Page);
        }else{
            JOptionPane.showMessageDialog(null,"nothings");
        }
    }

   /*  public static void main(String[] args) {
        MainPage main = new MainPage();
        main.setVisible(true);
        
    }*/
}
