package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TransactionView extends JDialog {
    private final JComboBox<String> methodComboBox;
    private final JTextField senderField;
    private final JTextField recipientField;
    private final JTextField amountField;
    private boolean confirmed;

    public TransactionView(JFrame parent) {
        super(parent, "Make Transaction", true);
        // Основная панель с отступами и заголовком
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Transaction Details", TitledBorder.LEFT, TitledBorder.TOP));

        // Панель формы с GridBagLayout для выравнивания компонентов
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        methodComboBox = new JComboBox<>(new String[]{"Phone Number", "Account Number"});
        senderField = new JTextField(15);
        recipientField = new JTextField(15);
        amountField = new JTextField(15);

        // Строим форму
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Method:"), gbc);
        gbc.gridx = 1;
        formPanel.add(methodComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Sender:"), gbc);
        gbc.gridx = 1;
        formPanel.add(senderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Recipient:"), gbc);
        gbc.gridx = 1;
        formPanel.add(recipientField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        formPanel.add(amountField, gbc);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Добавляем панели в основную панель
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Добавляем основную панель в диалог
        setContentPane(mainPanel);

        // Обработчики кнопок
        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public TransactionData getTransactionData() {
        return new TransactionData(
            (String) methodComboBox.getSelectedItem(),
            senderField.getText(),
            recipientField.getText(),
            Double.parseDouble(amountField.getText())
        );
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public static class TransactionData {
        public final String method;
        public final String sender;
        public final String recipient;
        public final double amount;

        public TransactionData(String method, String sender, String recipient, double amount) {
            this.method = method;
            this.sender = sender;
            this.recipient = recipient;
            this.amount = amount;
        }
    }
}
