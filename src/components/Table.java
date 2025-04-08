package components;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Table {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    public Table(String[] columns) {
        this.model = new DefaultTableModel(columns, 0);
        this.table = new JTable(model);
        table.setFont(new Font("Arial", Font.BOLD, 14));
        this.scrollPane = new JScrollPane(table);
    }

    // Getters
    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    // Setters
    public void setModel(DefaultTableModel model) {
        this.model = model;
        this.table.setModel(model); // sync model with table
    }

    public void setTable(JTable table) {
        this.table = table;
        this.scrollPane.setViewportView(table); // sync table with scroll pane
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    // ✅ Добавляем метод для смены колонок
    public void setColumns(String[] columns) {
        this.model = new DefaultTableModel(columns, 0);
        this.table.setModel(this.model);
    }
}