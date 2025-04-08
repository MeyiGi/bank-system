import javax.swing.*;

import view.MainView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and show the main window
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}