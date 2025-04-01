package controller;

import model.ClientModel;
import view.CSVView;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVController {
    private CSVView view;
    private List<ClientModel> clients;

    public CSVController(CSVView view) {
        this.view = view;
        this.clients = new ArrayList<>();

        view.addShowButtonListener(e -> loadData("input.csv"));
    }

    private void loadData(String filePath) {
        clients.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length == 7) {
                    clients.add(new ClientModel(
                            fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6])
                    ));
                }
            }

            updateView();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(view, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateView() {
        String[][] data = clients.stream().map(ClientModel::toArray).toArray(String[][]::new);
        view.updateTable(data);
    }
}
