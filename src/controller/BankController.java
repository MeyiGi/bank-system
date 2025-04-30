package controller;

import model.*;
import database.*;
import view.*;

import java.awt.event.ActionEvent;
import java.util.List;

public class BankController {
    private final CSVClientRepository clientRepository;
    private ClientsView clientsView;
    private BanksView banksView;
    private MainView mainView;

    public BankController(MainView mainView) {
        this.mainView = mainView;
        this.clientRepository = new CSVClientRepository("data/clients.csv");
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
        // Before passing to the view, select the correct strategy based on the user selection.
        TransactionView view = new TransactionView(mainView, clientRepository);
        view.setVisible(true);

        if (view.isConfirmed()) {
            executeTransaction(view.getTransactionData());
            loadClients(); // Refresh clients after transaction
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
        if (data.sender != null && data.recipient != null) {
            data.strategy.pay(data.sender, data.recipient, (int) data.amount);
            clientRepository.saveClientsInfo(); // Save updated data back to CSV
        } else {
            System.err.println("Sender or recipient not found.");
        }
    }
}
