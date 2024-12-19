package Gui;
import CustomControle.*; 
import Entities_CRUD.*;
import java.awt.*;
import javax.swing.*;

public class AdminLoginPage extends JFrame {
    private TextFieldStyle1 usernameField;
    private TextFieldStyle1 passwordField;
    String email = null;

    public AdminLoginPage() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450); 
        setLocationRelativeTo(null);
        setResizable(false);

      
        BackgroundPanel panel = new BackgroundPanel("C:\\Users\\DELL\\Desktop\\tous\\University\\L3\\S5\\IHM\\Projet\\Project-Code\\CarLocation\\src\\images\\background.jpg"); // ضع مسار الصورة هنا
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

       
        gbc.gridwidth = 1; 
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new LabelStyle1("Email:", 110, 30), gbc);

        usernameField = new TextFieldStyle1();
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new LabelStyle1("Mot de passe:", 110, 30), gbc);

        PasswordFieldStyle1 passwordField = new PasswordFieldStyle1();
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        
        ButtonStyle1 loginButton = new ButtonStyle1("Login");
        loginButton.addActionListener(e->{

            String email = usernameField.getText();
            String password = new String(passwordField.getPassword()); 
           if(email.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'email");
           }
           if(password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez entrer le mot de passe");
           }
           if(!email.isEmpty() && !password.isEmpty()){
            String Role = AdminLoginPage_CRUD.IsExsists(email, password);
            if(Role != null){
                MainPage mainPage = new MainPage(Role);
                mainPage.setVisible(true);
                dispose();
            }
            else    JOptionPane.showMessageDialog(null, "L'utilisateur n'existe pas");
        }

            

        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);
        add(panel);
        setVisible(true);
    }
 
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // رسم الصورة لتغطية اللوحة
            }
        }
    }
}