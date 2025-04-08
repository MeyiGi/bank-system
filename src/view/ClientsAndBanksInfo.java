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
        
        // Создание объекта для перевода
        MoneyTransfer transfer = new TransferByPhoneNumber(clientRepository);

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
                // Создаем окно для ввода данных перевода
                JTextField senderPhoneField = new JTextField(10);
                JTextField recipientPhoneField = new JTextField(10);
                JTextField amountField = new JTextField(10);

                JPanel panelForTransaction = new JPanel();
                panelForTransaction.add(new JLabel("Sender Phone:"));
                panelForTransaction.add(senderPhoneField);
                panelForTransaction.add(new JLabel("Recipient Phone:"));
                panelForTransaction.add(recipientPhoneField);
                panelForTransaction.add(new JLabel("Amount:"));
                panelForTransaction.add(amountField);

                int option = JOptionPane.showConfirmDialog(frame, panelForTransaction, 
                        "Enter Transaction Details", JOptionPane.OK_CANCEL_OPTION);

                // Если пользователь нажал ОК
                if (option == JOptionPane.OK_OPTION) {
                    String senderPhone = senderPhoneField.getText();
                    String recipientPhone = recipientPhoneField.getText();
                    int amount = Integer.parseInt(amountField.getText());

                    // Выполняем транзакцию
                    transfer.pay(senderPhone, recipientPhone, amount);
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
