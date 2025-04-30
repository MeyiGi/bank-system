import javax.swing.*;
import controller.BankController;
import view.MainView;
import view.ClientsView;
import view.BanksView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main view
            MainView mainView = new MainView();

            // Create other views
            ClientsView clientsView = new ClientsView();
            BanksView banksView = new BanksView();

            // Create the controller and set the views
            BankController controller = new BankController(mainView);
            controller.setViews(clientsView, banksView);

            // Show the main window
            mainView.setVisible(true);
        });
    }
}
