package services;

import database.ClientRepository;
import model.Client;

public class TransferByAccountNumber implements MoneyTransfer {
    private ClientRepository clientRepository;  // Репозиторий для работы с клиентами

    public TransferByAccountNumber(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void pay(String sender, String recipient, int amount) {
        Client senderClient = findClientByAccountNumber(sender);
        Client recipientClient = findClientByAccountNumber(recipient);

        if (senderClient == null || recipientClient == null) {
            System.out.println("Ошибка: один из клиентов не найден.");
            return;
        }

        if (senderClient.getBalance() < amount) {
            System.out.println("Ошибка: недостаточно средств у отправителя.");
            return;
        }

        senderClient.setBalance(senderClient.getBalance() - amount);
        recipientClient.setBalance(recipientClient.getBalance() + amount);
        
        System.out.println("Перевод выполнен: " + amount + " от " + sender + " к " + recipient);
        // Сохраняем обновленную информацию о клиентах, если есть такой метод в репозитории
        clientRepository.saveClientsInfo();
    }

    // Метод для поиска клиента по номеру счета через ClientRepository
    private Client findClientByAccountNumber(String accountNumber) {
        return clientRepository.findByAccountNumber(accountNumber);  // Поиск через репозиторий
    }
}
