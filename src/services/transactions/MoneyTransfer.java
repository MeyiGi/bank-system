package services.transactions;

public interface MoneyTransfer {
    // Transfers money from sender to recipient.
    void pay(String sender, String recipient, int amount);
}
