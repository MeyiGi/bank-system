package database;

import model.Client;

public interface ClientRepository {
    Client findByINN(String inn);
    Client findByPhoneNumber(String phoneNumber);
}
