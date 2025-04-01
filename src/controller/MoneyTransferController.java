package controller;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

import db.entity.Client;
import service.MoneyTransferService;

public class MoneyTransferController {
    // Контроллер, вызывающий сервис для перевода денег
    public static void performTransfer(BigDecimal amount, Client sender, Client recipient) {
        try {
            MoneyTransferService.transfer(amount, sender, recipient);
            JOptionPane.showMessageDialog(null, "Transfer successful!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Transfer Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
