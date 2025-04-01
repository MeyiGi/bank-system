package view;

import javax.swing.*;

import db.entity.Bank;

public class BankInfoView extends JFrame {
    public BankInfoView(Bank bank) {
        setTitle("Bank Info");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel bankLabel = new JLabel("<html>Bank Name: " + bank.getName() + "<br/>" +
                                      "Code: " + bank.getCode() + "<br/>" +
                                      "Address: " + bank.getAddress() + "<br/>" +
                                      "Balance: " + bank.getBalance() + "</html>");
        bankLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankLabel);
    }
}
