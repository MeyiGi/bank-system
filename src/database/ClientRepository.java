package database;

import model.Client;

public interface ClientRepository {
    Client findByAccountNumber(String inn);
    Client findByPhoneNumber(String phoneNumber);
    void saveClientsInfo();
}
