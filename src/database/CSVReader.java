package services.database;

import java.util.List;

public interface CSVReader<T> {
    List<T> read(String filename);
}
