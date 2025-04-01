package service;

import java.math.BigDecimal;

import db.entity.Client;

public class MoneyTransferService {
    // Метод перевода денег между клиентами
    public static void transfer(BigDecimal amount, Client sender, Client recipient) throws Exception {
        if (sender.getBalance().compareTo(amount) >= 0) {
            sender.setBalance(sender.getBalance().subtract(amount));
            recipient.setBalance(recipient.getBalance().add(amount));
        } else {
            throw new Exception("Insufficient funds in sender's account");
        }
    }
}
