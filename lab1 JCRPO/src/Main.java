public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AstrologyModel model = new AstrologyModel();
            AstrologyController controller = new AstrologyController(model);
            MainView view = new MainView(controller, model);
            view.setVisible(true);
        });
    }
}