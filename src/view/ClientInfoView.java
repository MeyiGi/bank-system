package view;

import javax.swing.*;

import controller.MoneyTransferController;
import db.entity.Client;
import db.repo.ClientRepository;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

public class ClientInfoView extends JFrame {
    private List<Client> clients;
    private JLabel senderLabel;
    private JLabel recipientLabel;
    private JTextField amountField;
    private JButton transferButton;

    public ClientInfoView() {
        setTitle("Money Transfer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Получаем клиентов из репозитория
        clients = ClientRepository.getClients();

        // Инициализируем компоненты Swing
        senderLabel = new JLabel("Sender: " + clients.get(0).getName() + " (Balance: " + clients.get(0).getBalance() + ")");
        recipientLabel = new JLabel("Recipient: " + clients.get(1).getName() + " (Balance: " + clients.get(1).getBalance() + ")");
        amountField = new JTextField(10);
        transferButton = new JButton("Transfer Money");

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BigDecimal amount = new BigDecimal(amountField.getText());
                    // Вызываем контроллер для выполнения перевода
                    MoneyTransferController.performTransfer(amount, clients.get(0), clients.get(1));
                    // Обновляем отображение баланса
                    senderLabel.setText("Sender: " + clients.get(0).getName() + " (Balance: " + clients.get(0).getBalance() + ")");
                    recipientLabel.setText("Recipient: " + clients.get(1).getName() + " (Balance: " + clients.get(1).getBalance() + ")");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClientInfoView.this, "Введите корректную сумму", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Размещение компонентов в окне
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(senderLabel);
        panel.add(recipientLabel);
        panel.add(amountField);
        panel.add(transferButton);

        add(panel);
    }
}
