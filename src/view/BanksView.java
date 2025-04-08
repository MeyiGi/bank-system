package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Bank;

public class BanksView extends JPanel {
    private final DefaultTableModel model;
    private final JTable table;

    public BanksView() {
        // Устанавливаем менеджер компоновки
        setLayout(new BorderLayout(10, 10));
        // Добавляем заголовок панели
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Banks", TitledBorder.CENTER, TitledBorder.TOP));

        String[] columns = {"Name", "Code", "Address", "Balance"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Оборачиваем таблицу в JScrollPane и добавляем отступы
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable(List<Bank> banks) {
        model.setRowCount(0);
        for (Bank bank : banks) {
            model.addRow(bank.toArray());
        }
    }
}
