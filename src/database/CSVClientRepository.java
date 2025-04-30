package database;

import model.Client;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVClientRepository implements ClientRepository {
    private final List<Client> clients;
    private final String csvFilename;

    public CSVClientRepository(String csvFilename) {
        this.csvFilename = csvFilename;
        this.clients = CSVClientsReader.read(csvFilename);  // Read data from file when initializing
    }

    public List<Client> getClients() {
        return clients;  // Return the already loaded client list
    }

    @Override
    public Client findByAccountNumber(String accountNumber) {
        return findClientByPredicate(client -> client.getBankAccount().equals(accountNumber));
    }

    @Override
    public Client findByPhoneNumber(String phoneNumber) {
        return findClientByPredicate(client -> client.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public Client findByInnNumber(String inn) {
        return findClientByPredicate(client -> client.getInn().equals(inn));
    }

    // Helper method to find clients using a predicate
    private Client findClientByPredicate(ClientPredicate predicate) {
        for (Client client : clients) {
            if (predicate.test(client)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public void saveClientsInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilename))) {
            // Write header
            writer.write("firstName,secondName,dateOfBirth,inn,phoneNumber,bankAccount,balance");
            writer.newLine();

            // Write client data
            for (Client client : clients) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%.2f",
                        client.getFirstName(),
                        client.getSecondName(),
                        client.getDateOfBirth(),
                        client.getInn(),
                        client.getPhoneNumber(),
                        client.getBankAccount(),
                        client.getBalance());  // Balance with two decimal places
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Client information successfully saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving client data: " + e.getMessage());
        }
    }

    // Functional interface to represent the condition used to find a client
    @FunctionalInterface
    private interface ClientPredicate {
        boolean test(Client client);
    }
}
