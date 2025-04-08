package view;

import javax.swing.*;
import java.awt.*;
import controller.BankController;

public class MainView extends JFrame {
    private final BankController controller;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final ClientsView clientsView;
    private final BanksView banksView;

    public MainView() {
        // Устанавливаем Nimbus Look and Feel для современного дизайна
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Bank Information System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна

        // Создаем меню
        setJMenuBar(createMenuBar());

        controller = new BankController(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Отступы вокруг панели

        // Инициализируем представления и задаем им рамки для современного вида
        clientsView = new ClientsView();
        banksView = new BanksView();
        clientsView.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        banksView.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        mainPanel.add(clientsView, "Clients");
        mainPanel.add(banksView, "Banks");

        controller.setViews(clientsView, banksView);

        // Создаем панель для кнопок с выравниванием и отступами
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton clientsButton = new JButton("Show Clients");
        JButton banksButton = new JButton("Show Banks");
        JButton transactionButton = new JButton("Make Transaction");

        // Настройка размеров кнопок
        Dimension buttonSize = new Dimension(150, 40);
        clientsButton.setPreferredSize(buttonSize);
        banksButton.setPreferredSize(buttonSize);
        transactionButton.setPreferredSize(buttonSize);

        controlPanel.add(clientsButton);
        controlPanel.add(banksButton);
        controlPanel.add(transactionButton);

        add(mainPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Привязываем слушатели действий к контроллеру
        clientsButton.addActionListener(controller::handleClients);
        banksButton.addActionListener(controller::handleBanks);
        transactionButton.addActionListener(controller::handleTransaction);
    }

    // Создание панели меню
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Файл
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Справка
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Bank Information System\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    // Getter для cardLayout
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    // Getter для mainPanel
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
