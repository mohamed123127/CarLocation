package CustomControle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainHeaderItem{
    public static JButton getNewItem(String buttonName,ImageIcon icon){
        JButton button = new JButton();
        button.setText(buttonName);
            button.setIcon(icon);  // Set both text and icon
            button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
            button.setForeground(Color.white);  // Set text color to white
            button.setBackground(new Color(98, 121, 255, 255));  // Set background color
            button.setBorder(null);
            button.setFont(new Font("Arial", Font.BOLD, 16));  // Set font and size
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Change cursor to hand
            button.setPreferredSize(new Dimension(140, 50));  // Width = width of the panel, height = 50px
            // إزالة تأثير الضغط على الزر
            button.setFocusPainted(false); // تعطيل إظهار التركيز
            button.setContentAreaFilled(false); // إلغاء ملء الخلفية أثناء الضغط
            button.setOpaque(true); // السماح بتلوين الخلفية

            // إزالة تأثير الضغط الافتراضي
            button.setBorderPainted(false); // تعطيل رسم الإطار عند الضغط
            // Add MouseListener to handle hover effect
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(70, 90, 180));  // Change background color on hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(98, 121, 255));  // Reset background color when hover ends
                }
            });
            return button;
    }
}
