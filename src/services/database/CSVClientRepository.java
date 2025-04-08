package services.database;

import model.Client;

import java.util.List;

public class CSVClientRepository implements ClientRepository {
    private final List<Client> clients;

    public CSVClientRepository(String csvFilename) {
        this.clients = CSVClientsReader.read(csvFilename);
    }

    @Override
    public Client findByINN(String inn) {
        for (Client client : clients) {
            if (client.getInn().equals(inn)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        for (Client client : clients) {
            if (client.getPhoneNumber().equals(phoneNumber)) {
                return client;
            }
        }
        return null;
    }
}
