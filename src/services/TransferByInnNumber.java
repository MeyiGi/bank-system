package services;

import database.ClientRepository;
import model.Client;

public class TransferByInnNumber implements MoneyTransfer {
    private ClientRepository clientRepository;  // Репозиторий для работы с клиентами

    public TransferByInnNumber(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void pay(String sender, String recipient, int amount) {
        // Поиск клиентов по номеру телефона через репозиторий
        Client senderClient = findClientByInnNumber(sender);
        Client recipientClient = findClientByInnNumber(recipient);

        if (senderClient == null || recipientClient == null) {
            System.out.println("Ошибка: один из клиентов не найден.");
            return;
        }

        if (senderClient.getBalance() < amount) {
            System.out.println("Ошибка: недостаточно средств у отправителя.");
            return;
        }

        // Выполнение перевода
        senderClient.setBalance(senderClient.getBalance() - amount);
        recipientClient.setBalance(recipientClient.getBalance() + amount);
        
        System.out.println("Перевод выполнен: " + amount + " от " + sender + " к " + recipient);
        
        // Сохраняем обновленную информацию о клиентах
        clientRepository.saveClientsInfo();
    }

    // Метод для поиска клиента по номеру телефона через ClientRepository
    private Client findClientByInnNumber(String phoneNumber) {
        return clientRepository.findByInnNumber(phoneNumber);  // Поиск через репозиторий
    }
}
