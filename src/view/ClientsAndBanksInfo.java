package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import components.*;
import services.TransferByPhoneNumber;
import database.CSVBanksReader;
import database.CSVClientRepository;
import database.CSVClientsReader;
import services.MoneyTransfer;
import services.TransferByAccountNumber;
import model.Client;
import model.Bank;

public class ClientsAndBanksInfo {

    public ClientsAndBanksInfo() {
        // Общие столбцы (будут меняться в зависимости от выбора)
        JFrame frame = new JFrame("Clients and Banks Info");
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Select what to show:");
        JButton showClientsButton = new JButton("Client Info");
        JButton showBanksButton = new JButton("Bank Info");
        JButton makeTransactionButton = new JButton("Make Transaction");

        // Таблица с изначально пустыми столбцами
        Table table = new Table(new String[]{});

        // Создание репозитория клиентов
        CSVClientRepository clientRepository = new CSVClientRepository("data/clients.csv");
        
        // Создание объектов для перевода
        MoneyTransfer transferByPhone = new TransferByPhoneNumber(clientRepository);
        MoneyTransfer transferByAccount = new TransferByAccountNumber(clientRepository);

        // Обработчик: показать клиентов
        showClientsButton.addActionListener(e -> {
            String[] clientColumns = {"First Name", "Last Name", "Date of Birth", "INN", "Phone", "Bank Account", "Balance"};
            table.setColumns(clientColumns); // Обновляем заголовки
            table.getModel().setRowCount(0); // Очищаем таблицу

            // Получаем список клиентов
            List<Client> clientList = CSVClientsReader.read("data/clients.csv");
            for (Client item : clientList) {
                table.getModel().addRow(item.toArray());
            }
        });

        // Обработчик: показать банки
        showBanksButton.addActionListener(e -> {
            String[] bankColumns = {"Name", "Code", "Address", "Balance"};
            table.setColumns(bankColumns); // Обновляем заголовки
            table.getModel().setRowCount(0); // Очищаем таблицу

            // Получаем список банков
            List<Bank> bankList = CSVBanksReader.read("data/banks.csv");
            for (Bank item : bankList) {
                table.getModel().addRow(item.toArray());
            }
        });

        // Обработчик: для выполнения транзакции
        makeTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создаем окно для выбора типа перевода (по телефону или по номеру счета)
                String[] options = {"Phone Number", "Account Number"};
                JComboBox<String> methodComboBox = new JComboBox<>(options);

                JPanel panelForTransaction = new JPanel();
                panelForTransaction.add(new JLabel("Select Transfer Method:"));
                panelForTransaction.add(methodComboBox);

                // Создание формы для ввода данных перевода
                JTextField senderField = new JTextField(10);
                JTextField recipientField = new JTextField(10);
                JTextField amountField = new JTextField(10);

                panelForTransaction.add(new JLabel("Sender:"));
                panelForTransaction.add(senderField);
                panelForTransaction.add(new JLabel("Recipient:"));
                panelForTransaction.add(recipientField);
                panelForTransaction.add(new JLabel("Amount:"));
                panelForTransaction.add(amountField);

                // Ожидаем выбор пользователя
                int option = JOptionPane.showConfirmDialog(frame, panelForTransaction, 
                        "Enter Transaction Details", JOptionPane.OK_CANCEL_OPTION);

                // Если пользователь нажал ОК
                if (option == JOptionPane.OK_OPTION) {
                    String sender = senderField.getText();
                    String recipient = recipientField.getText();
                    int amount = Integer.parseInt(amountField.getText());
                    MoneyTransfer selectedTransfer = null;

                    // Выбираем метод перевода в зависимости от выбора пользователя
                    if (methodComboBox.getSelectedItem().equals("Phone Number")) {
                        selectedTransfer = transferByPhone;
                    } else if (methodComboBox.getSelectedItem().equals("Account Number")) {
                        selectedTransfer = transferByAccount;
                    }

                    if (selectedTransfer != null) {
                        // Выполняем перевод
                        selectedTransfer.pay(sender, recipient, amount);
                    }
                }
            }
        });

        // Добавляем кнопки в нижнюю панель
        panel.add(showClientsButton);
        panel.add(showBanksButton);
        panel.add(makeTransactionButton);

        // Размещение элементов на фрейме
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(table.getScrollPane(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
