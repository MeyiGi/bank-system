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
        this.clients = CSVClientsReader.read(csvFilename);  // Чтение данных из файла при инициализации
    }

    public List<Client> getClients() {
        return CSVClientsReader.read(csvFilename);  // Предположим, что CSVClientsReader уже существует
    }

    @Override
    public Client findByAccountNumber(String accountNumber) {
        for (Client client : clients) {
            if (client.getBankAccount().equals(accountNumber)) {
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

    @Override
    public void saveClientsInfo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilename))) {
            // Запись заголовка
            writer.write("firstName,secondName,dateOfBirth,inn,phoneNumber,bankAccount,balance");
            writer.newLine();

            // Запись данных клиентов
            for (Client client : clients) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%.2f",
                        client.getFirstName(),
                        client.getSecondName(),
                        client.getDateOfBirth(),
                        client.getInn(),
                        client.getPhoneNumber(),
                        client.getBankAccount(),
                        client.getBalance()); // Баланс с двумя знаками после запятой
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Информация о клиентах успешно сохранена в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных клиентов: " + e.getMessage());
        }
    }
}
