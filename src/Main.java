// import view.ClientsAndBanksInfo;

// public class Main {
//     public static void main(String[] args) {
//         // ClientsAndBanksInfo clientView = new ClientsAndBanksInfo();
//         // clientView.equals(clientView);
//     }
// }
import model.Client;
import services.database.CSVClientRepository;
import services.database.ClientRepository;

public class Main {
    public static void main(String[] args) {
        ClientRepository repository = new CSVClientRepository("data/clients.csv");

        Client x = repository.findByINN("734043838");
        System.out.println(x.getFirstName());
    }
}