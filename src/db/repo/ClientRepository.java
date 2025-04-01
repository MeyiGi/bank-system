package db.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import db.entity.Client;

public class ClientRepository {
    // Метод для получения списка клиентов (в реальном приложении данные могут читаться из файла или базы)
    public static List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("John", new BigDecimal("5000")));
        clients.add(new Client("Jane", new BigDecimal("3000")));
        return clients;
    }
}
