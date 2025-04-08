package components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {
    public Label(String value) {
        this.setText(value);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension(800, 40));
        this.setFont(new Font("Arial", Font.BOLD, 14));
    }
}
