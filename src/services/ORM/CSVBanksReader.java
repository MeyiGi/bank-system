package services.ORM;

import model.Bank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVBanksReader {

    // Статический метод для чтения банков
    public static List<Bank> read(String filename) {
        List<Bank> banks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // пропустить заголовок
                    continue;
                }

                String[] values = line.split(",");

                if (values.length < 4) continue;

                String name = values[0].trim();
                String code = values[1].trim();
                String address = values[2].trim();

                // если адрес содержит запятые, собираем обратно
                for (int i = 3; i < values.length - 1; i++) {
                    address += ", " + values[i].trim();
                }

                String balanceStr = values[values.length - 1].trim();
                BigDecimal balance = new BigDecimal(balanceStr);

                Bank bank = new Bank(name, code, address, balance);
                banks.add(bank);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }

        return banks;
    }
}
