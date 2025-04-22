// KIND OF ORM FOR CSV FILES

package database;

import model.Client;

public interface ClientRepository {
    Client findByAccountNumber(String inn);
    Client findByPhoneNumber(String phoneNumber);
    Client findByInnNumber(String phoneNumber);
    void saveClientsInfo();
}
