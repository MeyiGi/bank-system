package view;
import java.awt.BorderLayout;
import java.util.List;

import components.*;
import model.Client;
import services.db.CSVClientsReader;

// import javax.swing.*;


public class ClientsInfo {
    public ClientsInfo() {
        String[] columns = {"First Name", "Last Name", "Date of Birth", "INN", "Phone", "Bank Account", "Balance"};
        Frame frame = new Frame();
        Panel panel = new Panel();
        Button loadClientsButton = new Button("Load Button");
        Label titleLabel = new Label("Client infomations");
        Table table = new Table(columns);

        loadClientsButton.addActionListener(e -> {
            List<Client> clientList = CSVClientsReader.read("data/clients.csv");

            table.getModel().setRowCount(0);
            for (Client item : clientList) {
                table.getModel().addRow(item.toArray());
            }
        });      

        panel.add(loadClientsButton);
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(table.getScrollPane(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
