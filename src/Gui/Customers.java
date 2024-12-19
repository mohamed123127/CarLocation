package Gui;

import Entities_CRUD.User_CRUD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Customers extends JFrame{
      //add the methode who you will use it 
      public void actionPerformed(ActionEvent e) {
        User_CRUD.execute();
    }
    public Customers(){
        JButton button = new JButton("Submit");
        button.setBounds(100, 80, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customers.this.actionPerformed(e);
            }
        });
        add(button);

    }
    public static void main(String[] args) {
        new Customers();
    }
    
}