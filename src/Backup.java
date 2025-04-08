import model.Client;
import services.ORM.CSVClientsReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Backup {
    public static void main(String[] args) {
        // Create window
        JFrame frame = new JFrame("Client Data");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        // Create button to load client data
        JButton btnLoadClients = new JButton("Load Clients");
        btnLoadClients.setFont(new Font("Arial", Font.BOLD, 14));
        btnLoadClients.setBackground(new Color(70, 130, 180)); // Button color
        btnLoadClients.setForeground(Color.WHITE); // Button text color
        btnLoadClients.setPreferredSize(new Dimension(200, 40));
        panel.add(btnLoadClients);

        // Create title label
        JLabel titleLabel = new JLabel("Client Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setPreferredSize(new Dimension(800, 40));

        // Create table
        String[] columns = {"First Name", "Last Name", "Date of Birth", "INN", "Phone", "Bank Account", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the window
        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button click event handler
        btnLoadClients.addActionListener(e -> {
            // Static call to read method
            List<Client> clientsList = CSVClientsReader.read("data/clients.csv");

            // Clear existing data in the table
            model.setRowCount(0);

            // Add client data to the table
            for (Client client : clientsList) {
                model.addRow(client.toArray());
            }
        });

        // Display the window
        frame.setVisible(true);
    }
}
