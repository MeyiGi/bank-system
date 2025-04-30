package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.CSVClientRepository;
import services.transfer.MoneyTransfer;
import services.transfer.TransferByAccountNumber;
import services.transfer.TransferByInnNumber;
import services.transfer.TransferByPhoneNumber;
import services.fee.AccountFeeCalculator;
import services.fee.InnFeeCalculator;
import services.fee.PhoneFeeCalculator;
import services.fee.FeeCalculator;

import java.awt.*;

public class TransactionView extends JDialog {
    private static final String PHONE_NUMBER_METHOD = "Phone Number";
    private static final String ACCOUNT_NUMBER_METHOD = "Account Number";
    private static final String INN_NUMBER_METHOD    = "Inn Number";

    private final JComboBox<String> methodComboBox;
    private final JTextField senderField;
    private final JTextField recipientField;
    private final JTextField amountField;
    private boolean confirmed;
    private final CSVClientRepository clientRepository;

    public TransactionView(JFrame parent, CSVClientRepository clientRepository) {
        super(parent, "Make Transaction", true);
        this.clientRepository = clientRepository;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Transaction Details", TitledBorder.LEFT, TitledBorder.TOP));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        methodComboBox = new JComboBox<>(new String[]{
            PHONE_NUMBER_METHOD,
            ACCOUNT_NUMBER_METHOD,
            INN_NUMBER_METHOD
        });
        senderField    = new JTextField(15);
        recipientField = new JTextField(15);
        amountField    = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Method:"), gbc);
        gbc.gridx = 1;
        formPanel.add(methodComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Sender:"), gbc);
        gbc.gridx = 1;
        formPanel.add(senderField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Recipient:"), gbc);
        gbc.gridx = 1;
        formPanel.add(recipientField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        formPanel.add(amountField, gbc);

        JPanel buttonPanel = new JPanel();
        JButton okButton     = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);

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
        String method = (String) methodComboBox.getSelectedItem();
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid amount: " + amountField.getText());
        }

        MoneyTransfer strategy = createTransferStrategy(method);

        return new TransactionData(
            senderField.getText(),
            recipientField.getText(),
            amount,
            strategy
        );
    }

    private MoneyTransfer createTransferStrategy(String method) {
        FeeCalculator feeCalc;
        switch (method) {
            case PHONE_NUMBER_METHOD:
                feeCalc = new PhoneFeeCalculator(1.5); // пример 1.5%
                return new TransferByPhoneNumber(clientRepository, feeCalc);
            case ACCOUNT_NUMBER_METHOD:
                feeCalc = new AccountFeeCalculator(100); // пример фикс.100
                return new TransferByAccountNumber(clientRepository, feeCalc);
            case INN_NUMBER_METHOD:
                feeCalc = new InnFeeCalculator(50); // пример мин.50
                return new TransferByInnNumber(clientRepository, feeCalc);
            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public static class TransactionData {
        public final String sender;
        public final String recipient;
        public final int amount;
        public final MoneyTransfer strategy;

        public TransactionData(String sender, String recipient, int amount, MoneyTransfer strategy) {
            this.sender   = sender;
            this.recipient= recipient;
            this.amount   = amount;
            this.strategy = strategy;
        }
    }
}
