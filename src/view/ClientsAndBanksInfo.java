package view;

import java.awt.BorderLayout;
import java.util.List;

import components.*;
import model.Client;
import services.ORM.CSVBanksReader;
import services.ORM.CSVClientsReader;
import model.Bank;

public class ClientsAndBanksInfo {
    public ClientsAndBanksInfo() {
        // Общие столбцы (будут меняться в зависимости от выбора)
        Frame frame = new Frame();
        Panel panel = new Panel();
        Label titleLabel = new Label("Select what to show:");
        Button showClientsButton = new Button("Client Info");
        Button showBanksButton = new Button("Bank Info");

        // Таблица с изначально пустыми столбцами
        Table table = new Table(new String[]{});

        // Обработчик: показать клиентов
        showClientsButton.addActionListener(e -> {
            String[] clientColumns = {"First Name", "Last Name", "Date of Birth", "INN", "Phone", "Bank Account", "Balance"};
            table.setColumns(clientColumns); // Обновляем заголовки
            table.getModel().setRowCount(0); // Очищаем таблицу

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

            List<Bank> bankList = CSVBanksReader.read("data/banks.csv");
            for (Bank item : bankList) {
                table.getModel().addRow(item.toArray());
            }
        });

        // Добавляем кнопки в нижнюю панель
        panel.add(showClientsButton);
        panel.add(showBanksButton);

        // Размещение элементов на фрейме
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(table.getScrollPane(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
