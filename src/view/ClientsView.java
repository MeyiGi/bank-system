package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Client;

public class ClientsView extends JPanel {
    private final DefaultTableModel model;
    private final JTable table;

    public ClientsView() {
        // Устанавливаем менеджер компоновки
        setLayout(new BorderLayout(10, 10));
        // Добавляем заголовок панели
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Clients", TitledBorder.CENTER, TitledBorder.TOP));

        String[] columns = {"First Name", "Last Name", "Date of Birth", "INN", "Phone", "Bank Account", "Balance"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Оборачиваем таблицу в JScrollPane и добавляем отступы
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable(List<Client> clients) {
        model.setRowCount(0);
        for (Client client : clients) {
            model.addRow(client.toArray());
        }
    }
}
