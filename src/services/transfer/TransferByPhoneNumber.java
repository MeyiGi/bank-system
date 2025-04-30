package services.transfer;

import database.ClientRepository;
import model.Client;
import services.fee.FeeCalculator;

public class TransferByPhoneNumber implements MoneyTransfer {
    private final ClientRepository clientRepository;
    private final FeeCalculator feeCalculator;

    public TransferByPhoneNumber(ClientRepository clientRepository,
                                  FeeCalculator feeCalculator) {
        this.clientRepository = clientRepository;
        this.feeCalculator     = feeCalculator;
    }

    @Override
    public void pay(String sender, String recipient, int amount) {
        Client senderClient    = clientRepository.findByPhoneNumber(sender);
        Client recipientClient = clientRepository.findByPhoneNumber(recipient);

        if (senderClient == null || recipientClient == null) {
            System.out.println("Ошибка: один из клиентов не найден.");
            return;
        }
        if (senderClient.getBalance() < amount) {
            System.out.println("Ошибка: недостаточно средств у отправителя.");
            return;
        }

        double fee = feeCalculator.calculateFee(amount);
        senderClient.setBalance(senderClient.getBalance() - amount - fee);
        recipientClient.setBalance(recipientClient.getBalance() + amount);

        System.out.println("Перевод выполнен: " + amount + ", комиссия: " + fee +
                           ", от " + sender + " к " + recipient);
        clientRepository.saveClientsInfo();
    }
}