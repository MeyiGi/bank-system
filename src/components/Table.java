package components;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Table {
    private DefaultTableModel model;
    private JTable table;

    public Table(String[] columns) {
        this.model = new DefaultTableModel(columns, 0);
        this.table = new JTable(model);
        table.setFont(new Font("Arial", Font.BOLD, 14));
        
    }
}
