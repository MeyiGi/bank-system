import controller.CSVController;
import view.CSVView;

public class Main {
    public static void main(String[] args) {
        CSVView view = new CSVView();
        new CSVController(view);
        view.setVisible(true);
    }
}
