package View;

import javax.swing.*;

public class Frame extends JFrame {
    JLabel label;

    public void Frame() {
        setSize(400, 200);
        setTitle("SELAMAT DATANG");
        setLayout(null);
        JLabel();
        setVisible(true);
    }

    private void JLabel() {
        label = new JLabel("SELAMAT MELIHAT - LIHAT");
        label.setBounds(117, 50, 300, 30);
        add(label);
    }
}
