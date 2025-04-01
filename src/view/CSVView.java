package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CSVView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton showButton;

    private static final String[] COLUMN_NAMES = {
            "First Name", "Second Name", "Date of Birth", "INN", "Phone Number", "Bank Account", "Balance"
    };

    public CSVView() {
        super("CSV Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null);

        // Создаём таблицу
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(tableModel);
        
        showButton = new JButton("Show Clients");

        JPanel topPanel = new JPanel();
        topPanel.add(showButton);

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void addShowButtonListener(ActionListener listener) {
        showButton.addActionListener(listener);
    }

    public void updateTable(String[][] data) {
        tableModel.setRowCount(0);
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }
}
