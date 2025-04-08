package components;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame() {
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}
