package controller;

import model.*;
import services.*;
import database.*;
import view.*;

import java.awt.event.ActionEvent;
import java.util.List;

public class BankController {
    private final CSVClientRepository clientRepository;
    private final MoneyTransfer phoneTransfer;
    private final MoneyTransfer accountTransfer;
    
    private ClientsView clientsView;
    private BanksView banksView;
    private MainView mainView;

    public BankController(MainView mainView) {
        this.mainView = mainView;
        clientRepository = new CSVClientRepository("data/clients.csv");
        phoneTransfer = new TransferByPhoneNumber(clientRepository);
        accountTransfer = new TransferByAccountNumber(clientRepository);
    }

    public void setViews(ClientsView clientsView, BanksView banksView) {
        this.clientsView = clientsView;
        this.banksView = banksView;
    }

    public void handleClients(ActionEvent e) {
        loadClients();
        mainView.getCardLayout().show(mainView.getMainPanel(), "Clients");
    }

    public void handleBanks(ActionEvent e) {
        loadBanks();
        mainView.getCardLayout().show(mainView.getMainPanel(), "Banks");
    }

    public void handleTransaction(ActionEvent e) {
        TransactionView view = new TransactionView(mainView);
        view.setVisible(true);
        
        if (view.isConfirmed()) {
            executeTransaction(view.getTransactionData());
            loadClients(); // Refresh client data
        }
    }

    public void loadClients() {
        List<Client> clients = CSVClientsReader.read("data/clients.csv");
        clientsView.updateTable(clients);
    }

    public void loadBanks() {
        List<Bank> banks = CSVBanksReader.read("data/banks.csv");
        banksView.updateTable(banks);
    }

    public void executeTransaction(TransactionView.TransactionData data) {
        MoneyTransfer transfer = data.method.equals("Phone Number") 
            ? phoneTransfer 
            : accountTransfer;
        transfer.pay(data.sender, data.recipient, (int)data.amount);
    }
}
