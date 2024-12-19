package CustomControle;

import java.awt.*;
import javax.swing.*;

public class LabelStyle1 extends JLabel {
    public LabelStyle1(String text) {
        super(text); // النص الأساسي للتسمية

        // تخصيص النص والخلفية
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(new Color(0, 102, 204)); // اللون الأزرق
       // setBackground(new Color(245, 245, 245)); // خلفية فاتحة
        setOpaque(true); // لجعل الخلفية مرئية
        setPreferredSize(new Dimension(150, 30));
        setHorizontalAlignment(SwingConstants.LEFT); // محاذاة النص إلى اليسار
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // إضافة مسافة داخلية
    }

    public LabelStyle1(String text,Color color) {
        super(text);
        setForeground(color);
    }
    public LabelStyle1(String text,int Width,int Height) {
        super(text); // النص الأساسي للتسمية
        setPreferredSize(new Dimension(Width, Height));
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(new Color(0, 102, 204)); // اللون الأزرق
       // setBackground(new Color(245, 245, 245)); // خلفية فاتحة
        setOpaque(true); // لجعل الخلفية مرئية
        
        setHorizontalAlignment(SwingConstants.LEFT); // محاذاة النص إلى اليسار
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // إضافة مسافة داخلية
    
    }
}
