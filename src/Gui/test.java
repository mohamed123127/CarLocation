package Gui;

import CustomControle.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class test {
    public static void main(String[] args) {
        // إنشاء نافذة (JFrame)
        JFrame frame = new JFrame("Car Rental Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // تخصيص خلفية نافذة التطبيق
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // خلفية فاتحة

        // إنشاء الزر المخصص
        ButtonStyle1 rentButton = new ButtonStyle1("Rent Car");
        ButtonStyle1 returnButton = new ButtonStyle1("Return Car");

        // إنشاء حقل النص المخصص
        TextFieldStyle1 carIdField = new TextFieldStyle1();
        TextFieldStyle1 customerNameField = new TextFieldStyle1(200,25);

        // إنشاء تسمية مخصصة
        LabelStyle1 carIdLabel = new LabelStyle1("Car ID:");
        LabelStyle1 customerNameLabel = new LabelStyle1("Customer Name:");

        LabelStyle1 SlectLabel = new LabelStyle1("Chouse:");
        String[] items = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
        ComboBoxStyle1 comboBox = new ComboBoxStyle1(items);
        // تخصيص Layout
        frame.setLayout(new FlowLayout());

        // إضافة العناصر إلى النافذة
        frame.add(carIdLabel);
        frame.add(carIdField);
        frame.add(customerNameLabel);
        frame.add(customerNameField);
        frame.add(SlectLabel);
        frame.add(comboBox);
        frame.add(rentButton);
        frame.add(returnButton);

        // إضافة حدث عند الضغط على الزر
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Car rented successfully!");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Car returned successfully!");
            }
        });

        // عرض النافذة
        frame.setVisible(true);
    }
}