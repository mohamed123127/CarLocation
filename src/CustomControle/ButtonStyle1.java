package CustomControle;

import java.awt.*;
import javax.swing.*;

public class ButtonStyle1 extends JButton {
    public ButtonStyle1(String text) {
        super(text); // النص الأساسي للزر

        // تعطيل الأنماط الافتراضية
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // تعيين حجم مفضل للزر
        setPreferredSize(new Dimension(100, 30));
        setHorizontalAlignment(SwingConstants.CENTER);  // المحاذاة الأفقية
        setVerticalAlignment(SwingConstants.CENTER);    // المحاذاة الرأسية
        setBorder(null);
    }

    public ButtonStyle1(String text,Color color) {
        setBackground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // تخصيص مظهر الزر
        if (getModel().isPressed()) {
            g.setColor(new Color(70, 130, 180)); // اللون عند الضغط
        } else {
            g.setColor(new Color(0, 102, 204)); // اللون الأساسي
        }
     g.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);

        // رسم النص
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 4;
        g.drawString(getText(), x, y);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // تخصيص الحواف
        g.setColor(new Color(50, 90, 140));
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 0, 0);
    }
}

