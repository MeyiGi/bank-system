package view;
import java.awt.BorderLayout;

import components.*;

// import javax.swing.*;


public class ClientsInfo {
    public ClientsInfo() {
        Frame frame = new Frame();
        Panel panel = new Panel();
        Button loadButton = new Button("Load Button");
        Label titleLabel = new Label("Client infomations");
        panel.add(loadButton);
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
    }
}
