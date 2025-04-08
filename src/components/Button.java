package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {
    public Button(String value) {
        this.setText(value);
        this.setBackground(new Color(70, 130, 180));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 40));
    }
}
