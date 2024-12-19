package CustomControle;

import java.awt.*;
import javax.swing.*;

public class ComboBoxStyle1 extends JComboBox<String> {
    private CustomComboBoxUI customUI;

    public ComboBoxStyle1(DefaultComboBoxModel<String> items) {
        super(items);

        // تخصيص الخلفية
        setBackground(Color.white); // خلفية فاتحة
        setForeground(new Color(0, 0, 0)); // النص باللون الأسود
        setPreferredSize(new Dimension(200, 25));
        // تخصيص الخط
        setFont(new Font("Arial", Font.PLAIN, 16)); // خط بسيط

        // تخصيص الحد
        setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));

        // تخصيص ارتفاع كل عنصر
        setMaximumRowCount(5); // عدد العناصر المرئية في القائمة المنسدلة
    }

    public ComboBoxStyle1(String[] items) {
        super(items);

        // تخصيص الخلفية
        setBackground(Color.white); // خلفية فاتحة
        setForeground(new Color(0, 0, 0)); // النص باللون الأسود
        setPreferredSize(new Dimension(200, 25));
        // تخصيص الخط
        setFont(new Font("Arial", Font.PLAIN, 16)); // خط بسيط

        // تخصيص الحد
        setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));

        // تخصيص ارتفاع كل عنصر
        setMaximumRowCount(5); // عدد العناصر المرئية في القائمة المنسدلة
    }

    public ComboBoxStyle1(String[] items,int width,int height){
        super(items);
        setPreferredSize(new Dimension(width,height));
    }

    @Override
    public void setPopupVisible(boolean v) {
        super.setPopupVisible(v);

        // تخصيص واجهة الـ ComboBox
        setUI(new CustomComboBoxUI());
    }

    // تخصيص واجهة الـ ComboBox
    static class CustomComboBoxUI extends javax.swing.plaf.basic.BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton button = super.createArrowButton(); // استخدام زر السهم الافتراضي

            // تخصيص زر السهم
            button.setBackground(new Color(0, 102, 204));
            button.setForeground(Color.WHITE);

            return button;
        }
    }

    public static void main(String[] args) {
        // اختبار ComboBoxStyle1
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ComboBox");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            String[] items = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
            ComboBoxStyle1 comboBox = new ComboBoxStyle1(items);

            frame.add(comboBox);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}
